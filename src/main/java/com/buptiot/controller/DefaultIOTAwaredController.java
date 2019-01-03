package com.buptiot.controller;

import com.buptiot.utils.HttpUtil;
import  com.buptiot.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zyf on 2018/12/12.
 */
public class DefaultIOTAwaredController {

    @Value("${device-access.host}")
    String deviceAccessHost;

    @Value("${device-access.port}")
    String deviceAccessPort;

    @Value("${account.host}")
    String accountHost ;

    @Value("${account.port}")
    String accountPort ;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ResponseUtil responseUtil ;

    protected String getServer(){
        return "";
    }

    protected String getDeviceAccessServer() {
        return deviceAccessHost+":"+ deviceAccessPort ;
    }

    protected String getAccountServer(){return accountHost+":"+accountPort ; }

    protected String retSuccess(String msg) {
        return responseUtil.onSuccess(msg) ;
    }

    protected String retFail(String msg) {
        return responseUtil.onFail(msg) ;
    }

    public Integer getTenantId(){
        HttpSession sess = request.getSession();
        Integer tenantId = (Integer) sess.getAttribute("tenant_id");
//        String res = HttpUtil.getAccessToken(sess);
//        JsonObject jo = (JsonObject)new JsonParser().parse(res);
//        Integer tenantId = jo.get("tenant_id").getAsInt();
        return tenantId;
    }

    public Integer getCustomerId(){
        HttpSession sess = request.getSession();
        Integer customerId = (Integer) sess.getAttribute("customer_id");
//        String res = HttpUtil.getAccessToken(sess);
//        JsonObject jo = (JsonObject)new JsonParser().parse(res);
//        Integer customerId = jo.get("customer_id").getAsInt();
        return customerId;
    }
}
