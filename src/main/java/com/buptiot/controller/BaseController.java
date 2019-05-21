package com.buptiot.controller;

import com.buptiot.dao.DataValidationException;
import com.buptiot.exception.IOTErrorCode;
import com.buptiot.exception.IOTException;
import com.buptiot.exception.IncorrectParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by zyf on 2019/5/21.
 */
@Slf4j
public abstract class BaseController {

//    @Autowired
//    private IOTErrorResponseHandler errorResponseHandler;

//    @Autowired
//    private UserService userService;


//    @ExceptionHandler(IOTException.class)
//    public void handleIOTException(IOTException ex, HttpServletResponse response) {
//        errorResponseHandler.handle(ex, response);
//    }

    IOTException handleException(Exception exception) {
        return handleException(exception, true);
    }

    private IOTException handleException(Exception exception, boolean logException) {
        if (logException) {
            log.error("Error [{}]", exception.getMessage());
        }

        String cause = "";
        if (exception.getCause() != null) {
            cause = exception.getCause().getClass().getCanonicalName();
        }

        if (exception instanceof IOTException) {
            return (IOTException) exception;
        } else if (exception instanceof IllegalArgumentException || exception instanceof IncorrectParameterException
                || exception instanceof DataValidationException || cause.contains("IncorrectParameterException")) {
            return new IOTException(exception.getMessage(), IOTErrorCode.BAD_REQUEST_PARAMS);
        }  else if(exception.getCause().getMessage().contains("Duplicate entry")){
            String causeInfo = exception.getCause().getMessage();
            int index1 = causeInfo.indexOf("'")+1;
            int index2 = causeInfo.indexOf("'",index1);
            return new IOTException("该信息已经在数据库中存在 '" + causeInfo.substring(index1,index2)+"'，请修改后重试!", IOTErrorCode.GENERAL);
        } else{
            return new IOTException(exception.getMessage(), IOTErrorCode.GENERAL);
        }
    }


}

