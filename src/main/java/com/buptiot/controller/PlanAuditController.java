package com.buptiot.controller;

import com.buptiot.dao.PlanAudit.PlanAuditService;
import com.buptiot.pojo.PlanAudit;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/12/6.
 */
@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class PlanAuditController {

    @Autowired
    PlanAuditService planAuditService;

    //配合分页设置，获取所有的审批信息
    @RequestMapping(value = "/planAuditByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanAuditByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return planAuditService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getPlanAuditByPage error!");
        }
    }

    //获取所有的审批的页数
    @RequestMapping(value = "/planAuditPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getPlanAuditPages(@RequestParam int limit) throws Exception {
        try {
            return planAuditService.findPlanAuditPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getPlanAuditPages error!");
        }
    }


    //根据id获取审批信息
    @RequestMapping(value = "/planAuditById",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPlanAuditById(@RequestParam Integer id) throws Exception{
        try {
            return planAuditService.findPlanAuditById(id).toString();
        }catch (Exception e){
            throw new Exception("getPlanAuditById error!");
        }
    }


    //统计有多少审批信息
    @RequestMapping(value = "/planAuditCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getPlanAuditCount() throws Exception{
        try {
            Integer count = planAuditService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getPlanAuditCount error!");
        }
    }

    //增加审批信息
    @RequestMapping(value = "/planAudit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createPlanAudit(@RequestBody String planInfo) throws Exception{
        JsonObject planString = new JsonParser().parse(planInfo).getAsJsonObject();
        PlanAudit planAudit = Json2Work(planString);
        try {
            planAuditService.save(planAudit);
            return planAudit.toString();
        } catch (Exception e) {
            throw new Exception("createPlanInfo error!");
        }
    }

    //更新审批信息
    @RequestMapping(value = "/planAudit", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updatePlanAudit(@RequestBody String planInfo) throws Exception{
        JsonObject planInfoString = new JsonParser().parse(planInfo).getAsJsonObject();
        if(planInfoString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有id，无法更新!");
        }
        PlanAudit planAudit = new PlanAudit();
        planAudit.setId(planInfoString.get("id").getAsInt());
        planAudit.setPlanId(planInfoString.get("planId").getAsInt());
        planAudit.setUserId(planInfoString.get("userId").getAsInt());
        planAudit.setUserName(planInfoString.get("userName").getAsString());
        planAudit.setAuditInfo(planInfoString.get("auditInfo").getAsString());
        if (planInfoString.get("userName") != null) {
            planAudit.setUserName(planInfoString.get("userName").getAsString());
        }
        try {
            planAuditService.update(planAudit);
            return planAudit.toString();
        } catch (Exception e) {
            throw new Exception("createPlanAudit error!");
        }
    }

    //通过Id删除信息
    @RequestMapping(value = "/planAuditById",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deletePlanAuditById(@RequestParam Integer id){
        try {
            planAuditService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的审批信息
    @RequestMapping(value = "/planAudit", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllPlanAudit() throws Exception{
        try {
            return planAuditService.findAllPlanAudit().toString();
        }catch (Exception e){
            throw new Exception("getPlanAuditCount error!");
        }
    }


    private PlanAudit Json2Work(JsonObject planInfoString) {
        PlanAudit planAudit = new PlanAudit();
        planAudit.setId(planInfoString.get("id").getAsInt());
        planAudit.setPlanId(planInfoString.get("planId").getAsInt());
        planAudit.setUserId(planInfoString.get("userId").getAsInt());
        planAudit.setUserName(planInfoString.get("userName").getAsString());
        planAudit.setAuditInfo(planInfoString.get("auditInfo").getAsString());

        return planAudit;
    }
}
