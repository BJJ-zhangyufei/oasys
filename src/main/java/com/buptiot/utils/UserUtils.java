package com.buptiot.utils;

import com.buptiot.dao.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by zyf on 2019/5/28.
 */
@Component
public class UserUtils {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected RoleService roleService;
    private static UserUtils userUtils;

    @PostConstruct
    public void init(){
        userUtils = this;
        userUtils.roleService = this.roleService;
        userUtils.request = this.request;
    }

    public Set<String> getUserRole(){
        String user_id = userUtils.request.getParameter("user_id");
        int id = Integer.parseInt(user_id);
        Set<String> currentRoles = userUtils.roleService.findRolesNameByUserId(id);
        return currentRoles;
        //return "GeneralDispatcher";
    }

    public Set<String> getDescriptionByUserId(){
        String user_id = userUtils.request.getParameter("user_id");
        int id = Integer.parseInt(user_id);
        Set<String> descriptions = userUtils.roleService.findDescriptionByUserId(id);
        return descriptions;
        //return "总中心调度员";
    }
}
