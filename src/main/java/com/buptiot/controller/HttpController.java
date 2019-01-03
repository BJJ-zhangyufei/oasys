package com.buptiot.controller;

import com.buptiot.utils.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by zyf on 2018/12/14.
 */
@RestController
@RequestMapping("/api/Token")
public class HttpController
{
    @RequestMapping(value = "/getToken", method = {RequestMethod.GET, RequestMethod.POST})
    public String getToken(HttpSession session)
    {

        HttpUtil.getAccessToken(session);
        String token=session.getAttribute("token").toString();
        return token;
    }
}
