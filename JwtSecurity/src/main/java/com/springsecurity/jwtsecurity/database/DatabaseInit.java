package com.springsecurity.jwtsecurity.database;

import com.springsecurity.jwtsecurity.domain.Permission;
import com.springsecurity.jwtsecurity.domain.Role;
import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.domain.UserRolePermission;
import com.springsecurity.jwtsecurity.config.repository.PermissionRepository;
import com.springsecurity.jwtsecurity.config.repository.RoleRepository;
import com.springsecurity.jwtsecurity.config.repository.UserRepository;
import com.springsecurity.jwtsecurity.config.repository.UserRolePermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class DatabaseInit implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;
    private UserRolePermissionRepository userRolePermissionRepository;

    @Override
    public void run(String... args) {
        // Delete all
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        this.permissionRepository.deleteAll();
        this.userRolePermissionRepository.deleteAll();

        // Crete users
        User userAdmin = User.builder()
                             .username("admin")
                             .passwordHash(passwordEncoder.encode("admin"))
                             .build();
        User user = User.builder()
                        .username("user")
                        .passwordHash(passwordEncoder.encode("user"))
                        .build();

        userAdmin = this.userRepository.save(userAdmin);
        user = this.userRepository.save(user);

        // Create role
        Role roleAdmin = Role.builder().name("ROLE_ADMIN").build();
        Role roleUser = Role.builder().name("ROLE_USER").build();

        roleAdmin = this.roleRepository.save(roleAdmin);
        roleUser = this.roleRepository.save(roleUser);

        // Create Permission
        Permission readPermission = Permission.builder().name("READ").build();
        Permission writePermission = Permission.builder().name("WRITE").build();
        Permission createPermission = Permission.builder().name("CREATE").build();
        Permission deletePermission = Permission.builder().name("DELETE").build();

        readPermission = this.permissionRepository.save(readPermission);
        writePermission = this.permissionRepository.save(writePermission);
        createPermission = this.permissionRepository.save(createPermission);
        deletePermission = this.permissionRepository.save(deletePermission);

        // Create User Role Permission
        UserRolePermission userRolePermission1 = UserRolePermission.builder()
                                                                   .userId(userAdmin.getId())
                                                                   .roleId(roleAdmin.getId())
                                                                   .permissionId(readPermission.getId())
                                                                   .build();
        UserRolePermission userRolePermission2 = UserRolePermission.builder()
                                                                   .userId(userAdmin.getId())
                                                                   .roleId(roleAdmin.getId())
                                                                   .permissionId(writePermission.getId())
                                                                   .build();
        UserRolePermission userRolePermission3 = UserRolePermission.builder()
                                                                   .userId(userAdmin.getId())
                                                                   .roleId(roleAdmin.getId())
                                                                   .permissionId(createPermission.getId())
                                                                   .build();
        UserRolePermission userRolePermission4 = UserRolePermission.builder()
                                                                   .userId(userAdmin.getId())
                                                                   .roleId(roleAdmin.getId())
                                                                   .permissionId(deletePermission.getId())
                                                                   .build();

        UserRolePermission userRolePermission5 = UserRolePermission.builder()
                                                                   .userId(user.getId())
                                                                   .roleId(roleUser.getId())
                                                                   .permissionId(readPermission.getId())
                                                                   .build();
        UserRolePermission userRolePermission6 = UserRolePermission.builder()
                                                                   .userId(user.getId())
                                                                   .roleId(roleUser.getId())
                                                                   .permissionId(writePermission.getId())
                                                                   .build();

        List<UserRolePermission> userRolePermissions = Arrays.asList(userRolePermission1,
                                                                     userRolePermission2,
                                                                     userRolePermission3,
                                                                     userRolePermission4,
                                                                     userRolePermission5,
                                                                     userRolePermission6);
        this.userRolePermissionRepository.saveAll(userRolePermissions);
    }
}
