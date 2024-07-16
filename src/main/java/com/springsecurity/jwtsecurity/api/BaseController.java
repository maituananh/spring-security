package com.springsecurity.jwtsecurity.api;

import com.springsecurity.jwtsecurity.support.ResponseSupport;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

public class BaseController {

    public ResponseEntity<?> ok(final Object body) {
        return new ResponseEntity<Object>(ResponseSupport.build(body), OK);
    }

    public ResponseEntity<?> create(final Object body) {
        return new ResponseEntity<Object>(ResponseSupport.build(body), CREATED);
    }

    public ResponseEntity<?> noContent() {
        return new ResponseEntity<Object>(NO_CONTENT);
    }
}
