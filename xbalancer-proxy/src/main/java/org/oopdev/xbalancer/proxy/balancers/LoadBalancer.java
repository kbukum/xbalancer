package org.oopdev.xbalancer.proxy.balancers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.oopdev.xbalancer.proxy.client.ProxyClient;
import org.oopdev.xbalancer.proxy.client.ProxyClientListener;
import org.oopdev.xbalancer.proxy.host.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kamilbukum on 03/04/2017.
 */

public class LoadBalancer implements HttpHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoadBalancer.class);
    private final HostFilter hostFilter;
    private ConcurrentHashMap<String, Host> hostMap = new ConcurrentHashMap<>();
    private ProxyClientListener listener;

    public LoadBalancer(HostFilter hostFilter) {
        this.hostFilter = hostFilter;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        Map<String, Host> selectedHosts = this.hostFilter.filter(exchange, hostMap);
        if (selectedHosts == null || selectedHosts.size() == 0) {
            LOGGER.warn("Not found any host to match the url ! " + exchange.getRequestURL());
            exchange.getResponseSender().send("Not found any host to match the url ! " + exchange.getRequestURL());
            return;
        }
        exchange.dispatch(new ProxyClient(exchange, selectedHosts, this.listener));
    }

    public synchronized boolean addHost(Host host) {
        Host existHost = hostMap.get(host.getName());
        if (existHost != null) {
            LOGGER.warn("Host already exist on proxy server ! Host Name : " + host.getName());
        }
        hostMap.put(host.getName(), host);
        return true;
    }

    public synchronized boolean addHost(String name, String ip, int port) {
        Host host = hostMap.get(name);
        if (host != null) {
            LOGGER.warn("Host already exist on proxy server ! Host Name : " + name);
        }
        hostMap.put(name, new Host(name, ip, port));
        return true;
    }

    public synchronized boolean removeHost(String name) {
        Host host = hostMap.get(name);
        if (host == null) {
            LOGGER.warn("Host not exist on proxy server ! Host Name : " + name);
        }
        hostMap.remove(name);
        return true;
    }

    public void setListener(ProxyClientListener listener) {
        this.listener = listener;
    }
}
