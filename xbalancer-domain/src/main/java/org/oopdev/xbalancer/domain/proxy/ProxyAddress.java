package org.oopdev.xbalancer.domain.proxy;

import org.oopdev.xbalancer.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Entity
@Table(name = "PROXY_ADDRESS")
public class ProxyAddress extends BaseEntity {
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;
    @Column(name = "HOST")
    private String host = "localhost";
    @Column(name = "PORT", nullable = false)
    private Integer port;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getPort() {
        return port;
    }
}
