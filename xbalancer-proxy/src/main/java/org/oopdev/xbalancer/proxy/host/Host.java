package org.oopdev.xbalancer.proxy.host;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class Host {
    private String name;
    private String scheme = "http";
    private String host;
    private int port;

    public Host() {

    }

    public Host(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public Host(String name, String scheme, String host, int port) {
        this.name = name;
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return this.scheme + "://" + host + ":" + port;
    }
}
