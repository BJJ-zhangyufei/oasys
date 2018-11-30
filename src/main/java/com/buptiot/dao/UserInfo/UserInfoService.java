package com.buptiot.dao.UserInfo;

import com.buptiot.pojo.UserInfo;

import java.util.List;

/**
 * Created by zyf on 2018/11/28.
 */
public interface UserInfoService {

    List<UserInfo> findALlByPage(Integer page, Integer pageSize);

    Integer findUserInfoPageNum(Integer size);

    UserInfo findUserInfoById(Integer Id);

    Integer allWorkCount();

    void save(UserInfo userInfo);

    void update(UserInfo userInfo);

    void deleteById(Integer Id);

    List<UserInfo> findAllUserInfo();

    List<UserInfo> findUserInfoByRoleId(Integer roleId);

    List<UserInfo> findUserInfoByAccessId(Integer accessId);
}
