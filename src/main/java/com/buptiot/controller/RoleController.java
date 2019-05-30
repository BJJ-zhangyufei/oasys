package com.buptiot.controller;

import com.buptiot.annotation.Auth;
import com.buptiot.dao.Role.RoleService;
import com.buptiot.exception.IOTException;
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
@RequestMapping("/api/v1/user")
@CrossOrigin
public class RoleController {

    @Autowired
    RoleService roleService;

    //获取所有的角色信息
    @Auth(roles = {"GeneralDispatcher","GeneralMonitor"})
    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllRole() throws Exception{
        try {
            return roleService.findAllRoles().toString();
        }catch (Exception e){
                throw new Exception("getAllRoles error!");
        }
    }

    //根据用户id获取信息
    @RequestMapping(value = "/allRolesByUserId",params = {"user_id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findAllRolesByUserId(@RequestParam Integer user_id) throws Exception{
        try {
            return roleService.findAllRolesByUserId(user_id).toString();
        }catch (Exception e){
            throw new Exception("findAllRolesByUserId error!");
        }
    }


    //通过Id删除信息
    @RequestMapping(value = "/deleteRoleById",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteRoleById(@RequestParam Integer id){
        try {
            roleService.deleteRoleById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //根据角色id获取信息
    @RequestMapping(value = "/findRoleById",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findRoleById(@RequestParam Integer id) throws Exception{
        try {
            return roleService.findRoleById(id).toString();
        }catch (Exception e){
            throw new Exception("findRoleById error!");
        }
    }

    //为一个user分配role
    @RequestMapping(value = "/role/user", params = { "user_id"},method = RequestMethod.POST)
    public void saveUserToRole(@RequestParam Integer user_id) throws IOTException {
        roleService.saveUserToRole(user_id);
    }

//    //配合分页设置，获取所有的角色信息
//    @RequestMapping(value = "/roleByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//    @ResponseBody
//    public String getRoleByPage(@RequestParam int limit,
//                                    @RequestParam int page) throws Exception {
//        try {
//            return roleService.findALlByPage(page,limit).toString();
//        } catch (Exception e) {
//            throw new Exception("getRoleByPage error!");
//        }
//    }
//
//    //获取所有的角色的页数
//    @RequestMapping(value = "/rolePages", params = {  "limit"  }, method = RequestMethod.GET)
//    @ResponseBody
//    public Integer getRolePages(@RequestParam int limit) throws Exception {
//        try {
//            return roleService.findRolePageNum(limit);
//        } catch (Exception e) {
//            throw new Exception("getRolePages error!");
//        }
//    }

    //根据权限id获取角色信息
    @RequestMapping(value = "/findRoleByPermissionId",params = {"permission_id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findRoleByPermissionId(@RequestParam Integer permission_id) throws Exception{
        try {
            return roleService.findRoleByPermissionId(permission_id).toString();
        }catch (Exception e){
            throw new Exception("findRoleByPermissionId error!");
        }
    }

}
