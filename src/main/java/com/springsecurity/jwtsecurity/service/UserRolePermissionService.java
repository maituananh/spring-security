package com.springsecurity.jwtsecurity.service;

import com.springsecurity.jwtsecurity.repository.UserRolePermissionRepository;
import com.springsecurity.jwtsecurity.domain.UserRolePermission;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserRolePermissionService {

    private UserRolePermissionRepository repository;

    public List<UserRolePermission> fetchByUserId(final Long userId) {
        log.info("Fetch by userId #{}", userId);
        return repository.findByUserId(userId);
    }

    public void deleteByIdIn(final Collection<Long> ids) {
        log.info("Delete by ids #{}", ids);
        repository.deleteAllById(ids);
    }
}
