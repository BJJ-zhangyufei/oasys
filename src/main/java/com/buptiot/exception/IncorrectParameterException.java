package com.buptiot.exception;

/**
 * Created by zyf on 2019/5/21.
 */
public class IncorrectParameterException extends RuntimeException {

    public IncorrectParameterException(String message) {
        super(message);
    }

    public IncorrectParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}