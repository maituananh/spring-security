package com.springsecurity.jwtsecurity.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
        throws IOException, ServletException {
        log.info("Request filter");
        // verify token
        // save userPrincipal
    }
}
