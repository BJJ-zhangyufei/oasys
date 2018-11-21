package com.buptiot.controller;

import com.buptiot.dao.CarFare.CarFareService;
import com.buptiot.pojo.CarFare;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/11/15.
 */

@RestController
@RequestMapping("/api/v1/info")
public class CarFareController {
    @Autowired
    CarFareService carFareService;

    //配合分页设置，获取所有的预案信息
    @RequestMapping(value = "/carFareByPage", params = {  "limit","page"  }, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCarFareByPage(@RequestParam int limit,
                                        @RequestParam int page) throws Exception {
        try {
            return carFareService.findALlByPage(page,limit).toString();
        } catch (Exception e) {
            throw new Exception("getCarFareByPage error!");
        }
    }

    //获取所有的预案信息的页数
    @RequestMapping(value = "/carFarePages", params = {  "limit"  }, method = RequestMethod.GET)
    @ResponseBody
    public Integer getCarFarePages(@RequestParam int limit) throws Exception {
        try {
            return carFareService.findCarFarePageNum(limit);
        } catch (Exception e) {
            throw new Exception("getCarFarePages error!");
        }
    }


    //根据预案信息Id获取预案信息
    @RequestMapping(value = "/carFareById",params = {"Id"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCarFareById(@RequestParam Integer Id) throws Exception{
        try {
            return carFareService.findCarFareById(Id).toString();
        }catch (Exception e){
            throw new Exception("getCarFareById error!");
        }
    }

    //根据预案信息Date获取预案信息
    @RequestMapping(value = "/carFareByDate",params = {"Date"}, method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCarFareByDate(@RequestParam Long Date) throws Exception{
        try {
            return carFareService.findCarFareByDate(Date).toString();
        }catch (Exception e){
            throw new Exception("getCarFareByDate error!");
        }
    }

    //统计有多少预案
    @RequestMapping(value = "/carFareCount", method = RequestMethod.GET)
    @ResponseBody
    public Integer getCarFareCount() throws Exception{
        try {
            Integer count = carFareService.allWorkCount();
            return count;
        }catch (Exception e){
            throw new Exception("getCarFareCount error!");
        }
    }

    //增加预案的信息
    @RequestMapping(value = "/carFare", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String createCarFare(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        CarFare carFare = Json2Work(workString);
        try {
            carFareService.save(carFare);
            return carFare.toString();
        } catch (Exception e) {
            throw new Exception("createCarFare error!");
        }
    }

    //更新预案信息
    @RequestMapping(value = "/carFare", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateCarFare(@RequestBody String workInfo) throws Exception{
        JsonObject workString = new JsonParser().parse(workInfo).getAsJsonObject();
        if(workString.get("Id").getAsString().equals("")) {
            throw new RuntimeException("没有Id，无法更新!");
        }
        CarFare carFare = new CarFare();
        carFare.setId(workString.get("id").getAsInt());
        carFare.setDate(workString.get("date").getAsLong());
        carFare.setReason(workString.get("reason").getAsString());
        carFare.setUserId(workString.get("userId").getAsInt());
        if (workString.get("reason") != null) {
            carFare.setReason(workString.get("reason").getAsString());
        }
        try {
            carFareService.update(carFare);
            return carFare.toString();
        } catch (Exception e) {
            throw new Exception("createCarFare error!");
        }
    }

    //通过Id删除预案信息
    @RequestMapping(value = "/carFareById",params = {"Id"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCarFareById(@RequestParam Integer Id){
        try {
            carFareService.deleteById(Id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //通过Date删除预案信息
    @RequestMapping(value = "/carFareByDate",params = {"date"},method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCarFareByDate(@RequestParam Long Date){
        try {
            carFareService.deleteByDate(Date);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取所有的预案信息
    @RequestMapping(value = "/carFare", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAllCarFare() throws Exception{
        try {
            return carFareService.findAllCarFare().toString();
        }catch (Exception e){
            throw new Exception("getCarFareCount error!");
        }
    }

    private CarFare Json2Work(JsonObject workString) {
        CarFare carFare = new CarFare();
        carFare.setId(workString.get("id").getAsInt());
        carFare.setDate(workString.get("date").getAsLong());
        carFare.setReason(workString.get("reason").getAsString());
        carFare.setUserId(workString.get("userId").getAsInt());

        if (workString.get("reason") != null) {
            carFare.setReason(workString.get("reason").getAsString());
        }

        return carFare;
    }
}
