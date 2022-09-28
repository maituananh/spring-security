package com.springsecurity.jwtsecurity.api;

import com.springsecurity.jwtsecurity.domain.User;
import com.springsecurity.jwtsecurity.dto.UserCreate;
import com.springsecurity.jwtsecurity.dto.UserUpdate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @GetMapping("/all")
    public HttpResponse<User> fetchAll() {
        return null;
    }

    @PostMapping("/")
    public HttpResponse<User> store(@RequestBody final UserCreate userCreate) {
        return null;
    }

    @PutMapping("/")
    public HttpResponse<User> update(@RequestBody final UserUpdate userUpdate) {
        return null;
    }

    @DeleteMapping("/{id}")
    public HttpResponse<User> delete(@PathVariable final String id) {
        return null;
    }
}
