package org.oopdev.xbalancer.security.config;

import org.oopdev.xbalancer.common.converter.PropertyConverter;
import org.oopdev.xbalancer.security.RestAuthenticationEntryPoint;
import org.oopdev.xbalancer.security.auth.ajax.AjaxAuthenticationProvider;
import org.oopdev.xbalancer.security.auth.ajax.AjaxLoginProcessingFilter;
import org.oopdev.xbalancer.security.auth.jwt.JwtAuthenticationProvider;
import org.oopdev.xbalancer.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import org.oopdev.xbalancer.security.auth.jwt.SkipPathRequestMatcher;
import org.oopdev.xbalancer.security.auth.jwt.extractor.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSecurityConfig
 */
@ComponentScan("org.oopdev.xbalancer")
@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "xb")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Map<String, String> security = new LinkedHashMap<>();
    public static final String HEADER_KEY = "header";
    public static final String SECREY_KEY = "secret";
    public static final String AUTH_PATH_KEY = "authPath";
    public static final String LOGIN_PATH_KEY = "loginPath";
    public static final String REFRESH_TOKEN_PATH_KEY = "refreshToken";
    public static final String JWT_TOKEN_HEADER_PARAM = "X-Auth-Token";
    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
    public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${server.servlet-path}")
    private String restPath;

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(getLoginPath(), successHandler, failureHandler, PropertyConverter.JSON_MAPPER);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter() throws Exception {
        List<String> pathsToSkip = Arrays.asList(getRefreshTokenPath(), getLoginPath());
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, getAuthPath());
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    public String getLoginPath() {
        return security.getOrDefault(LOGIN_PATH_KEY, FORM_BASED_LOGIN_ENTRY_POINT);
    }

    public String getRefreshTokenPath() {
        return security.getOrDefault(REFRESH_TOKEN_PATH_KEY, TOKEN_REFRESH_ENTRY_POINT);
    }

    public String getAuthPath() {
        String authPath = security.get(AUTH_PATH_KEY);
        if (authPath == null) {
            if (restPath != null) {
                authPath = restPath;
            }
        }
        return authPath;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(getLoginPath()).permitAll() // Login end-point
                .antMatchers(getRefreshTokenPath()).permitAll() // Token refresh end-point
                .antMatchers("/console").permitAll() // H2 Console Dash-board - only for testing
                .and()
                .authorizeRequests()
                .antMatchers(getAuthPath()).authenticated() // Protected API End-points
                .and()
                .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public Map<String, String> getSecurity() {
        return security;
    }
}
