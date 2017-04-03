package org.oopdev.xbalancer.proxy;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import org.oopdev.xbalancer.proxy.balancers.HostFilter;
import org.oopdev.xbalancer.proxy.balancers.LoadBalancer;
import org.oopdev.xbalancer.proxy.host.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kamilbukum on 31/03/2017.
 */
public class ProxyServer {
    private ProxyServerConfiguration configuration;
    private HostFilter hostFilter;
    /**
     * Logging System
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyServer.class);
    /**
     *
     */
    private LoadBalancer proxyHandler;
    /**
     *
     */
    private boolean started = false;
    /**
     *
     */
    Undertow reverseProxy = null;

    public HttpHandler getProxyHandler() {
        return proxyHandler;
    }

    public ProxyServer(ProxyServerConfiguration configuration, HostFilter filter) {
        this.configuration = configuration;
        this.hostFilter = filter;
        this.proxyHandler = new LoadBalancer(this.hostFilter);
    }

    public synchronized void start() {
        if (reverseProxy != null && started) {
            LOGGER.warn("Server already started !");
            return;
        }
        reverseProxy = Undertow.builder()
                .addHttpListener(configuration.getHost().getPort(), configuration.getHost().getHost())
                .setIoThreads(configuration.getIoThreads())
                .setHandler(this.proxyHandler)
                .build();
        reverseProxy.start();
        if (configuration.getHosts() != null && configuration.getHosts().size() > 0) {
            for (Host host : configuration.getHosts()) {
                proxyHandler.addHost(host);
            }
        }
        started = true;
    }

    public synchronized void stop() {
        if (reverseProxy == null || !started) {
            LOGGER.warn("Server already stopped !");
            return;
        }

        reverseProxy.stop();
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public LoadBalancer getHandler() {
        return this.proxyHandler;
    }
}
