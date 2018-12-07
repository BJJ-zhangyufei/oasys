package com.buptiot.controller;

import com.buptiot.dao.UserInfo.UserInfoService;
import com.buptiot.pojo.UserInfo;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/28.
 */
@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    //配合分页设置，获取所有的用户信息
    @RequestMapping(value = "/userInfoByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfoByPage(@RequestParam int limit,
                                @RequestParam int page) throws Exception {
        try {
            return userInfoService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getUserInfoByPage error!");
        }
    }

    //获取所有的用户的页数
    @RequestMapping(value = "/userInfoPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getUserInfoPages(@RequestParam int limit) throws Exception {
        try {
            return userInfoService.findUserInfoPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getUserInfoPages error!");
        }
    }


    //根据用户id获取用户信息
    @RequestMapping(value = "/userInfoById",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfoById(@RequestParam Integer Id) throws Exception{
        try {
            return userInfoService.findUserInfoById(Id).toString();
        }catch (Exception e){
            throw new Exception("getUserInfoById error!");
        }
    }


    //统计有多少用户
    @RequestMapping(value = "/userInfoCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getUserInfoCount() throws Exception{
        try {
            Integer count = userInfoService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getUserInfoCount error!");
        }
    }

    //增加用户的信息
    @RequestMapping(value = "/userInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createUserInfo(@RequestBody String userInformation) throws Exception{
        JsonObject userString = new JsonParser().parse(userInformation).getAsJsonObject();
        UserInfo userInfo = Json2Work(userString);
        try {
            userInfoService.save(userInfo);
            return userInfo.toString();
        } catch (Exception e) {
            throw new Exception("createUserInfo error!");
        }
    }

    //更新用户信息
    @RequestMapping(value = "/userInfo", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateUserInfo(@RequestBody String userInformation) throws Exception{
        JsonObject userInfoString = new JsonParser().parse(userInformation).getAsJsonObject();
        if(userInfoString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有id，无法更新!");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoString.get("id").getAsInt());
        userInfo.setUserName(userInfoString.get("userName").getAsString());
        userInfo.setPosition(userInfoString.get("position").getAsString());
        userInfo.setDepartment(userInfoString.get("department").getAsString());
        userInfo.setGender(userInfoString.get("gender").getAsString());
        userInfo.setAge(userInfoString.get("age").getAsInt());
        userInfo.setRole(userInfoString.get("role").getAsInt());
        if (userInfoString.get("userName") != null) {
            userInfo.setUserName(userInfoString.get("userName").getAsString());
        }
        try {
            userInfoService.update(userInfo);
            return userInfo.toString();
        } catch (Exception e) {
            throw new Exception("createUserInfo error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/userInfoById",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUserInfoById(@RequestParam Integer Id){
        try {
            userInfoService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的用户信息
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllUserInfo() throws Exception{
        try {
            return userInfoService.findAllUserInfo().toString();
        }catch (Exception e){
            throw new Exception("getUserInfoCount error!");
        }
    }

    //根据角色id获取用户信息
    @RequestMapping(value = "/userInfoByRoleId",params = {"roleId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfoByRoleId(@RequestParam Integer roleId) throws Exception{
        try {
            return userInfoService.findUserInfoByRoleId(roleId).toString();
        }catch (Exception e){
            throw new Exception("getUserInfoByRoleId error!");
        }
    }

    //根据权限id获取用户信息
    @RequestMapping(value = "/userInfoByAccessId",params = {"accessId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfoByAccessId(@RequestParam Integer accessId) throws Exception{
        try {
            return userInfoService.findUserInfoByAccessId(accessId).toString();
        }catch (Exception e){
            throw new Exception("getUserInfoByAccessId error!");
        }
    }

    private UserInfo Json2Work(JsonObject workString) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(workString.get("id").getAsInt());
        userInfo.setUserName(workString.get("userName").getAsString());
        userInfo.setPosition(workString.get("position").getAsString());
        userInfo.setDepartment(workString.get("department").getAsString());
        userInfo.setGender(workString.get("gender").getAsString());
        userInfo.setAge(workString.get("age").getAsInt());
        userInfo.setRole(workString.get("role").getAsInt());

        if (workString.get("userName") != null) {
            userInfo.setUserName(workString.get("userName").getAsString());
        }

        return userInfo;
    }
}

