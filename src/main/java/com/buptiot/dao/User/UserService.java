package com.buptiot.dao.User;


import com.buptiot.pojo.User;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */
public interface UserService {

    List<User> findALlByPage(Integer page, Integer pageSize);

    Integer findUserPageNum(Integer size);

    User findUserById(Integer Id);

    Integer allWorkCount();

    void save(User user);

    void update(User user);

    void deleteById(Integer Id);

    List<User> findAllUser();
}
