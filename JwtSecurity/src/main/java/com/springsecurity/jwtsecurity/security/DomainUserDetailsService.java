package com.springsecurity.jwtsecurity.security;

import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

@Slf4j
@AllArgsConstructor
public class DomainUserDetailsService
    implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username)
        throws UsernameNotFoundException {

        User user = userService.getByUsername(username);

        return DomainUserDetails.builder()
                                .id(user.getId())
                                .password(user.getPasswordHash())
                                .roles(Collections.emptyList())
                                .authorities(Collections.emptyList()) // permission
                                .build();
    }
}
