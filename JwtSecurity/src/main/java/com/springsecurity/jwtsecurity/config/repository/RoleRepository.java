package com.springsecurity.jwtsecurity.config.repository;

import com.springsecurity.jwtsecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
