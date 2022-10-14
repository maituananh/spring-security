package com.springsecurity.jwtsecurity.api;

import com.springsecurity.jwtsecurity.bloc.UserBloc;
import com.springsecurity.jwtsecurity.dto.request.user.UserCreateReq;
import com.springsecurity.jwtsecurity.dto.request.user.UserUpdateReq;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController extends BaseController {

    private UserBloc userBloc;

    @GetMapping("/all")
    public ResponseEntity<?> fetchAll() {
        return ok(userBloc.fetchAllUser());
    }

    @PostMapping("/")
    public ResponseEntity<?> store(@RequestBody final UserCreateReq userCreateReq) {
        return null;
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody final UserUpdateReq userUpdateReq) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final String id) {
        return null;
    }
}
