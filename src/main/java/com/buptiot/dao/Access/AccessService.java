package com.buptiot.dao.Access;


import com.buptiot.pojo.Access;

import java.util.List;

/**
 * Created by zyf on 2018/11/30.
 */
public interface AccessService {

    List<Access> findALlByPage(Integer page, Integer pageSize);

    Integer findAccessPageNum(Integer size);

    Access findAccessByAccessId(Integer accessId);

    Integer allWorkCount();

    void save(Access access);

    void update(Access access);

    void deleteByAccessId(Integer accessId);

    List<Access> findAllAccess();

    List<Access> findAccessByUserId(Integer Id);

    List<Access> findAccessByRoleId(Integer roleId);

}
