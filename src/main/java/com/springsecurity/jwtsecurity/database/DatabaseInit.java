//package com.springsecurity.jwtsecurity.database;
//
//import com.springsecurity.jwtsecurity.domain.Permission;
//import com.springsecurity.jwtsecurity.domain.Role;
//import com.springsecurity.jwtsecurity.domain.User;
//import com.springsecurity.jwtsecurity.domain.UserRolePermission;
//import com.springsecurity.jwtsecurity.repository.PermissionRepository;
//import com.springsecurity.jwtsecurity.repository.RoleRepository;
//import com.springsecurity.jwtsecurity.repository.UserRepository;
//import com.springsecurity.jwtsecurity.repository.UserRolePermissionRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Service
//@AllArgsConstructor
//public class DatabaseInit implements CommandLineRunner {
//
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//    private RoleRepository roleRepository;
//    private PermissionRepository permissionRepository;
//    private UserRolePermissionRepository userRolePermissionRepository;
//
//    @Override
//    public void run(String... args) {
//        // Delete all
////        this.userRepository.deleteAll();
////        this.roleRepository.deleteAll();
////        this.permissionRepository.deleteAll();
////        this.userRolePermissionRepository.deleteAll();
//
//        // Create role
//        Role roleAdmin = Role.builder().name("ROLE_ADMIN").build();
//        Role roleUser = Role.builder().name("ROLE_USER").build();
//
//        roleAdmin = this.roleRepository.save(roleAdmin);
//        roleUser = this.roleRepository.save(roleUser);
//
//        // Create Permission
//        Permission readPermission = Permission.builder().name("READ").build();
//        Permission writePermission = Permission.builder().name("WRITE").build();
//        Permission deletePermission = Permission.builder().name("DELETE").build();
//
//        readPermission = this.permissionRepository.save(readPermission);
//        writePermission = this.permissionRepository.save(writePermission);
//        deletePermission = this.permissionRepository.save(deletePermission);
//
//        // Create User Role Permission
//        UserRolePermission userRolePermission1 = UserRolePermission.builder()
//                                                                   .role(roleAdmin)
//                                                                   .permission(readPermission)
//                                                                   .build();
//        UserRolePermission userRolePermission2 = UserRolePermission.builder()
//                                                                   .role(roleAdmin)
//                                                                   .permission(writePermission)
//                                                                   .build();
//        UserRolePermission userRolePermission3 = UserRolePermission.builder()
//                                                                   .role(roleAdmin)
//                                                                   .permission(deletePermission)
//                                                                   .build();
//
//        UserRolePermission userRolePermission5 = UserRolePermission.builder()
//                                                                   .role(roleUser)
//                                                                   .permission(deletePermission)
//                                                                   .build();
//        UserRolePermission userRolePermission6 = UserRolePermission.builder()
//                                                                   .role(roleUser)
//                                                                   .permission(writePermission)
//                                                                   .build();
//        UserRolePermission userRolePermission7 = UserRolePermission.builder()
//                                                                   .role(roleUser)
//                                                                   .permission(readPermission)
//                                                                   .build();
//
//        Set<UserRolePermission> userRolePermissionUser = Set.of(userRolePermission5, userRolePermission6, userRolePermission7);
//        Set<UserRolePermission> userRolePermissionAdmin = Set.of(userRolePermission1, userRolePermission2, userRolePermission3);
//
//        // Crete users
//        User userAdmin = User.builder()
//                             .username("admin")
//                             .passwordHash(passwordEncoder.encode("admin"))
//                             .userRolePermissions(userRolePermissionAdmin)
//                             .enable(true)
//                             .build();
//
//        User user = User.builder()
//                        .username("user")
//                        .passwordHash(passwordEncoder.encode("user"))
//                        .userRolePermissions(userRolePermissionUser)
//                        .enable(true)
//                        .build();
//
//        userRolePermissionAdmin.forEach(urp -> urp.setUser(userAdmin));
//        userRolePermissionUser.forEach(urp -> urp.setUser(user));
//
//        this.userRepository.save(userAdmin);
//        this.userRepository.save(user);
//    }
//}
