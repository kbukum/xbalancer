package org.oopdev.xbalancer.security.service;

import org.oopdev.xbalancer.domain.security.User;
import org.oopdev.xbalancer.security.auth.jwt.extractor.TokenExtractor;
import org.oopdev.xbalancer.security.auth.jwt.verifier.TokenVerifier;
import org.oopdev.xbalancer.security.config.JwtSettings;
import org.oopdev.xbalancer.security.config.WebSecurityConfig;
import org.oopdev.xbalancer.security.exceptions.InvalidJwtToken;
import org.oopdev.xbalancer.security.model.UserContext;
import org.oopdev.xbalancer.security.model.token.JwtToken;
import org.oopdev.xbalancer.security.model.token.JwtTokenFactory;
import org.oopdev.xbalancer.security.model.token.RawAccessJwtToken;
import org.oopdev.xbalancer.security.model.token.RefreshToken;
import org.oopdev.xbalancer.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kamilbukum on 03/04/2017.
 */
@Service
public class TokenService {
    @Autowired
    private JwtTokenFactory tokenFactory;
    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private TokenVerifier tokenVerifier;
    @Autowired
    @Qualifier("jwtHeaderTokenExtractor")
    private TokenExtractor tokenExtractor;

    public JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = securityService.getByUsername(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));

        List<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities == null) throw new InsufficientAuthenticationException("User has no roles assigned");

        UserContext userContext = UserContext.create(user.getUsername(), authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
