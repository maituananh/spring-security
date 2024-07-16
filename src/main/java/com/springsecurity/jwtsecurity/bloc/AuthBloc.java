package com.springsecurity.jwtsecurity.bloc;

import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.dto.request.auth.LoginReq;
import com.springsecurity.jwtsecurity.dto.response.auth.TokenRes;
import com.springsecurity.jwtsecurity.security.DomainUserDetails;
import com.springsecurity.jwtsecurity.security.jwt.TokenProvider;
import com.springsecurity.jwtsecurity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuthBloc {

    private TokenProvider tokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public TokenRes login(final LoginReq req) {
        // check login
        var domainUserDetails = authenticate(req.getUsername(), req.getPassword());
        // create token
        return tokenProvider.generateToken(domainUserDetails);
    }

    public DomainUserDetails authenticate(final String username, final String password) {
        User user = userService.getByUsername(username);

        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new UsernameNotFoundException(username);
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return DomainUserDetails.builder()
                .id(user.getId())
                .roles(Collections.emptyList())
                .authorities(Collections.emptyList())
                .enabled(true)
                .username(String.valueOf(authenticationToken.getPrincipal())).build();
    }
}
