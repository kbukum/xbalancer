package org.oopdev.xbalancer.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * PasswordEncoderConfig
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
