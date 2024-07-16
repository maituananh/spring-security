package com.springsecurity.jwtsecurity.security.jwt;

public class JwtProperties {
    public static final String SECRET = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";
    public static final Long EXPIRATION_TIME = 604800000L; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
