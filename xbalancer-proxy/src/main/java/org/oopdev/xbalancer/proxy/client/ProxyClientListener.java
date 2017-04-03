package org.oopdev.xbalancer.proxy.client;

import io.undertow.server.handlers.proxy.ProxyConnection;
import org.oopdev.xbalancer.proxy.host.Host;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public interface ProxyClientListener {
    void completed(String name, Host host, ProxyConnection connection);

    void failed(String name, Host host, Exception e);

}
