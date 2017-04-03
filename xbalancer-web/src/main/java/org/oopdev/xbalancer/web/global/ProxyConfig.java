package org.oopdev.xbalancer.web.global;

import io.undertow.server.handlers.proxy.ProxyConnection;
import org.oopdev.xbalancer.common.util.Strings;
import org.oopdev.xbalancer.proxy.ProxyProperties;
import org.oopdev.xbalancer.proxy.ProxyServer;
import org.oopdev.xbalancer.proxy.client.ProxyClientListener;
import org.oopdev.xbalancer.proxy.client.ProxyClientRegister;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
            if (requestPath.equals("/*")) {
                return hostMap;
            } else if (requestPath.startsWith("/")) {
                requestPath = requestPath.substring(1);
            }

            if (!Strings.has(requestPath)) {
                LOGGER.warn("Proxy URL cannot be empty !");
                return null;
            } else if (requestPath.equals(balancer.getAllPath())) {
                return hostMap;
            }
            if (!balancer.isDynamic()) {
                return manuelFilter(requestPath);
            }
            return proxyService.filter(requestPath);
        });

        proxyServer.getHandler().setListener(new ProxyClientListener() {
            @Override
            public void completed(String name, Host host, ProxyConnection connection) {
                LOGGER.info("Dispatch request to " + host);
            }

            @Override
            public void failed(String name, Host host, Exception e) {
                LOGGER.info("Dispatch request failed ! Host: " + host);
            }
        });
        proxyServer.start();
        return proxyServer;
    }

    public Map<String, Host> manuelFilter(String path) {
        List<String> hostNames = balancer.getProxies().get(path);

        if (hostNames == null || hostNames.size() == 0) return null;

        Map<String, Host> hostMap = new LinkedHashMap<>();
        for (Host host : balancer.getHosts()) {
            if (hostNames.contains(host)) {
                hostMap.put(host.getName(), host);
            }
        }
        return hostMap;
    }

    @Bean
    public ProxyClientRegister clientRegister(ProxyServer proxyServer) {
        ProxyClientRegister proxyClientRegister = new ProxyClientRegister(proxyServer);
        if (!balancer.isDynamic()) {
            for (Host host : balancer.getHosts()) {
                proxyClientRegister.register(host);
            }
        }
        return proxyClientRegister;
    }
}
