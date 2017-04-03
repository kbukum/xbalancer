package org.oopdev.xbalancer.domain.security;

import org.oopdev.xbalancer.domain.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity {
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_PERMISSION",
            joinColumns = {
                    @JoinColumn(name = "ROLE_OID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "PERMISSION_OID")
            }
    )
    private Set<Permission> permissions = new HashSet<>();

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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
