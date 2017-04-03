package org.oopdev.xbalancer.proxy.client;

import io.undertow.client.ClientCallback;
import io.undertow.client.ClientConnection;
import io.undertow.client.UndertowClient;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.ServerConnection;
import io.undertow.server.handlers.proxy.ProxyConnection;
import io.undertow.util.AttachmentKey;
import org.oopdev.xbalancer.proxy.host.Host;
import org.xnio.IoUtils;
import org.xnio.OptionMap;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.Channel;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ProxyClientProvider {
    private final String name;
    private final Host host;
    private final URI uri;
    private final AttachmentKey<ClientConnection> clientAttachmentKey = AttachmentKey.create(ClientConnection.class);
    private final UndertowClient client;
    private ProxyClientListener listener;

    public ProxyClientProvider(String name, Host host, ProxyClientListener listener) throws URISyntaxException {
        this.name = name;
        this.host = host;
        this.listener = listener;
        this.uri = new URI(host.toString());
        client = UndertowClient.getInstance();
    }

    public void getConnection(HttpServerExchange exchange, long timeout, TimeUnit timeUnit) {
        ClientConnection existing = exchange.getConnection().getAttachment(clientAttachmentKey);
        if (existing != null) {
            if (existing.isOpen()) {
                //this connection already has a client, re-use it
                if (listener != null) {
                    listener.completed(name, host, new ProxyConnection(existing, uri.getPath() == null ? "/" : uri.getPath()));
                }
                return;
            } else {
                exchange.getConnection().removeAttachment(clientAttachmentKey);
            }
        }
        client.connect(new ProxyClientProvider.ConnectNotifier(exchange), uri, exchange.getIoThread(), exchange.getConnection().getByteBufferPool(), OptionMap.EMPTY);
    }

    private final class ConnectNotifier implements ClientCallback<ClientConnection> {
        private final HttpServerExchange exchange;

        private ConnectNotifier(HttpServerExchange exchange) {
            this.exchange = exchange;
        }

        @Override
        public void completed(final ClientConnection connection) {
            final ServerConnection serverConnection = exchange.getConnection();
            //we attach to the connection so it can be re-used
            serverConnection.putAttachment(clientAttachmentKey, connection);
            serverConnection.addCloseListener(srcConnection -> IoUtils.safeClose(connection));
            connection.getCloseSetter().set((Channel channel) -> serverConnection.removeAttachment(clientAttachmentKey));
            //this connection already has a client, re-use it
            if (listener != null) {
                listener.completed(name, host, new ProxyConnection(connection, uri.getPath() == null ? "/" : uri.getPath()));
            }
        }

        @Override
        public void failed(IOException e) {
            if (listener != null) {
                listener.failed(name, host, e);
            }
        }
    }


}
