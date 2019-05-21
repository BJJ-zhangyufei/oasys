package com.buptiot.exception;

/**
 * Created by zyf on 2019/5/21.
 */
public class IOTException extends Exception{

    private IOTErrorCode errorCode;

    public IOTException() {
        super();
    }

    public IOTException(IOTErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public IOTException(String message, IOTErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public IOTException(String message, Throwable cause, IOTErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public IOTException(Throwable cause, IOTErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public IOTErrorCode getErrorCode() {
        return errorCode;
    }
}

