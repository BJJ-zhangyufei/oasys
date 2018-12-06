package com.buptiot.dao.PlanAudit;


import com.buptiot.pojo.PlanAudit;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
public interface PlanAuditService {

    List<PlanAudit> findALlByPage(Integer page, Integer pageSize);

    Integer findPlanAuditPageNum(Integer size);

    PlanAudit findPlanAuditById(Integer id);

    Integer allWorkCount();

    void save(PlanAudit planAudit);

    void update(PlanAudit planAudit);

    void deleteById(Integer id);

    List<PlanAudit> findAllPlanAudit();

}
