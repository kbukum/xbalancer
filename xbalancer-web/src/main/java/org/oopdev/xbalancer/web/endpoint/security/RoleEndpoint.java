package org.oopdev.xbalancer.web.endpoint.security;

import org.oopdev.xbalancer.domain.security.Role;
import org.oopdev.xbalancer.service.security.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@RestController
@RequestMapping(value = "roles")
public class RoleEndpoint {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAnyAuthority('ROLE')")
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE')")
    @RequestMapping(method = RequestMethod.POST)
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @PreAuthorize("hasAnyAuthority('ROLE')")
    @RequestMapping(method = RequestMethod.DELETE, value = "{oid}")
    public Role delete(@PathVariable("oid") String id) {
        return roleService.delete(id);
    }
}

