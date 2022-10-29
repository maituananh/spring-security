package com.springsecurity.jwtsecurity.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidatorException extends RuntimeException {
    private int code;
    private String message;

    public ValidatorException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ValidatorException(String message) {
        this.code = 400;
        this.message = message;
    }

    public ValidatorException(String message, Throwable cause, int code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public ValidatorException(Throwable cause, int code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public ValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }
}
