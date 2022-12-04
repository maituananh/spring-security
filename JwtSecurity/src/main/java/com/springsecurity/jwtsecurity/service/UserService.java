package com.springsecurity.jwtsecurity.service;

import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(propagation = Propagation.NESTED, isolation = Isolation.DEFAULT)
public class UserService extends ServiceGeneric<UserRepository> {

    private UserRepository repository;

    public User getByUsername(final String username) {
        log.info("Get by username #{}", username);
        return repository.findByUsername(username).orElse(null);
    }

    public List<User> fetchAllUser() {
        log.info("Fetch all user");
        return repository.findAll();
    }

    public User save(final User user) {
        log.info("save user with username #{}", user.getUsername());
        return repository.save(user);
    }

    public void deleteById(final Long userId) {
        log.info("Delete user by id #{}", userId);
        repository.deleteById(userId);
    }

    public Object custom() {
        User person = new User();
        ExampleMatcher matcher = ExampleMatcher.matching()
                                               .withIgnorePaths("lastname")
                                               .withIncludeNullValues()
                                               .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);

        Example<User> example = Example.of(person, matcher);

        Example<User> example1 = Example.of(new User());
        List<User> match = repository.findBy(example1,
                                             q -> q
                                                 .sortBy(Sort.by("lastname")
                                                             .descending()).all()
//                                                     .first()
                                            );
        return match;
    }
}
