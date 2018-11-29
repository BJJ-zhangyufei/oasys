package com.buptiot.controller;

import com.buptiot.dao.Role.RoleService;
import com.buptiot.pojo.Role;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/29.
 */
@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class RoleController {

    @Autowired
    RoleService roleService;

    //配合分页设置，获取所有的角色信息
    @RequestMapping(value = "/roleByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfoByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return roleService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getRoleByPage error!");
        }
    }

    //获取所有的角色的页数
    @RequestMapping(value = "/rolePages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getRolePages(@RequestParam int limit) throws Exception {
        try {
            return roleService.findRolePageNum(limit);
        } catch (Exception e) {
            throw new Exception("getRolePages error!");
        }
    }


    //根据角色id获取入廊信息
    @RequestMapping(value = "/roleByRoleId",params = {"roleId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getRoleByRoleId(@RequestParam Integer roleId) throws Exception{
        try {
            return roleService.findRoleByRoleId(roleId).toString();
        }catch (Exception e){
            throw new Exception("getRoleByRoleId error!");
        }
    }


    //统计有多少角色
    @RequestMapping(value = "/roleCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getRoleCount() throws Exception{
        try {
            Integer count = roleService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getRoleCount error!");
        }
    }

    //增加角色的信息
    @RequestMapping(value = "/role", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createRole(@RequestBody String roleInfo) throws Exception{
        JsonObject userString = new JsonParser().parse(roleInfo).getAsJsonObject();
        Role role = Json2Work(userString);
        try {
            roleService.save(role);
            return roleInfo.toString();
        } catch (Exception e) {
            throw new Exception("createRole error!");
        }
    }

    //更新角色信息
    @RequestMapping(value = "/role", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateRole(@RequestBody String roleInfo) throws Exception{
        JsonObject userInfoString = new JsonParser().parse(roleInfo).getAsJsonObject();
        if(userInfoString.get("roleId").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        Role role = new Role();
        role.setRoleId(userInfoString.get("roleId").getAsInt());
        role.setRoleName(userInfoString.get("roleName").getAsString());
        if (userInfoString.get("roleName") != null) {
            role.setRoleName(userInfoString.get("roleName").getAsString());
        }
        try {
            roleService.update(role);
            return role.toString();
        } catch (Exception e) {
            throw new Exception("createRole error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/roleByRoleId",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteRoleByRoleId(@RequestParam Integer roleId){
        try {
            roleService.deleteByRoleId(roleId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的角色信息
    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllRole() throws Exception{
        try {
            return roleService.findAllRole().toString();
        }catch (Exception e){
            throw new Exception("getRoleCount error!");
        }
    }

    private Role Json2Work(JsonObject workString) {
        Role role = new Role();
        role.setRoleId(workString.get("roleId").getAsInt());
        role.setRoleName(workString.get("roleName").getAsString());

        if (workString.get("roleName") != null) {
            role.setRoleName(workString.get("roleName").getAsString());
        }

        return role;
    }
}
