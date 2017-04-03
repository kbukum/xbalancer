package org.oopdev.xbalancer.persistence.repository.security;

import org.oopdev.xbalancer.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kamilbukum on 02/04/2017.
 */
public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String username);
}
