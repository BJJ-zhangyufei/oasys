package com.buptiot.dao;

/**
 * Created by zyf on 2018/11/16.
 */
public class DataValidationException extends RuntimeException{

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
