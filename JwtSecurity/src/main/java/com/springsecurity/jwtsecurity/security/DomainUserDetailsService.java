package com.springsecurity.jwtsecurity.security;

import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.domain.UserRolePermission;
import com.springsecurity.jwtsecurity.service.UserRolePermissionService;
import com.springsecurity.jwtsecurity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class DomainUserDetailsService
    implements UserDetailsService {

    private UserService userService;
    private UserRolePermissionService userRolePermissionService;

    @Override
    public UserDetails loadUserByUsername(final String username)
        throws UsernameNotFoundException {
        log.info("set user details service with data: {}", username);

        User user = userService.getByUsername(username);

        List<UserRolePermission> userRolePermissions = userRolePermissionService.fetchByUserId(user.getId());

        Set<GrantedAuthority> roles = userRolePermissions.stream()
                                                         .map(UserRolePermission::getRoleName)
                                                         .map(SimpleGrantedAuthority::new)
                                                         .collect(Collectors.toSet());

        Set<GrantedAuthority> permissions = userRolePermissions.stream()
                                                               .map(UserRolePermission::getPermissionName)
                                                               .map(SimpleGrantedAuthority::new)
                                                               .collect(Collectors.toSet());

        return DomainUserDetails.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .password(user.getPasswordHash())
                                .roles(roles)
                                .authorities(permissions)
                                .build();
    }
}
