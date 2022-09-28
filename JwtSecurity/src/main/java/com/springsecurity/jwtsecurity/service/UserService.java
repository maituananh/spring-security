package com.springsecurity.jwtsecurity.service;

import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {
    private UserRepository repository;

    public User getByUsername(final String username) {
        log.info("Get by username #{}", username);
        return repository.findByUsername(username).orElseThrow(() -> new NullPointerException("user"));
    }
}
