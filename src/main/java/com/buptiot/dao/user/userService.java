package com.buptiot.dao.user;


import com.buptiot.pojo.user;

import java.util.List;

/**
 * Created by zyf on 2019/1/22.
 */
public interface userService {

    List<user> findALlByPage(Integer page, Integer pageSize);

    List<user> findALlByName(String name,Integer page, Integer pageSize);

    Integer findUserPageNum(Integer size);

    user findUserById(Integer Id);

    List<user> findUserByChatGroupId(Integer chatGroupId);

    Integer allWorkCount();

    Integer findCountByName(String name);

    void save(user user);

    void update(user user);

    void deleteById(Integer Id);

    List<user> findAllUser(Integer Id);

    List<user> findAllUsers();

}
