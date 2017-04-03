package org.oopdev.xbalancer.proxy;

import org.oopdev.xbalancer.proxy.host.Host;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ProxyProperties {
    private Host host;
    private List<Host> hosts = new LinkedList<>();
    private int ioThreads = 5;
    private String allPath;
    private boolean dynamic = true;
    private Map<String, List<String>> proxies = new ConcurrentHashMap<>();

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public int getIoThreads() {
        return ioThreads;
    }

    public void setIoThreads(int ioThreads) {
        this.ioThreads = ioThreads;
    }

    public String getAllPath() {
        return allPath;
    }

    public void setAllPath(String allPath) {
        this.allPath = allPath;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Map<String, List<String>> getProxies() {
        return proxies;
    }

    public void setProxies(Map<String, List<String>> proxies) {
        this.proxies = proxies;
    }
}
