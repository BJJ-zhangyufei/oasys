package com.buptiot.controller;

import com.buptiot.annotation.Auth;
import com.buptiot.dao.ReservePlan.ReservePlanService;
import com.buptiot.pojo.ReservePlan;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/12/5.
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class ReservePlanController {

    @Autowired
    ReservePlanService reservePlanService;

    //配合分页设置，获取所有的预案信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/reservePlanByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getReservePlanByPage(@RequestParam int limit,
                                    @RequestParam int page) throws Exception {
        try {
            return reservePlanService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getReservePlanByPage error!");
        }
    }

    //获取所有的预案的页数
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/reservePlanPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getReservePlanPages(@RequestParam int limit) throws Exception {
        try {
            return reservePlanService.findReservePlanPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getReservePlanPages error!");
        }
    }


    //根据预案id获取预案信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/reservePlanById",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getReservePlanById(@RequestParam Integer Id) throws Exception{
        try {
            return reservePlanService.findReservePlanById(Id).toString();
        }catch (Exception e){
            throw new Exception("getReservePlanById error!");
        }
    }

    //根据user_id获取预案信息
    //@Auth(roles = {"RepairMan","PipeRepairMan"})
    @RequestMapping(value = "/reservePlanByUserId", method = RequestMethod.GET)
    @ResponseBody
    public String getReservePlanByUserId(@RequestParam (name = "user_id")Integer user_id) throws Exception{
        try {
            return reservePlanService.findReservePlanByUserId(user_id).toString();
        }catch (Exception e){
            throw new Exception("getReservePlanByUserId error!");
        }
    }


    //统计有多少预案
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/reservePlanCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getReservePlanCount() throws Exception{
        try {
            Integer count = reservePlanService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getReservePlanCount error!");
        }
    }

    //增加预案的信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor","RepairMan"})
    @RequestMapping(value = "/reservePlan", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createReservePlan(@RequestBody String planInfo) throws Exception{
        JsonObject userString = new JsonParser().parse(planInfo).getAsJsonObject();
        ReservePlan reservePlan = Json2Work(userString);
        try {
            reservePlanService.save(reservePlan);
            return reservePlan.toString();
        } catch (Exception e) {
            throw new Exception("createReservePlan error!");
        }
    }

    //更新预案信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/reservePlan", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateReservePlan(@RequestBody String planInfo) throws Exception{
        JsonObject planInfoString = new JsonParser().parse(planInfo).getAsJsonObject();
        if(planInfoString.get("id").getAsString().equals("")) {
            throw new RuntimeException("没有id，无法更新!");
        }
        ReservePlan reservePlan = new ReservePlan();
        reservePlan.setId(planInfoString.get("id").getAsInt());
        reservePlan.setPlanName(planInfoString.get("planName").getAsString());
        reservePlan.setUserId(planInfoString.get("userId").getAsInt());
        reservePlan.setUserName(planInfoString.get("userName").getAsString());
        reservePlan.setAddDate(planInfoString.get("addDate").getAsString());
        reservePlan.setState(planInfoString.get("state").getAsInt());
        if (planInfoString.get("planName") != null) {
            reservePlan.setPlanName(planInfoString.get("planName").getAsString());
        }
        try {
            reservePlanService.update(reservePlan);
            return reservePlan.toString();
        } catch (Exception e) {
            throw new Exception("createReservePlan error!");
        }
    }

    //通过Id删除信息
    @Auth(roles = {"BranchDispatcher"})
    @RequestMapping(value = "/reservePlanById",params = {"id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteReservePlanById(@RequestParam Integer id){
        try {
            reservePlanService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //获取所有的未审批的预案信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/noReservePlan", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getNoReservePlan() throws Exception{
        try {
            return reservePlanService.findNoReservePlan().toString();
        }catch (Exception e){
            throw new Exception("getReservePlanCount error!");
        }
    }


    //获取所有的已审批的预案信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor","RepairMan"})
    @RequestMapping(value = "/alreadyReservePlan", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAlreadyReservePlan() throws Exception{
        try {
            return reservePlanService.findAlreadyReservePlan().toString();
        }catch (Exception e){
            throw new Exception("getReservePlanCount error!");
        }
    }

    //获取所有的下级无权限审批的预案信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/cantReservePlan", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String findCantReservePlan() throws Exception{
        try {
            return reservePlanService.findCantReservePlan().toString();
        }catch (Exception e){
            throw new Exception("findCantReservePlan error!");
        }
    }


    //审批通过
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/agreeReservePlan", params = {"id"},method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void agreeReservePlan(@RequestParam Integer id) throws Exception{
        try {
            reservePlanService.agree(id);
           // return reservePlan.toString();
        } catch (Exception e) {
            throw new Exception("agreeReservePlan error!");
        }
    }


    //审批不通过
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/disagreeReservePlan", params = {"id"},method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void disagreeReservePlan(@RequestParam Integer id) throws Exception{
        try {
            reservePlanService.disagree(id);
            // return reservePlan.toString();
        } catch (Exception e) {
            throw new Exception("agreeReservePlan error!");
        }
    }

    //交给上级审批
    @Auth(roles = {"RepairMan"})
    @RequestMapping(value = "/nextBoss", params = {"id"},method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void nextBoss(@RequestParam Integer id) throws Exception{
        try {
            reservePlanService.nextBoss(id);
            // return reservePlan.toString();
        } catch (Exception e) {
            throw new Exception("nextBoss error!");
        }
    }

    //根据预案id获取用户信息
    @Auth(roles = {"BranchDispatcher","BranchMonitor"})
    @RequestMapping(value = "/userInfoByPlanId",params = {"id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserInfoByPlanId(@RequestParam Integer id) throws Exception{
        try {
            return reservePlanService.findUserInfoByPlanId(id).toString();
        }catch (Exception e){
            throw new Exception("getUserInfoByPlanId error!");
        }
    }



    private ReservePlan Json2Work(JsonObject workString) {
        ReservePlan reservePlan = new ReservePlan();
        reservePlan.setId(workString.get("id").getAsInt());
        reservePlan.setPlanName(workString.get("planName").getAsString());
        reservePlan.setUserId(workString.get("userId").getAsInt());
        reservePlan.setUserName(workString.get("userName").getAsString());
        reservePlan.setAddDate(workString.get("addDate").getAsString());
        reservePlan.setState(workString.get("state").getAsInt());

        if (workString.get("planName") != null) {
            reservePlan.setPlanName(workString.get("planName").getAsString());
        }

        return reservePlan;
    }
}
