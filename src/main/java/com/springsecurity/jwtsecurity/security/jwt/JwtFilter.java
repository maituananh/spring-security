package com.springsecurity.jwtsecurity.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@AllArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private TokenProvider tokenProvider;

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
        throws IOException, ServletException {
        log.info("Request filter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = tokenProvider.resolveToken(request);
        Claims claims = tokenProvider.validateTokenAndGet(token);

        if (Objects.nonNull(claims)) {
            Authentication authentication = tokenProvider.getAuthentication(claims, token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
