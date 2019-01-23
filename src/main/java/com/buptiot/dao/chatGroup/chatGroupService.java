package com.buptiot.dao.chatGroup;

import com.buptiot.pojo.chatGroup;

import java.util.List;

/**
 * Created by zyf on 2019/1/23.
 */
public interface chatGroupService {

    List<chatGroup> findALlByPage(Integer page, Integer pageSize);

    Integer findGroupPageNum(Integer size);

    chatGroup findGroupById(Integer Id);

    List<chatGroup> findGroupByUserId(Integer userId);

    chatGroup findGroupIdByName(String chatGroupName);

    Integer allWorkCount();

    void save(chatGroup chatGroup);

    void update(chatGroup chatGroup);

    void deleteById(Integer Id);

    List<chatGroup> findAllGroup();
}
