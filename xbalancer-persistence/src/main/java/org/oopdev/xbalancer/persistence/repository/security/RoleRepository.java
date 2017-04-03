package org.oopdev.xbalancer.persistence.repository.security;


import org.oopdev.xbalancer.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
