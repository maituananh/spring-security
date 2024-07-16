package com.springsecurity.jwtsecurity.api;

import com.springsecurity.jwtsecurity.bloc.AuthBloc;
import com.springsecurity.jwtsecurity.dto.request.auth.LoginReq;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/api/token")
@AllArgsConstructor
public class AuthController extends BaseController {

    private AuthBloc authBloc;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody final LoginReq req) throws ParseException {
        log.info("API Login with request: {}", req);

        return ok(authBloc.login(req));
    }
}
