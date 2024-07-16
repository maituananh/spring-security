package com.springsecurity.jwtsecurity.repository;

import com.springsecurity.jwtsecurity.domain.UserRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolePermissionRepository
    extends JpaRepository<UserRolePermission, Long> {

    List<UserRolePermission> findByUserId(@Param("userId") Long userId);
}
