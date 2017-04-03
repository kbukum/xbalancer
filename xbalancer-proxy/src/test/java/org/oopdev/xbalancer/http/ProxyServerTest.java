package org.oopdev.xbalancer.http;

import org.junit.Test;
import org.oopdev.xbalancer.proxy.ProxyServer;
import org.oopdev.xbalancer.proxy.ProxyServerConfiguration;
import org.oopdev.xbalancer.proxy.host.Host;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ProxyServerTest {

    @Test
    public void start() throws Exception {

    }

    public static void main(String[] args) {


        ProxyServerConfiguration configuration = new ProxyServerConfiguration();
        configuration.setIoThreads(10);
        configuration.setHost(new Host("Server", "127.0.0.1", 8080));
        ProxyServer proxyServer = new ProxyServer(configuration, (exchange, hostMap) -> {
            Map<String, Host> filteredMap = new LinkedHashMap<>();
            for (Map.Entry<String, Host> hostEntry : hostMap.entrySet()) {
                filteredMap.put(hostEntry.getKey(), hostEntry.getValue());
            }
            return filteredMap;
        });
        proxyServer.getHandler().setListener(new ServerCallback());
        proxyServer.getHandler().addHost("server1", "localhost", 8081);
        proxyServer.getHandler().addHost("server2", "localhost", 8082);
        proxyServer.getHandler().addHost("server3", "localhost", 8083);
        proxyServer.getHandler().addHost("server4", "localhost", 8084);
        proxyServer.start();
        proxyServer.getHandler().addHost("server5", "localhost", 8085);
        proxyServer.getHandler().addHost("server6", "localhost", 8086);
    }
}