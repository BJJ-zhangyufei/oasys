package com.buptiot.controller;

import com.buptiot.dao.Permission.PermissionService;
import com.buptiot.dao.Role.RoleService;
import com.buptiot.exception.IOTException;
import com.buptiot.pojo.Role;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

/**
 * Created by zyf on 2019/5/21.
 */
@RestController
@RequestMapping("/api/v1/user")
public class PermissionController extends BaseController{

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    //为一个role增加permission
    @RequestMapping(value = "/permission", params = { "role_id"},method = RequestMethod.POST)
    public void saveRolePermissionRelation(@RequestParam int role_id,
                                           @RequestBody String permission_ids) throws IOTException {

        JsonObject PermissionJson = (JsonObject) new JsonParser().parse(permission_ids);
        String[] permissionIds = PermissionJson.get("id").getAsString().split(",");
        for (String permission_id:permissionIds){
            Integer id = Integer.parseInt(permission_id);
            permissionService.saveRolePermissionRelation(role_id,id);
        }
    }

    //删除一个role下的permission
    @RequestMapping(value = "/permission",params = {  "role_id"  }, method = RequestMethod.DELETE)
    public void deleteRolePermissionRelation(@RequestParam int role_id,
                                             @RequestBody String permission_ids) throws IOTException {
        JsonObject PermissionJson = (JsonObject) new JsonParser().parse(permission_ids);
        String[] permissionIds = PermissionJson.get("id").getAsString().split(",");
        for (String permission_id:permissionIds){
            Integer id = Integer.parseInt(permission_id);
            permissionService.deleteARelation(role_id,id);
        }
    }

    //根据ID获取role
    @RequestMapping(value = "/role",params = {  "role_id"  }, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getRole(@RequestParam int role_id) throws IOTException {
        return roleService.findRoleById(role_id).toString();
    }

    //获取所有role
    @RequestMapping(value = "/roles", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getRoles() throws IOTException {
        return roleService.findAllRoles().toString();
    }

    //创建一个role
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Role saveRole(@RequestBody String roleInfo) throws IOTException {
        Role role = JSON.parseObject(roleInfo,Role.class);
        roleService.saveRole(role);
        return role;
    }

    //删除一个role
    @RequestMapping(value = "/role", method = RequestMethod.DELETE)
    public void deleteRole(@RequestParam Integer roleId) throws IOTException {
        roleService.deleteRoleById(roleId);
    }

    //更新一个role
    @RequestMapping(value = "/role", method = RequestMethod.PUT)
    public void updateRole(@RequestBody String roleInfo) throws IOTException {
        try {
            Role role = JSON.parseObject(roleInfo,Role.class);
            roleService.updateRole(role);
        }catch (Exception e){
            handleException(e);
        }
    }

    //获取所有permission
    @RequestMapping(value = "/permissions",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPermissions() throws IOTException {
        return permissionService.findAllPermissions().toString();
    }

    //根据user_id获取拥有的权限
    @RequestMapping(value = "/findAllByUserId", params = { "user_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String findAllByUserId(@RequestParam Integer user_id) throws IOTException {
        return permissionService.findAllByUserId(user_id).toString();
    }

    //根据role_id获取权限id
    @RequestMapping(value = "/findPermissionIdsByRoleId", params = { "role_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String findPermissionIdsByRoleId(@RequestParam Integer role_id) throws IOTException {
        return permissionService.findPermissionIdsByRoleId(role_id).toString();
    }

    //根据role_id获取权限名称
    @RequestMapping(value = "/findPermissionNamesByRoleId", params = { "role_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String findPermissionNamesByRoleId(@RequestParam Integer role_id) throws IOTException {
        return permissionService.findPermissionNamesByRoleId(role_id).toString();
    }

    //获取一个role下分配的permission
    @RequestMapping(value = "/rolePermission", params = {  "role_id"  },method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPermissionsByRoleId(@RequestParam int role_id) throws IOTException {
        return permissionService.findAllPermissionsByRoleId(role_id).toString();
    }

    //获取一个role下还未分配的permission
    @RequestMapping(value = "/roleNotOwnedPermission", params = {  "role_id"  },method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getNotOwnedPermissionsByRoleId(@RequestParam int role_id) throws IOTException {
        return permissionService.findAllNotOwnedPermissionsByRoleId(role_id).toString();
    }

    //为一个user分配role
    @RequestMapping(value = "/user/role", params = { "role_id","user_id"},method = RequestMethod.POST)
    public void saveRoleUserRelation(@RequestParam Integer role_id,
                                     @RequestParam Integer user_id) throws IOTException {
        roleService.saveRoleUserRelation(role_id,user_id);
    }

    //为一个user删除role
    @RequestMapping(value = "/user/role", params = { "role_id","user_id"},method = RequestMethod.DELETE)
    public void deleteRoleUserRelation(@RequestParam Integer role_id,
                                       @RequestParam Integer user_id) throws IOTException {
        roleService.deleteRoleUserRelation(role_id,user_id);
    }

    //通过user_id删除role
    @RequestMapping(value = "/user/deleteRoleByUserId", params = { "user_id"},method = RequestMethod.DELETE)
    public void deleteRoleUserRelationByUserId(@RequestParam Integer user_id) throws IOTException {
        roleService.deleteRoleUserRelationByUserId(user_id);
    }

    //获取一个用户下的所有extra role
    @RequestMapping(value = "/roles", params = { "user_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUserExtraRoles(@RequestParam Integer user_id) throws IOTException {
        return roleService.findExtraRolesByUserId(user_id).toString();
    }

    //获取一个用户下的所有未拥有的extra role
    @RequestMapping(value = "/notOwnedRoles", params = { "user_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUserNotOwnedExtraRoles(@RequestParam Integer user_id) throws IOTException {
        return roleService.findNotOwnedExtraRolesByUserId(user_id).toString();
    }

    //获取一个用户下的所有role
    @RequestMapping(value = "/UserRoles", params = { "user_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String getUserRoles(@RequestParam Integer user_id) throws IOTException {
        return roleService.findAllRolesByUserId(user_id).toString();
    }

    //根据用户user_id获取角色名称
    @RequestMapping(value = "/findRolesNameByUserId", params = { "user_id"}, method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String findRolesNameByUserId(@RequestParam Integer user_id) throws IOTException {
        return roleService.findRolesNameByUserId(user_id).toString();
    }

    //根据权限id获取角色信息
    @RequestMapping(value = "/role/findRoleByPermissionId",params = {"permission_id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findRoleByPermissionId(@RequestParam Integer permission_id) throws Exception{
        try {
            return roleService.findRoleByPermissionId(permission_id).toString();
        }catch (Exception e){
            throw new Exception("findRoleByPermissionId error!");
        }
    }
}

