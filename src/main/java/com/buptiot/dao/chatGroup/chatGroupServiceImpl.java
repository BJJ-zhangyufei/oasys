package com.buptiot.dao.chatGroup;


import com.buptiot.dao.chatGroup.chatGroupService;
import com.buptiot.pojo.chatGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2019/1/23.
 */

@Slf4j
@Service
public class chatGroupServiceImpl implements chatGroupService {

    @Autowired
    com.buptiot.dao.chatGroup.chatGroupRepository chatGroupRepository;

    @Override
    public List<chatGroup> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return chatGroupRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findGroupPageNum(Integer size) {
        log.trace("Executing findGroupPageNum [{}]", size);
        Integer num = (chatGroupRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public chatGroup findGroupById(Integer Id) {
        log.trace("Executing findGroupById [{}]", Id);
        return chatGroupRepository.findGroupById(Id);
    }

    @Override
    public List<chatGroup> findGroupByUserId(Integer userId) {
        log.trace("Executing findGroupByUserId [{}]", userId);
        return chatGroupRepository.findGroupByUserId(userId);
    }

    @Override
    public chatGroup findGroupIdByName(String chatGroupName) {
        log.trace("Executing findGroupIdByName [{}]", chatGroupName);
        return chatGroupRepository.findGroupIdByName(chatGroupName);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = chatGroupRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(chatGroup chatGroup) {
        log.trace("Executing save [{}]");
        chatGroupRepository.save(chatGroup);
    }

    @Override
    public void update(chatGroup chatGroup) {
        log.trace("Executing update [{}]");
        chatGroupRepository.update(chatGroup);
    }

    @Override
    public void deleteById(Integer Id) {
        log.trace("Executing deleteById [{}]",Id);
        chatGroupRepository.deleteById(Id);
    }

    @Override
    public List<chatGroup> findAllGroup() {
        log.trace("Executing findAllUser [{}]");
        return chatGroupRepository.findAll();
    }
}
