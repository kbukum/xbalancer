package org.oopdev.xbalancer.domain.proxy;

import org.oopdev.xbalancer.domain.BaseEntity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@Entity
@Table(name = "PROXY_POINTER")
public class ProxyPointer extends BaseEntity {
    @Column(name = "PATH", unique = true)
    private String path;
    @Column(name = "DESCRIPTION")
    private String description;


    @JoinTable(name = "PROXY_POINTER_ADDRESSES")
    @OneToMany
    private List<ProxyAddress> proxyAddressList = new LinkedList<>();

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<ProxyAddress> getProxyAddressList() {
        return proxyAddressList;
    }

    public void setProxyAddressList(List<ProxyAddress> proxyAddressList) {
        this.proxyAddressList = proxyAddressList;
    }
}
