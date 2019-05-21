package com.buptiot.controller;

import com.buptiot.annotation.Auth;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zyf on 2019/5/21.
 */
@RestController
public class AuthController {

    @Auth(roles = {"admin","customer"})
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "hello world.";
    }

    @Auth(roles = {"tenant"})
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "just a test";
    }
}
