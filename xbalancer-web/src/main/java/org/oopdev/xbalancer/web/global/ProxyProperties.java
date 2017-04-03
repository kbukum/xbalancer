package org.oopdev.xbalancer.web.global;

import org.oopdev.xbalancer.proxy.ProxyServerConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kamilbukum on 03/04/2017.
 */
public class ProxyProperties extends ProxyServerConfiguration {
    private String allPath;
    private boolean dynamic = true;
    private Map<String, Object> proxies = new LinkedHashMap<>();

    public void setAllPath(String allPath) {
        this.allPath = allPath;
    }

    public String getAllPath() {
        return allPath;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Map<String, Object> getProxies() {
        return proxies;
    }

    public void setProxies(Map<String, Object> proxies) {
        this.proxies = proxies;
    }
}
