package com.buptiot.dao.UserInfo;

import com.buptiot.dao.UserInfo.UserInfoRepository;
import com.buptiot.dao.UserInfo.UserInfoService;
import com.buptiot.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/11/28.
 */

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return userInfoRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findUserInfoPageNum(Integer size) {
        log.trace("Executing findUserInfoPageNum [{}]", size);
        Integer num = (userInfoRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public UserInfo findUserInfoById(Integer Id) {
        log.trace("Executing findUserInfoById [{}]", Id);
        return userInfoRepository.findUserInfoById(Id);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = userInfoRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(UserInfo userInfo) {
        log.trace("Executing save [{}]");
        userInfoRepository.save(userInfo);
    }

    @Override
    public void update(UserInfo userInfo) {
        log.trace("Executing update [{}]");
        userInfoRepository.update(userInfo);
    }

    @Override
    public void deleteById(Integer Id) {
        log.trace("Executing deleteById [{}]",Id);
        userInfoRepository.deleteById(Id);
    }

    @Override
    public List<UserInfo> findAllUserInfo() {
        log.trace("Executing findAllWork [{}]");
        return userInfoRepository.findAll();
    }

}
