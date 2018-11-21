package com.buptiot.dao.User;

import com.buptiot.dao.User.UserRepository;
import com.buptiot.dao.User.UserService;
import com.buptiot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return userRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findUserPageNum(Integer size) {
        log.trace("Executing findTravelPageNum [{}]", size);
        Integer num = (userRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public User findUserById(Integer Id) {
        log.trace("Executing findTravelById [{}]", Id);
        return userRepository.findUserById(Id);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = userRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(User user) {
        log.trace("Executing save [{}]");
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        log.trace("Executing update [{}]");
        userRepository.update(user);
    }

    @Override
    public void deleteById(Integer Id) {
        log.trace("Executing deleteById [{}]",Id);
        userRepository.deleteById(Id);
    }

    @Override
    public List<User> findAllUser() {
        log.trace("Executing findAllWork [{}]");
        return userRepository.findAll();
    }



}
