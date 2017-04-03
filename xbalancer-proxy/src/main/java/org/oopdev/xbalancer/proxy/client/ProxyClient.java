package org.oopdev.xbalancer.proxy.client;

import io.undertow.server.HttpServerExchange;
import org.oopdev.xbalancer.proxy.host.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ProxyClient implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyClient.class);
    long timeout = 3000;
    private final Map<String, Host> hostMap;
    private final HttpServerExchange exchange;
    private ProxyClientListener listener;

    public ProxyClient(HttpServerExchange exchange, Map<String, Host> selectedHosts, ProxyClientListener listener) {
        this.exchange = exchange;
        this.hostMap = selectedHosts;
        this.listener = listener;
    }

    @Override
    public void run() {
        Queue<ProxyClientProvider> proxyClientProviders = new ConcurrentLinkedQueue<>();
        for (Map.Entry<String, Host> hostEntry : hostMap.entrySet()) {
            try {
                ProxyClientProvider provider = new ProxyClientProvider(
                        hostEntry.getKey(),
                        hostEntry.getValue(),
                        listener
                );
                proxyClientProviders.add(provider);
            } catch (URISyntaxException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
        while (!proxyClientProviders.isEmpty()) {
            ProxyClientProvider provider = proxyClientProviders.poll();
            provider.getConnection(exchange, timeout, TimeUnit.MILLISECONDS);
        }
    }
}
