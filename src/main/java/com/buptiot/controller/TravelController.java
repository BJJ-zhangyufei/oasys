package com.buptiot.controller;


import com.buptiot.dao.Travel.TravelService;
import com.buptiot.pojo.Travel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/16.
 */
@RestController
@RequestMapping("/api/v1/info")
public class TravelController {
    @Autowired
    TravelService travelService;

    //配合分页设置，获取所有的预案信息
    @RequestMapping(value = "/travelByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelByPage(@RequestParam int limit,
                                   @RequestParam int page) throws Exception {
        try {
            return travelService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getTravelByPage error!");
        }
    }

    //获取所有的预案信息的页数
    @RequestMapping(value = "/travelPages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getTravelPages(@RequestParam int limit) throws Exception {
        try {
            return travelService.findTravelPageNum(limit);
        } catch (Exception e) {
            throw new Exception("getTravelPages error!");
        }
    }


    //根据预案信息travelId获取入廊信息
    @RequestMapping(value = "/travelByTravelId",params = {"travelId"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelByTravelId(@RequestParam Integer travelId) throws Exception{
        try {
            return travelService.findTravelByTravelId(travelId).toString();
        }catch (Exception e){
            throw new Exception("getTravelByTravelId error!");
        }
    }

    //根据预案Date获取入廊信息
    @RequestMapping(value = "/travelByDate",params = {"Date"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getTravelByDate(@RequestParam Long Date) throws Exception{
        try {
            return travelService.findTravelByDate(Date).toString();
        }catch (Exception e){
            throw new Exception("getTravelByDate error!");
        }
    }

    //统计有多少预案信息
    @RequestMapping(value = "/travelCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getTravelCount() throws Exception{
        try {
            Integer count = travelService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getTravelCount error!");
        }
    }

    //增加预案的信息
    @RequestMapping(value = "/travel", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createTravel(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        Travel travel = Json2Work(workString);
        try {
            travelService.save(travel);
            return travel.toString();
        } catch (Exception e) {
            throw new Exception("createTravel error!");
        }
    }

    //更新预案信息
    @RequestMapping(value = "/travel", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateTravel(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        if(workString.get("travelId").getAsString().equals("")) {
            throw new RuntimeException("没有travelId，无法更新!");
        }
        Travel travel = new Travel();
        travel.setTravelId(workString.get("travelId").getAsInt());
        travel.setDate(workString.get("date").getAsLong());
        travel.setTraveler(workString.get("traveler").getAsString());
        travel.setReason(workString.get("reason").getAsString());
        travel.setUserId(workString.get("userId").getAsInt());
        if (workString.get("reason") != null) {
            travel.setReason(workString.get("reason").getAsString());
        }
        try {
            travelService.update(travel);
            return travel.toString();
        } catch (Exception e) {
            throw new Exception("createTravel error!");
        }
    }

    //通过travelId删除预案信息
    @RequestMapping(value = "/travelByTravelId",params = {"travelId"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTravelByTravelId(@RequestParam Integer travelId){
        try {
            travelService.deleteByTravelId(travelId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过Date删除预案信息
    @RequestMapping(value = "/travelByDate",params = {"date"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTravelByDate(@RequestParam Long Date){
        try {
            travelService.deleteByDate(Date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的预案信息
    @RequestMapping(value = "/travel", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllTravel() throws Exception{
        try {
            return travelService.findAllTravel().toString();
        }catch (Exception e){
            throw new Exception("getTravel error!");
        }
    }

    private Travel Json2Work(JsonObject workString) {
        Travel travel = new Travel();
        travel.setTravelId(workString.get("travelId").getAsInt());
        travel.setDate(workString.get("date").getAsLong());
        travel.setTraveler(workString.get("traveler").getAsString());
        travel.setReason(workString.get("reason").getAsString());
        travel.setUserId(workString.get("userId").getAsInt());

        if (workString.get("reason") != null) {
            travel.setReason(workString.get("reason").getAsString());
        }

        return travel;
    }
}

