package com.buptiot.controller;

import com.buptiot.dao.Access.AccessService;
import com.buptiot.pojo.Access;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/30.
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class AccessController {

    @Autowired
    AccessService accessService;

    //配合分页设置，获取所有的权限信息
    @RequestMapping(value = "/accessByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAccessByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return accessService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getAccessByPage error!");
        }
    }

    //获取所有的权限的页数
    @RequestMapping(value = "/accessPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getAccessPages(@RequestParam int limit) throws Exception {
        try {
            return accessService.findAccessPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getAccessPages error!");
        }
    }


    //根据权限id获取信息
    @RequestMapping(value = "/accessByAccessId",params = {"accessId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAccessByAccessId(@RequestParam Integer accessId) throws Exception{
        try {
            return accessService.findAccessByAccessId(accessId).toString();
        }catch (Exception e){
            throw new Exception("getAccessByAccessId error!");
        }
    }


    //统计有多少权限
    @RequestMapping(value = "/accessCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAccessCount() throws Exception{
        try {
            Integer count = accessService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getAccessCount error!");
        }
    }

    //增加权限的信息
    @RequestMapping(value = "/access", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createAccess(@RequestBody String accessInfo) throws Exception{
        JsonObject userString = new JsonParser().parse(accessInfo).getAsJsonObject();
        Access access = Json2Work(userString);
        try {
            accessService.save(access);
            return accessInfo.toString();
        } catch (Exception e) {
            throw new Exception("createAccess error!");
        }
    }

    //更新权限信息
    @RequestMapping(value = "/access", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateAccess(@RequestBody String accessInfo) throws Exception{
        JsonObject userInfoString = new JsonParser().parse(accessInfo).getAsJsonObject();
        if(userInfoString.get("accessId").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        Access access= new Access();
        access.setAccessId(userInfoString.get("accessId").getAsInt());
        access.setAccessName(userInfoString.get("accessName").getAsString());
        if (userInfoString.get("accessName") != null) {
            access.setAccessName(userInfoString.get("accessName").getAsString());
        }
        try {
            accessService.update(access);
            return access.toString();
        } catch (Exception e) {
            throw new Exception("createAccess error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/accessByAccessId",params = {"accessId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAccessByAccessId(@RequestParam Integer accessId){
        try {
            accessService.deleteByAccessId(accessId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的权限信息
    @RequestMapping(value = "/access", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllAccess() throws Exception{
        try {
            return accessService.findAllAccess().toString();
        }catch (Exception e){
            throw new Exception("getAccessCount error!");
        }
    }

    //根据用户id获取信息
    @RequestMapping(value = "/accessByUserId",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAccessByUserId(@RequestParam Integer Id) throws Exception{
        try {
            return accessService.findAccessByUserId(Id).toString();
        }catch (Exception e){
            throw new Exception("getAccessByUserId error!");
        }
    }

    //根据角色id获取信息
    @RequestMapping(value = "/accessByRoleId",params = {"roleId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAccessByRoleId(@RequestParam Integer roleId) throws Exception{
        try {
            return accessService.findAccessByRoleId(roleId).toString();
        }catch (Exception e){
            throw new Exception("getAccessByRoleId error!");
        }
    }

    private Access Json2Work(JsonObject workString) {
        Access access = new Access();
        access.setAccessId(workString.get("accessId").getAsInt());
        access.setAccessName(workString.get("accessName").getAsString());

        if (workString.get("accessName") != null) {
            access.setAccessName(workString.get("accessName").getAsString());
        }

        return access;
    }
}
