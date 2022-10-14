package com.springsecurity.jwtsecurity.repository;

import com.springsecurity.jwtsecurity.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
}
