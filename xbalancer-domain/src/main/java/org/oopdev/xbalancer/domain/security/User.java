package org.oopdev.xbalancer.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.oopdev.xbalancer.domain.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Entity
@Table(name = "USER")
public class User extends BaseEntity implements UserDetails {
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_NON_LOCKED")
    private boolean accountNonLocked = true;
    @Column(name = "CREDENTIALS_NON_EXPIRED")
    private boolean credentialsNonExpired = true;
    @Column(name = "ACCOUNT_ENABLED")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_OID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_OID")
            }
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Transient
    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new LinkedList<>();
        for (Role role : this.getRoles()) {
            authorityList.addAll(role.getPermissions());
        }
        return authorityList;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
