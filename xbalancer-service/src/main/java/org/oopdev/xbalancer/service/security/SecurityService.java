package org.oopdev.xbalancer.service.security;

import org.oopdev.xbalancer.domain.security.User;
import org.oopdev.xbalancer.persistence.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by kamilbukum on 02/04/2017.
 */
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
