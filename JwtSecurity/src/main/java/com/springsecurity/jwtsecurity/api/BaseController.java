package com.springsecurity.jwtsecurity.api;

import com.springsecurity.jwtsecurity.support.ResponseSupport;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

public class BaseController {

    public ResponseEntity<?> ok(final Object body) {
        return new ResponseEntity<Object>(ResponseSupport.build(body), OK);
    }
}
