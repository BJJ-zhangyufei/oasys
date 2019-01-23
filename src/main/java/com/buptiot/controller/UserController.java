package com.buptiot.controller;

import com.buptiot.dao.user.userService;
import com.buptiot.pojo.user;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2019/1/22.
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    userService userService;

    //配合分页设置，获取所有的用户信息
    @RequestMapping(value = "/userByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return userService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getUserInfoByPage error!");
        }
    }

    //获取所有的用户的页数
    @RequestMapping(value = "/userPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getUserPages(@RequestParam int limit) throws Exception {
        try {
            return userService.findUserPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getUserInfoPages error!");
        }
    }


    //根据用户id获取用户信息
    @RequestMapping(value = "/userById",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserById(@RequestParam Integer Id) throws Exception{
        try {
            return userService.findUserById(Id).toString();
        }catch (Exception e){
            throw new Exception("getUserById error!");
        }
    }


    //根据群组id获取用户信息
    @RequestMapping(value = "/userByGroupId",params = {"chatGroupId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserByGroupId(@RequestParam Integer chatGroupId) throws Exception{
        try {
            return userService.findUserByChatGroupId(chatGroupId).toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("getUserCount error!");
        }
    }


    //统计有多少用户
    @RequestMapping(value = "/userCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getUserCount() throws Exception{
        try {
            Integer count = userService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getUserCount error!");
        }
    }

    //增加用户的信息
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createUser(@RequestBody String userInformation) throws Exception{
        JsonObject userString = new JsonParser().parse(userInformation).getAsJsonObject();
        user user= Json2Work(userString);
        try {
            userService.save(user);
            return user.toString();
        } catch (Exception e) {
            throw new Exception("createUser error!");
        }
    }

    //更新用户信息
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateUser(@RequestBody String userInformation) throws Exception{
        JsonObject userInfoString = new JsonParser().parse(userInformation).getAsJsonObject();
        if(userInfoString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有id，无法更新!");
        }
        user user = new user();
        user.setId(userInfoString.get("id").getAsInt());
        user.setName(userInfoString.get("name").getAsString());
        user.setEmail(userInfoString.get("email").getAsString());
        if (userInfoString.get("name") != null) {
            user.setName(userInfoString.get("name").getAsString());
        }
        try {
            userService.update(user);
            return user.toString();
        } catch (Exception e) {
            throw new Exception("createUser error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/userById",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUserInfoById(@RequestParam Integer Id){
        try {
            userService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的用户信息
    @RequestMapping(value = "/user",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllUser(@RequestParam Integer Id) throws Exception{
        try {
            return userService.findAllUser(Id).toString();
        }catch (Exception e){
            throw new Exception("getUserCount error!");
        }
    }


    private user Json2Work(JsonObject userInfoString) {
        user user = new user();
        user.setId(userInfoString.get("id").getAsInt());
        user.setName(userInfoString.get("name").getAsString());
        user.setEmail(userInfoString.get("email").getAsString());
        if (userInfoString.get("name") != null) {
            user.setName(userInfoString.get("name").getAsString());
        }

        return user;
    }
}
