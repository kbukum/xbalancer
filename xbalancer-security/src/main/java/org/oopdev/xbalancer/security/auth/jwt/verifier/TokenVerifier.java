package org.oopdev.xbalancer.security.auth.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
