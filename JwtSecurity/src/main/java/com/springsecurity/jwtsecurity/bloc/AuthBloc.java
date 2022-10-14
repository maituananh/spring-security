package com.springsecurity.jwtsecurity.bloc;

import com.springsecurity.jwtsecurity.dto.request.auth.LoginReq;
import com.springsecurity.jwtsecurity.dto.response.auth.TokenRes;
import com.springsecurity.jwtsecurity.security.DomainUserDetails;
import com.springsecurity.jwtsecurity.security.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AuthBloc {

    private AuthenticationManager authenticationManager;
    private TokenProvider tokenProvider;

    public TokenRes login(final LoginReq req) throws ParseException {
        // check login
        var domainUserDetails = authenticate(req.getUsername(), req.getPassword());
        // create token
        return tokenProvider.generateToken(domainUserDetails);
    }

    public DomainUserDetails authenticate(final String username, final String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(authenticationToken);
        return (DomainUserDetails) authentication.getPrincipal();
    }
}
