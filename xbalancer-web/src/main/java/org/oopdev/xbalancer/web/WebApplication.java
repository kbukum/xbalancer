package org.oopdev.xbalancer.web;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Created by kamilbukum on 01/04/2017.
 */
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({"org.oopdev.xbalancer"})
@EnableJpaRepositories(basePackages = {"org.oopdev.xbalancer.persistence"})
@EntityScan(basePackages = {"org.oopdev.xbalancer.domain"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebApplication extends SpringBootServletInitializer implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass()).bannerMode(Banner.Mode.OFF);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }
}