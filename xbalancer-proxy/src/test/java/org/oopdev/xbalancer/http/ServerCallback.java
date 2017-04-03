package org.oopdev.xbalancer.http;

import io.undertow.server.handlers.proxy.ProxyConnection;
import org.oopdev.xbalancer.proxy.client.ProxyClientListener;
import org.oopdev.xbalancer.proxy.host.Host;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ServerCallback implements ProxyClientListener {
    @Override
    public void completed(String name, Host host, ProxyConnection connection) {
        System.out.println(name);
        System.out.println(host);
        System.out.println(connection);
    }

    @Override
    public void failed(String name, Host host, Exception e) {
        System.out.println(name);
        System.out.println(host);
        System.out.println(e);
    }
}