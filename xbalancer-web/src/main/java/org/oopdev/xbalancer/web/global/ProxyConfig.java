package org.oopdev.xbalancer.web.global;

import io.undertow.server.handlers.proxy.ProxyConnection;
import org.oopdev.xbalancer.common.util.Strings;
import org.oopdev.xbalancer.proxy.ProxyServer;
import org.oopdev.xbalancer.proxy.client.ProxyClientListener;
import org.oopdev.xbalancer.proxy.host.Host;
import org.oopdev.xbalancer.service.proxy.ProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by kamilbukum on 03/04/2017.
 */
@ComponentScan("org.oopdev.xbalancer")
@Configuration
@ConfigurationProperties(prefix = "xb")
@Order(2)
public class ProxyConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyConfig.class);
    private ProxyProperties balancer;

    public void setBalancer(ProxyProperties balancer) {
        this.balancer = balancer;
    }

    public ProxyProperties getBalancer() {
        return balancer;
    }

    @Autowired
    ProxyService proxyService;

    @Bean
    public ProxyServer proxyServer() {
        ProxyServer proxyServer = new ProxyServer(balancer, (exchange, hostMap) -> {
            String requestPath = exchange.getRequestPath();
            if (requestPath.startsWith("/")) {
                requestPath = requestPath.substring(1);
            }
            if (!Strings.has(requestPath)) {
                LOGGER.warn("Proxy URL cannot be empty !");
                return null;
            } else if (requestPath.equals(balancer.getAllPath())) {
                return hostMap;
            }
            return proxyService.filter(exchange.getRequestPath(), hostMap);
        });
        proxyServer.getHandler().setListener(new ProxyClientListener() {
            @Override
            public void completed(String name, Host host, ProxyConnection connection) {
                System.out.println("Dispatch request to " + host);
            }

            @Override
            public void failed(String name, Host host, Exception e) {
                System.out.println("Dispatch request failed ! Host: " + host);
            }
        });
        proxyServer.start();
        return proxyServer;
    }
}
