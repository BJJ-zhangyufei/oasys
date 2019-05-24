package com.buptiot.interceptor;

import com.buptiot.controller.DefaultIOTAwaredController;

/**
 * Created by zyf on 2019/5/24.
 */
public class GetUserId extends DefaultIOTAwaredController {

    public int returnUserId(){

        int id = getUserId();
        return id;
    }
}
