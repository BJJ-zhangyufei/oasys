package com.buptiot.dao.user;

import com.buptiot.dao.user.userRepository;
import com.buptiot.dao.user.userService;
import com.buptiot.pojo.user;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2019/1/22.
 */

@Slf4j
@Service
public class userServiceImpl implements userService {

    @Autowired
    userRepository userRepository;

    @Override
    public List<user> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return userRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findUserPageNum(Integer size) {
        log.trace("Executing findUserPageNum [{}]", size);
        Integer num = (userRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public user findUserById(Integer Id) {
        log.trace("Executing findUserById [{}]", Id);
        return userRepository.findUserById(Id);
    }

    @Override
    public List<user> findUserByChatGroupId(Integer Id) {
        log.trace("Executing findUserByGroupId [{}]", Id);
        return userRepository.findUserByChatGroupId(Id);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = userRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(user user) {
        log.trace("Executing save [{}]");
        userRepository.save(user);
    }

    @Override
    public void update(user user) {
        log.trace("Executing update [{}]");
        userRepository.update(user);
    }

    @Override
    public void deleteById(Integer Id) {
        log.trace("Executing deleteById [{}]",Id);
        userRepository.deleteById(Id);
    }

    @Override
    public List<user> findAllUser(Integer Id) {
        log.trace("Executing findAllUser [{}]");
        return userRepository.findAll(Id);
    }

}
