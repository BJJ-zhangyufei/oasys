package com.buptiot.dao.PlanAudit;

import com.buptiot.dao.PlanAudit.PlanAuditRepository;
import com.buptiot.dao.PlanAudit.PlanAuditService;
import com.buptiot.pojo.PlanAudit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
@Slf4j
@Service
public class PlanAuditServiceImpl implements PlanAuditService {

    @Autowired
    PlanAuditRepository planAuditRepository;

    @Override
    public List<PlanAudit> findALlByPage(Integer page, Integer pageSize) {
        log.trace("Executing findALlByPage [{}]", page,pageSize);
        return planAuditRepository.findAllByPage(page,pageSize);
    }

    @Override
    public Integer findPlanAuditPageNum(Integer size) {
        log.trace("Executing findPlanAuditPageNum [{}]", size);
        Integer num = (planAuditRepository.AllWorkCount()+size-1)/size;
        return num;
    }

    @Override
    public PlanAudit findPlanAuditById(Integer id) {
        log.trace("Executing findPlanAuditById [{}]", id);
        return planAuditRepository.findPlanAuditById(id);
    }


    @Override
    public Integer allWorkCount() {
        log.trace("Executing allWorkCount [{}]");
        Integer count = planAuditRepository.AllWorkCount();
        return count;
    }

    @Override
    public void save(PlanAudit planAudit) {
        log.trace("Executing save [{}]");
        planAuditRepository.save(planAudit);
    }

    @Override
    public void update(PlanAudit planAudit) {
        log.trace("Executing update [{}]");
        planAuditRepository.update(planAudit);
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("Executing deleteById [{}]",id);
        planAuditRepository.deleteById(id);
    }

    @Override
    public List<PlanAudit> findAllPlanAudit() {
        log.trace("Executing findAllPlanAudit [{}]");
        return planAuditRepository.findAll();
    }
}
