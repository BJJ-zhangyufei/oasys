package com.buptiot.controller;

import com.buptiot.dao.TravelDetail.TravelDetailService;
import com.buptiot.pojo.TravelDetail;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/19.
 */
@RestController
@RequestMapping("/api/v1/info")
@CrossOrigin
public class TravelDetailController {
    @Autowired
    TravelDetailService travelDetailService;

    //配合分页设置，获取所有的预案详细信息
    @RequestMapping(value = "/travelDetailByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelDetailByPage(@RequestParam int limit,
                                  @RequestParam int page) throws Exception {
        try {
            return travelDetailService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getTravelDetailByPage error!");
        }
    }

    //获取所有的预案详细信息的页数
    @RequestMapping(value = "/travelDetailPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getTravelDetailPages(@RequestParam int limit) throws Exception {
        try {
            return travelDetailService.findTravelDetailPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getTravelDetailPages error!");
        }
    }


    //根据预案详细信息detailId获取入廊信息
    @RequestMapping(value = "/travelDetailByDetailId",params = {"detailId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelDetailByDetailId(@RequestParam Integer detailId) throws Exception{
        try {
            return travelDetailService.findTravelDetailByDetailId(detailId).toString();
        }catch (Exception e){
            throw new Exception("getTravelDetailByDetailId error!");
        }
    }

    //根据预案详细信息startDate获取入廊信息
    @RequestMapping(value = "/travelDetailByStartDate",params = {"startDate"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelDetailByStartDate(@RequestParam Long startDate) throws Exception{
        try {
            return travelDetailService.findTravelDetailByStartDate(startDate).toString();
        }catch (Exception e){
            throw new Exception("getTravelDetailByStartDate error!");
        }
    }

    //根据预案详细信息endDate获取入廊信息
    @RequestMapping(value = "/travelDetailByEndDate",params = {"endDate"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelDetailByEndDate(@RequestParam Long endDate) throws Exception{
        try {
            return travelDetailService.findTravelDetailByEndDate(endDate).toString();
        }catch (Exception e){
            throw new Exception("getTravelDetailByEndDate error!");
        }
    }

    //统计有多少预案详细信息
    @RequestMapping(value = "/travelDetailCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getTravelDetailCount() throws Exception{
        try {
            Integer count = travelDetailService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getTravelDetailCount error!");
        }
    }

    //增加预案详细信息
    @RequestMapping(value = "/travelDetail", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createTravelDetail(@RequestBody String travelDetailInfo) throws Exception{
        JsonObject travelDetailString = new JsonParser().parse(travelDetailInfo).getAsJsonObject();
        TravelDetail travelDetail = Json2Work(travelDetailString);
        try {
            travelDetailService.save(travelDetail);
            return travelDetail.toString();
        } catch (Exception e) {
            throw new Exception("createTravelDetail error!");
        }
    }

    //更新预案详细信息
    @RequestMapping(value = "/travelDetail", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateTravelDetail(@RequestBody String travelDetailInfo) throws Exception{
        JsonObject travelDetailString = new JsonParser().parse(travelDetailInfo).getAsJsonObject();
        if(travelDetailString.get("detailId").getAsString().equals("")) {
            throw new RuntimeException("没有detailId，无法更新!");
        }
        TravelDetail travelDetail = new TravelDetail();
        travelDetail.setDetailId(travelDetailString.get("detailId").getAsInt());
        travelDetail.setStartDate(travelDetailString.get("startDate").getAsLong());
        travelDetail.setEndDate(travelDetailString.get("endDate").getAsLong());
        travelDetail.setUserId(travelDetailString.get("userId").getAsInt());
//        if (workString.get("reason") != null) {
//            travelDetail.setStartDate(workString.get("startReason").getAsString());
//        }
        try {
            travelDetailService.update(travelDetail);
            return travelDetail.toString();
        } catch (Exception e) {
            throw new Exception("createTravelDetail error!");
        }
    }

    //通过detailId删除信息
    @RequestMapping(value = "/travelDetailByDetailId",params = {"detailId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTravelDetailById(@RequestParam Integer detailId){
        try {
            travelDetailService.deleteByDetailId(detailId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过startDate删除信息
    @RequestMapping(value = "/travelDetailByStartDate",params = {"startDate"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTravelDetailByStartDate(@RequestParam Long startDate){
        try {
            travelDetailService.deleteByStartDate(startDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过endDate删除信息
    @RequestMapping(value = "/travelDetailByEndDate",params = {"endDate"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTravelDetailByEndDate(@RequestParam Long endDate){
        try {
            travelDetailService.deleteByEndDate(endDate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的预案详细信息
    @RequestMapping(value = "/travelDetail", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllTravelDetail() throws Exception{
        try {
            return travelDetailService.findAllTravelDetail().toString();
        }catch (Exception e){
            throw new Exception("getTravelDetailCount error!");
        }
    }

    private TravelDetail Json2Work(JsonObject workString) {
        TravelDetail travelDetail = new TravelDetail();
        travelDetail.setDetailId(workString.get("detailId").getAsInt());
        travelDetail.setStartDate(workString.get("startDate").getAsLong());
        travelDetail.setEndDate(workString.get("endDate").getAsLong());
        travelDetail.setUserId(workString.get("userId").getAsInt());

        return travelDetail;
    }
}

