package com.springsecurity.jwtsecurity.bloc;

import com.springsecurity.jwtsecurity.domain.Permission;
import com.springsecurity.jwtsecurity.domain.Role;
import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.domain.UserRolePermission;
import com.springsecurity.jwtsecurity.dto.request.user.UserCreateReq;
import com.springsecurity.jwtsecurity.dto.request.user.UserUpdateReq;
import com.springsecurity.jwtsecurity.dto.response.user.UserRes;
import com.springsecurity.jwtsecurity.exception.ValidatorException;
import com.springsecurity.jwtsecurity.mapper.UserMapper;
import com.springsecurity.jwtsecurity.service.AuthorService;
import com.springsecurity.jwtsecurity.service.PermissionService;
import com.springsecurity.jwtsecurity.service.RoleService;
import com.springsecurity.jwtsecurity.service.UserRolePermissionService;
import com.springsecurity.jwtsecurity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserBloc {

    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;
    private PasswordEncoder passwordEncoder;
    private AuthorService authorService;
    private UserRolePermissionService userRolePermissionService;

    public List<UserRes> fetchAllUser() {
        log.info("Fetch all user");

        var userList = userService.fetchAllUser();
        return UserMapper.MAPPER.toUserResList(userList);
    }

    public UserRes createUser(final UserCreateReq req) throws ValidatorException {
        var username = req.getUsername();

        log.info("Create user with username #{}", username);

        if (Objects.nonNull(userService.getByUsername(username))) {
            throw new ValidatorException("Username is exist");
        }

        var userRolePermissions = upOrsertUser(req.getRolePermissions());

        var user = userService.save(User.builder()
                                        .username(username)
                                        .passwordHash(passwordEncoder.encode(req.getPassword()))
                                        .userRolePermissions(userRolePermissions)
                                        .build());
        userRolePermissions.forEach(urp -> urp.setUser(user));

        return UserMapper.MAPPER.toUserRes(user);
    }

    public void updateUser(final UserUpdateReq req) throws ValidatorException {
        var username = authorService.getCurrentUsername();

        log.info("Update user with username #{}", username);

        var user = userService.getByUsername(username);

        if (Objects.isNull(user)) {
            throw new ValidatorException("Username does not exist");
        }

        var userRolePermissions = upOrsertUser(req.getRolePermissions());

        userRolePermissionService.deleteByIdIn(user.getUserRolePermissions()
                                                   .stream()
                                                   .map(UserRolePermission::getId)
                                                   .collect(Collectors.toSet()));

        user.setUserRolePermissions(userRolePermissions);
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        userRolePermissions.forEach(urp -> urp.setUser(user));

        userService.save(user);
//        return UserMapper.MAPPER.toUserRes(user);
    }

    public void deleteUser(final Long userId) {
        log.info("Delete user with userId #{}", userId);

        userService.deleteById(userId);
    }

    private Set<UserRolePermission> upOrsertUser(final Set<UserCreateReq.RolePermission> rolePermissions)
        throws ValidatorException {
        var roles = validateAndGetRoles(rolePermissions
                                            .stream()
                                            .map(UserCreateReq.RolePermission::getRole)
                                            .collect(Collectors.toSet()));

        var permissions = validateAndGetPermissions(rolePermissions
                                                        .stream()
                                                        .map(UserCreateReq.RolePermission::getPermissions)
                                                        .flatMap(Set::stream)
                                                        .collect(Collectors.toSet()));

        return rolePermissions.stream()
                              .map(rolePermission ->
                                       rolePermission.getPermissions()
                                                     .stream()
                                                     .map(permission ->
                                                              UserRolePermission.builder()
                                                                                .role(roles.get(rolePermission.getRole()))
                                                                                .permission(permissions.get(permission))
                                                                                .build()
                                                         ).collect(Collectors.toSet())
                                  ).collect(Collectors.toSet())
                              .stream()
                              .flatMap(Set::stream)
                              .collect(Collectors.toSet());
    }

    private Map<Long, Role> validateAndGetRoles(final Set<Long> roles)
        throws ValidatorException {
        var rolesEntity = roleService.fetchByIdIn(roles);

        if (roles.size() != rolesEntity.size()) {
            throw new ValidatorException("Role does not exist");
        }

        return rolesEntity.stream()
                          .collect(Collectors.toMap(Role::getId, Function.identity()));
    }

    private Map<Long, Permission> validateAndGetPermissions(final Set<Long> permissions)
        throws ValidatorException {
        var permissionsEntity = permissionService.fetchByIdIn(permissions);

        if (permissionsEntity.size() != permissions.size()) {
            throw new ValidatorException("Permissions does not exist");
        }

        return permissionsEntity.stream()
                                .collect(Collectors.toMap(Permission::getId, Function.identity()));
    }
}
