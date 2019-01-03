package com.buptiot.utils;

import com.google.gson.JsonObject;

/**
 * Created by zyf on 2018/12/12.
 */
public interface ResponseUtil {

    public String onSuccess(String msg);
    public String onSuccess(JsonObject jsonObject) ;
    public String onFail(String msg);
    public String onFail(Exception exception);
}
