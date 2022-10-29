package com.springsecurity.jwtsecurity.aspect;

import com.springsecurity.jwtsecurity.exception.ValidatorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    private static final String MESSAGE = "message";
    private static final String CODE = "code";

    @org.springframework.web.bind.annotation.ExceptionHandler(ValidatorException.class)
    public ResponseEntity<?> handleExceptionA(Exception e) {
        Map<String, Object> mapProblem = new HashMap<>();
        mapProblem.put(MESSAGE, e.getMessage());
        mapProblem.put(CODE, ((ValidatorException) e).getCode());

        return ResponseEntity.status(400).body(mapProblem);
    }
}
