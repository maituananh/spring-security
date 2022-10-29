package com.springsecurity.jwtsecurity.service;

import com.springsecurity.jwtsecurity.domain.Role;
import com.springsecurity.jwtsecurity.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class RoleService {

    private RoleRepository repository;

    public List<Role> fetchByIdIn(final Collection<Long> ids) {
        log.info("Fetch role by id in #{}", ids);

        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return repository.findAllById(ids);
    }
}
