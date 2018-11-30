package com.buptiot.dao.Access;

import com.buptiot.dao.Access.AccessRepository;
import com.buptiot.dao.Access.AccessService;
import com.buptiot.pojo.Access;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/11/30.
 */
@Slf4j
@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    AccessRepository accessRepository;

    @Override
    public List<Access> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return accessRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findAccessPageNum(Integer size) {
        log.trace("Executing findAccessPageNum [{}]", size);
        Integer num = (accessRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public Access findAccessByAccessId(Integer accessId) {
        log.trace("Executing findAccessByAccessId [{}]", accessId);
        return accessRepository.findAccessByAccessId(accessId);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = accessRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(Access access) {
        log.trace("Executing save [{}]");
        accessRepository.save(access);
    }

    @Override
    public void update(Access access) {
        log.trace("Executing update [{}]");
        accessRepository.update(access);
    }

    @Override
    public void deleteByAccessId(Integer accessId) {
        log.trace("Executing deleteByAccessId [{}]",accessId);
        accessRepository.deleteByAccessId(accessId);
    }

    @Override
    public List<Access> findAllAccess() {
        log.trace("Executing findAllWork [{}]");
        return accessRepository.findAll();
    }

    @Override
    public List<Access> findAccessByUserId(Integer Id) {
        log.trace("Executing findAccessByUserId [{}]", Id);
        return accessRepository.findAccessByUserId(Id);
    }

    @Override
    public List<Access> findAccessByRoleId(Integer roleId) {
        log.trace("Executing findRoleByAccessId [{}]", roleId);
        return accessRepository.findAccessByRoleId(roleId);
    }
}
