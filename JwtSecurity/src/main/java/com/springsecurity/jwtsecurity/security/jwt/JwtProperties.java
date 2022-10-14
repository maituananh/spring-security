package com.springsecurity.jwtsecurity.security.jwt;

public class JwtProperties {
    public static final String SECRET = "SomeSecretForJWTGeneration";
    public static final Long EXPIRATION_TIME = 604800000L; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
