package com.buptiot.dao.Role;

import com.buptiot.dao.Role.RoleRepository;
import com.buptiot.dao.Role.RoleService;
import com.buptiot.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/11/29.
 */

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return roleRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findRolePageNum(Integer size) {
        log.trace("Executing findTravelPageNum [{}]", size);
        Integer num = (roleRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public Role findRoleByRoleId(Integer roleId) {
        log.trace("Executing findRoleByRoleId [{}]", roleId);
        return roleRepository.findRoleByRoleId(roleId);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = roleRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(Role role) {
        log.trace("Executing save [{}]");
        roleRepository.save(role);
    }

    @Override
    public void update(Role role) {
        log.trace("Executing update [{}]");
        roleRepository.update(role);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        log.trace("Executing deleteByRoleId [{}]",roleId);
        roleRepository.deleteByRoleId(roleId);
    }

    @Override
    public List<Role> findAllRole() {
        log.trace("Executing findAllWork [{}]");
        return roleRepository.findAll();
    }
}
