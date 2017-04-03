package org.oopdev.xbalancer.proxy;

import org.oopdev.xbalancer.proxy.host.Host;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kamilbukum on 31/03/2017.
 */
public class ProxyServerConfiguration {
    private Host host;
    private List<Host> hosts = new LinkedList<>();
    private int ioThreads = 5;

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

    public void setIoThreads(int ioThreads) {
        this.ioThreads = ioThreads;
    }

    public int getIoThreads() {
        return ioThreads;
    }
}
