package com.buptiot.controller;

import com.buptiot.config.MySessionContext;
import com.buptiot.utils.HttpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.buptiot.config.MySessionContext;
import com.buptiot.utils.HttpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

/**
 * Created by zyf on 2018/12/12.
 */
@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class LoginController extends DefaultIOTAwaredController{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody String body){
        JsonObject json1 = new JsonParser().parse(body).getAsJsonObject();
        String username = json1.get("username").getAsString() ;
        String password = json1.get("password").getAsString() ;
        HttpSession session = request.getSession();

        session.setAttribute("username", username);
        session.setAttribute("password", password);

        String res = HttpUtil.getAccessToken(session);
        JsonObject responseJson = (JsonObject) new JsonParser().parse(res);
        if(responseJson.has("error")){
            response.setStatus(400);
            session.removeAttribute("username");
            session.removeAttribute("password");
        }else if(responseJson.has("access_token")){
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);   //完成登录
        }
        return res;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        boolean res = HttpUtil.logout(token);
        if(res) {
            session.removeAttribute("username");
            session.removeAttribute("password");
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return retSuccess("success to logout");
        }else {
            return retFail("fail to logout");
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
    public String changePassword (
            @RequestBody String changePasswordRequest) {
        String requestAddr = "/api/v1/account/changePassword";
        JsonObject PasswordInfoJson = (JsonObject) new JsonParser().parse(changePasswordRequest);
        String responseContent = null;
        try {
            responseContent = HttpUtil.sendPutToThingsboard("http://" + getAccountServer() + requestAddr,
                    null,
                    PasswordInfoJson,
                    request.getSession());
            if(responseContent.equals("")){
                return "succeed to change password!";
            }else {
                JsonObject responseJson = (JsonObject) new JsonParser().parse(responseContent);
                if (responseJson.has("status")) {
                    response.setStatus(responseJson.get("status").getAsInt());
                }
                return responseContent;
            }
        } catch (Exception e) {
            return retFail(e.toString());
        }

    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public String refreshToken () {
        HttpSession session = request.getSession();
        String refresh_token = session.getAttribute("refreshToken").toString();
        String res = HttpUtil.refreshToken(refresh_token);
        JsonObject newAccessTokenJson = (JsonObject) new JsonParser().parse(res);
        session.setAttribute("token",newAccessTokenJson.get("access_token").getAsString());
        session.setAttribute("refreshToken",newAccessTokenJson.get("refresh_token").getAsString());
        return newAccessTokenJson.get("access_token").getAsString();
    }

    @RequestMapping(value = "/authorize/{sessionId}", method = RequestMethod.GET)
    @ResponseBody
    public String authorize(@PathVariable("sessionId") String sessionId){


        MySessionContext myc= MySessionContext.getInstance();
        HttpSession sess = myc.getSession(sessionId);


        if(sess != null){
            return sess.getAttribute("token").toString();
        }else {
            response.setStatus(401);
            return "unauthorized";
        }
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    @ResponseBody
    public String authorize(){

        String req =  request.getRequestedSessionId();
        String result = "{\"session\":\""+req+"\"}";
        return result;

    }

}
