package com.buptiot.controller;

import com.buptiot.dao.chatGroup.chatGroupService;
import com.buptiot.pojo.chatGroup;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2019/1/23.
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class chatGroupController {

    @Autowired
    chatGroupService chatGroupService;

    //配合分页设置，获取所有的群组信息
    @RequestMapping(value = "/groupByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getGroupByPage(@RequestParam int limit,
                                @RequestParam int page) throws Exception {
        try {
            return chatGroupService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getGroupByPage error!");
        }
    }

    //获取所有的群组的页数
    @RequestMapping(value = "/groupPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getGroupPages(@RequestParam int limit) throws Exception {
        try {
            return chatGroupService.findGroupPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getGroupPages error!");
        }
    }


    //根据id获取群组信息
    @RequestMapping(value = "/groupById",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getGroupById(@RequestParam Integer Id) throws Exception{
        try {
            return chatGroupService.findGroupById(Id).toString();
        }catch (Exception e){
            throw new Exception("getGroupById error!");
        }
    }


    //根据用户id获取群组信息
    @RequestMapping(value = "/groupByUserId",params = {"userId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getGroupByUserId(@RequestParam Integer userId) throws Exception{
        try {
            return chatGroupService.findGroupByUserId(userId).toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("getUserCount error!");
        }
    }

    //根据群组名字获取群组id
    @RequestMapping(value = "/groupIdByName",params = {"chatGroupName"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getGroupIdByName(@RequestParam String chatGroupName) throws Exception{
        try {
            return chatGroupService.findGroupIdByName(chatGroupName).toString();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("getGroupName error!");
        }
    }


    //统计有多少群组
    @RequestMapping(value = "/groupCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getGroupCount() throws Exception{
        try {
            Integer count = chatGroupService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getGroupCount error!");
        }
    }


    //通过Id删除信息
    @RequestMapping(value = "/groupById",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUserInfoById(@RequestParam Integer Id){
        try {
            chatGroupService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的群组信息
    @RequestMapping(value = "/group", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllGroup() throws Exception{
        try {
            return chatGroupService.findAllGroup().toString();
        }catch (Exception e){
            throw new Exception("getUserCount error!");
        }
    }


}
