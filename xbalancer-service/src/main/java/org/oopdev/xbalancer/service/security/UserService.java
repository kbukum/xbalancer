package org.oopdev.xbalancer.service.security;

import org.oopdev.xbalancer.domain.security.User;
import org.oopdev.xbalancer.persistence.repository.security.UserRepository;
import org.oopdev.xbalancer.service.jpa.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends JpaService<User, String> {
    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
