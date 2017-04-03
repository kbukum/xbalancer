package org.oopdev.xbalancer.web.endpoint.security;

import org.oopdev.xbalancer.domain.security.User;
import org.oopdev.xbalancer.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@RestController
@RequestMapping(value = "users")
public class UserEndpoint {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @RequestMapping(method = RequestMethod.DELETE, value = "{oid}")
    public User delete(@PathVariable("oid") String id) {
        return userService.delete(id);
    }
}

