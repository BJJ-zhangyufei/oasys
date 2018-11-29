package com.buptiot.dao.Role;


import com.buptiot.pojo.Role;

import java.util.List;

/**
 * Created by zyf on 2018/11/29.
 */
public interface RoleService {


    List<Role> findALlByPage(Integer page, Integer pageSize);

    Integer findRolePageNum(Integer size);

    Role findRoleByRoleId(Integer roleId);

    Integer allWorkCount();

    void save(Role role);

    void update(Role role);

    void deleteByRoleId(Integer roleId);

    List<Role> findAllRole();
}
