package org.oopdev.xbalancer.proxy.client;

import org.oopdev.xbalancer.proxy.ProxyServer;
import org.oopdev.xbalancer.proxy.host.Host;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ProxyClientRegister {

    private final ProxyServer proxyServer;

    public ProxyClientRegister(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    public boolean register(Host host) {
        return proxyServer.getHandler().addHost(host);
    }

    public boolean register(String name, String ip, int port) {
        return proxyServer.getHandler().addHost(name, ip, port);
    }

    public boolean unRegister(String name) {
        return proxyServer.getHandler().removeHost(name);
    }
}
