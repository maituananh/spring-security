package com.springsecurity.jwtsecurity.config.repository;

import com.springsecurity.jwtsecurity.domain.UserRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolePermissionRepository
    extends JpaRepository<UserRolePermission, Long> {

    @Query("SELECT new UserRolePermission(urp.id, r.name, p.name)"
        + " FROM UserRolePermission urp"
        + " JOIN Role r ON r.id = urp.roleId"
        + " JOIN Permission p ON p.id = urp.permissionId"
        + " WHERE urp.userId = :userId")
    List<UserRolePermission> findByUserId(@Param("userId") Long userId);
}
