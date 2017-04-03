package org.oopdev.xbalancer.domain.security;

import org.oopdev.xbalancer.domain.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Entity
@Table(name = "PERMISSON")
public class Permission extends BaseEntity implements GrantedAuthority {
    @Column(name = "AUTHORITY", unique = true)
    private String authority;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
