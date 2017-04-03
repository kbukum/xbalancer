package org.oopdev.xbalancer.service.security;

import org.oopdev.xbalancer.domain.security.Role;
import org.oopdev.xbalancer.persistence.repository.security.RoleRepository;
import org.oopdev.xbalancer.service.jpa.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Service
public class RoleService extends JpaService<Role, String> {
    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
    }
}

