package com.buptiot.controller;

import com.buptiot.dao.User.UserService;
import com.buptiot.pojo.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/21.
 */
@RestController
@RequestMapping("/api/v1/info")
public class UserController {
    @Autowired
    UserService userService;

    //配合分页设置，获取所有的角色信息
    @RequestMapping(value = "/UserByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserByPage(@RequestParam int limit,
                                   @RequestParam int page) throws Exception {
        try {
            return userService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getUserByPage error!");
        }
    }

    //获取所有的角色的页数
    @RequestMapping(value = "/userPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getUserPages(@RequestParam int limit) throws Exception {
        try {
            return userService.findUserPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getUserPages error!");
        }
    }


    //根据角色id获取入廊信息
    @RequestMapping(value = "/userById",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserById(@RequestParam Integer Id) throws Exception{
        try {
            return userService.findUserById(Id).toString();
        }catch (Exception e){
            throw new Exception("getUserById error!");
        }
    }


    //统计有多少角色
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

    //增加角色的信息
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createUser(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        User user = Json2Work(workString);
        try {
            userService.save(user);
            return user.toString();
        } catch (Exception e) {
            throw new Exception("createUser error!");
        }
    }

    //更新角色信息
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateUser(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        if(workString.get("Id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        User user = new User();
        user.setId(workString.get("id").getAsInt());
        user.setUserName(workString.get("userName").getAsString());
        user.setLoginName(workString.get("loginName").getAsString());
        user.setLoginPwd(workString.get("loginPwd").getAsString());
        user.setRole(workString.get("role").getAsInt());
        if (workString.get("userName") != null) {
            user.setUserName(workString.get("userName").getAsString());
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
    public void deleteUserById(@RequestParam Integer Id){
        try {
            userService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的角色信息
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllUser() throws Exception{
        try {
            return userService.findAllUser().toString();
        }catch (Exception e){
            throw new Exception("getUserCount error!");
        }
    }

    private User Json2Work(JsonObject workString) {
        User user = new User();
        user.setId(workString.get("id").getAsInt());
        user.setUserName(workString.get("userName").getAsString());
        user.setLoginName(workString.get("loginName").getAsString());
        user.setLoginPwd(workString.get("loginPwd").getAsString());
        user.setRole(workString.get("role").getAsInt());

        if (workString.get("userName") != null) {
            user.setUserName(workString.get("userName").getAsString());
        }

        return user;
    }
}
