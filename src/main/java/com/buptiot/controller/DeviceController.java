package com.buptiot.controller;

import com.buptiot.utils.HttpUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zyf on 2018/12/26.
 */
@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
@Slf4j
public class DeviceController extends DefaultIOTAwaredController {

    public static final String DEVICE_ID = "deviceId";

    //@ApiOperation(value="获取租户所有设备的数量", notes="获取租户所有设备的数量")
    @RequestMapping(value = "/tenant/devicesCount", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevicesCount(){

        String requestAddr = "/api/v1/deviceaccess/tenant/deviceCount?tenantId="  + getTenantId();

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(responseContent) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

    }

    //@ApiOperation(value="获取客户所有设备的数量", notes="获取客户所有设备的数量")
    @RequestMapping(value = "/customer/devicesCount", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCustomerDevicesCount(){

        String requestAddr = "/api/v1/deviceaccess/customer/deviceCount?tenantId="+getTenantId()+"&customerId="+ getCustomerId();

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(responseContent) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

    }

    //@ApiOperation(value="获取租户所查询设备的数量", notes="获取租户所查询设备的数量")
    @RequestMapping(value = "/tenant/searchCount", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevicesSearchCount(@RequestParam String textSearch){

        String requestAddr = "/api/v1/deviceaccess/tenant/devices/SearchCount/"+getTenantId()+"?textSearch="  + textSearch;

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(responseContent) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

    }

    //@ApiOperation(value="获取客户所查询设备的数量", notes="获取客户所查询设备的数量")
    @RequestMapping(value = "/customer/searchCount", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCustomerDevicesSearchCount(@RequestParam String textSearch){

        String requestAddr = "/api/v1/deviceaccess/customerdevices/SearchCount/"+getTenantId()+"/"  + getCustomerId() + "?textSearch="  + textSearch;

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(responseContent) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

    }


    /**
     * 获取租户所有设备的信息
     * @return
     */

    //创建设备
    //@ApiImplicitParam(name="deviceInfo", value = "设备信息JSON", required = true, paramType = "body")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String createDevice(@RequestBody String deviceInfo) {
        String requestAddr = "/api/v1/deviceaccess/device" ;

        /**
         * 这里的deviceInfo为json
         * {"name":"test0name","type":"default","additionalInfo":{"description":"jhdajd"}}
         */
        JsonObject deviceInfoJson = (JsonObject)new JsonParser().parse(deviceInfo);
        if(deviceInfoJson.get("lifeTime").getAsString().equals("NaN")){
            deviceInfoJson.add("lifeTime",null);
        }
        if(deviceInfoJson.has("parentDeviceId") && deviceInfoJson.get("parentDeviceId").getAsString().equals("undefined")){
            deviceInfoJson.remove("parentDeviceId");
            deviceInfoJson.addProperty("parentDeviceId","");
        }

        deviceInfoJson.addProperty("tenantId", getTenantId());

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendPostToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    deviceInfoJson,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        return retSuccess(responseContent) ;
    }



    //删除设备
   // @ApiOperation(value = "删除设备", notes = "根据deviceId删除设备")
    //@ApiImplicitParam(name="deviceId", value = "设备ID", required = true, paramType = "path", dataType = "String")
    @RequestMapping(value = "/delete/{deviceId}", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String delete(@PathVariable(DEVICE_ID) String strDeviceId) {
        String requestAddr ="http://"+ getDeviceAccessServer() +String.format("/api/v1/deviceaccess/device/%s", strDeviceId);
        try{
            String responseContent = HttpUtil.sendDeletToThingsboard(requestAddr,request.getSession());
            return retSuccess(responseContent) ;
        }catch(Exception e){
            return retFail(e.toString()) ;
        }
    }




    //更新设备
    //@ApiImplicitParam(name = "deviceId", value = "设备ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/updatedevice", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateDeviceCoordinate(@RequestBody String json) {

        JsonObject deviceInfoJson = (JsonObject)new JsonParser().parse(json);
        deviceInfoJson.addProperty("tenantId", getTenantId());
        String requestAddr = "/api/v1/deviceaccess/device" ;
        String responseContent = null ;
        try{
            responseContent = HttpUtil.sendPostToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    deviceInfoJson,
                    request.getSession());
        }catch(Exception e){
            return retFail(e.toString()) ;
        }
        return retSuccess(responseContent);
    }



    //通过设备ID获取设备名字
    @RequestMapping(value = "/name/{deviceId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String finddeviceName(@PathVariable(DEVICE_ID) String strDeviceId) {

        String requestAddr ="http://"+ getDeviceAccessServer() +String.format("/api/v1/deviceaccess/device/%s", strDeviceId);
        try{
            String responseContent = HttpUtil.sendGetToThingsboard(requestAddr,
                    null,
                    request.getSession());
            JsonObject jsonObject = (JsonObject)new JsonParser().parse(responseContent);
            String parentname = jsonObject.get("name").toString();

            return retSuccess(parentname) ;
        }catch(Exception e){
            return null ;
        }
    }


    //获取所有设备，其中limit为必要参数
   // @ApiOperation(value="获取租户所有设备的信息", notes="获取租户所有设备的信息")
    @RequestMapping(value = "/alldevices",  method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevices(@RequestParam int limit,
                             @RequestParam(required = false) String textSearch,
                             @RequestParam(required = false) String idOffset,
                             @RequestParam(required = false) String textOffset) {

        String requestAddr = "/api/v1/deviceaccess/tenant/devices/"  + getTenantId() +"?limit=" + limit;
        if(textSearch != null){
            requestAddr = requestAddr + "&textSearch=" + textSearch;
        }
        if(idOffset != null){
            requestAddr = requestAddr + "&idOffset=" + idOffset;
        }
        if(textOffset != null){
            requestAddr = requestAddr + "&textOffset=" + textOffset;
        }

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(decodeArray(responseContent)) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

    }

    //删除某个tenant下所有设备
    // @ApiOperation(value="删除租户所有设备的信息", notes="删除租户所有设备的信息")
    @RequestMapping(value = "/deleteAllDevices", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteDevices(@PathVariable("tenantId") Integer tenantId) throws Exception{

        String requestAddr = "/api/v1/deviceaccess/devices/"  + tenantId;

        try{
            String responseContent = HttpUtil.sendDeletToThingsboard(requestAddr,request.getSession());
            return retSuccess(responseContent) ;
        }catch(Exception e){
            return retFail(e.toString()) ;
        }

    }

    //通过设备ID获取其下的设备
    // @ApiOperation(value = "得到deviceId设备的设备信息", notes = "通过deviceId获取设备信息")
    @RequestMapping(value = "/devices", params = {"deviceId"}, method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevicesById(@RequestParam String deviceId) {

        String requestAddr = "http://" + getDeviceAccessServer() + "/api/v1/deviceaccess/device/"+deviceId ;

        String responseContent = null ;
        try{
            responseContent = HttpUtil.sendGetToThingsboard(requestAddr,
                    null,
                    request.getSession());
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(decodeArray(responseContent)) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }
    }

    //通过设备名获取其下的设备
    // @ApiOperation(value = "通过设备名字获取设备信息", notes = "通过设备名字获取设备信息")
    @RequestMapping(value = "/devicesByName",  params = {"name"},method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevicesByName(@RequestParam String name) throws Exception{

        String requestAddr = "/api/v1/deviceaccess/device/" +  getTenantId() + "/" + name;

        String responseContent = null ;
        try{
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession());
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(decodeArray(responseContent)) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }
    }

    //根据站点Id获取设备，其中limit为必要参数
    // @ApiOperation(value="根据站点Id获取设备的信息", notes="根据站点Id获取设备的信息")
    @RequestMapping(value = "/devicesBySiteId", params = {"siteId"},method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevicesBySiteId(
            @RequestParam Integer siteId,
            @RequestParam int limit,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String idOffset,
            @RequestParam(required = false) String textOffset) throws Exception{

        String requestAddr = "/api/v1/deviceaccess/sitedevices/" + getTenantId()+ "/" + siteId +"?limit=" + limit;
        if(textSearch != null){
            requestAddr = requestAddr + "&textSearch=" + textSearch;
        }
        if(idOffset != null){
            requestAddr = requestAddr + "&idOffset=" + idOffset;
        }
        if(textOffset != null){
            requestAddr = requestAddr + "&textOffset=" + textOffset;
        }

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(decodeArray(responseContent)) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

    }

    //通过父设备ID获取其下的设备
   // @ApiOperation(value = "得到parentId设备的设备信息", notes = "得到parentId设备的设备信息")
    //@ApiImplicitParam(name = "parentDeviceId", value = "父设备ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "/parentDevices/{parentDeviceId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getParentDevices(@PathVariable String parentDeviceId, @RequestParam int limit,
                                   @RequestParam(required = false) String textSearch,
                                   @RequestParam(required = false) String idOffset,
                                   @RequestParam(required = false) String textOffset) {

        String requestAddr = "http://" + getDeviceAccessServer() + "/api/v1/deviceaccess/parentdevices/"+parentDeviceId + "?limit=" + limit;
        if(textSearch != null){
            requestAddr = requestAddr + "&textSearch=" + textSearch;
        }
        if(idOffset != null){
            requestAddr = requestAddr + "&idOffset=" + idOffset;
        }
        if(textOffset != null){
            requestAddr = requestAddr + "&textOffset=" + textOffset;
        }

        String responseContent = null ;
        try{
            responseContent = HttpUtil.sendGetToThingsboard(requestAddr,
                    null,
                    request.getSession());
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }

        try {
            return retSuccess(decodeArray(responseContent)) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }
    }


    //获取设备token
    @RequestMapping(value = "/token/{deviceId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDeviceToken(@PathVariable String deviceId) {
        String requestAddr = "/api/v1/deviceaccess/credentialbyid/" + deviceId ;
        String responseContent = null ;
        try{
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession());
        }catch(Exception e){
            return retFail(e.toString()) ;
        }
        // token = (JsonObject)DeviceTokenInfoDecode.deviceToken(responseContent);
        return retSuccess(responseContent);
    }

    //获取设备状态信息
    @RequestMapping(value = "/status", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getDevicesStatus(@RequestBody String Dids) {
        String requestAddr = "/api/v1/deviceaccess/device/status/" + getTenantId() ;
        JsonObject deviceid = (JsonObject)new JsonParser().parse(Dids);
        String responseContent = null ;
        try{
            responseContent = HttpUtil.sendPostToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    deviceid,
                    request.getSession());
        }catch(Exception e){
            return retFail(e.toString()) ;
        }
        return retSuccess(responseContent);
    }


    //以下是客户层面的设备操作
    //分配设备给客户
    @RequestMapping(value = "/assign/customer/{deviceId}/{customerId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String assignDeviceToCustomer(@PathVariable("deviceId") String dId, @PathVariable("customerId") Integer cId){
        String requestAddr = String.format("/api/v1/deviceaccess/assign/customer/%s/%s", dId, cId);
        String responseContent = null;

        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }
        return retSuccess(responseContent) ;
    }

    //取消分配给客户的某个设备
    @RequestMapping(value = "/unassign/customer/{deviceId}", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String unAssignDeviceFromCustomer(@PathVariable("deviceId") String dId) {
        String requestAddr = String.format("/api/v1/deviceaccess/unassign/customer/%s", dId);

        String responseContent = null ;
        try {
            responseContent = HttpUtil.sendDeletToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }
        return retSuccess(responseContent) ;
    }

    //取消分配客户下的所有设备
    @RequestMapping(value = "/unassign/customerDevices/{customerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public String unAssignCustomerDevices(@PathVariable("customerId") String cId){
        String requestAddr = "/api/v1/deviceaccess/unassign/" + getTenantId() + "/" + cId;
        String responseContent = null;
        try {
            responseContent = HttpUtil.sendDeletToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    request.getSession()) ;
        } catch (Exception e) {
            return retFail(e.toString()) ;
        }
        return retSuccess(responseContent) ;
    }

    //获取客户的所有设备
    @RequestMapping(value = "/customerDevices/{customerId}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCustomerDevices(@PathVariable("customerId") Integer cId,
                                     @RequestParam int limit,
                                     @RequestParam(required = false) String textSearch,
                                     @RequestParam(required = false) String idOffset,
                                     @RequestParam(required = false) String textOffset) {

        String requestAddr = "/api/v1/deviceaccess/customerdevices/" + getTenantId() + "/" + cId
                + "?limit=" + limit;
        if (textSearch != null) {
            requestAddr = requestAddr + "&textSearch=" + textSearch;
        }
        if (idOffset != null) {
            requestAddr = requestAddr + "&idOffset=" + idOffset;
        }
        if (textOffset != null) {
            requestAddr = requestAddr + "&textOffset=" + textOffset;
        }

        String responseContent = null;
        try {
            responseContent = HttpUtil.sendGetToThingsboard("http://" + getDeviceAccessServer() + requestAddr,
                    null,
                    request.getSession());
        } catch (Exception e) {
            return retFail(e.toString());
        }
        try {
            return retSuccess(decodeArray(responseContent));
        } catch (Exception e) {
            return retFail(e.toString());
        }
    }



    //增加显示警报的方法
    public String decodeArray(String res){
        JsonObject jsonObject = (JsonObject)new JsonParser().parse(res);
        JsonArray array = jsonObject.getAsJsonArray("data");
        for(JsonElement je : array){
            JsonObject jo = je.getAsJsonObject();
            try {
                if (jo.get("lifeTime").getAsLong() == 0) {
                    continue;
                }
                //判断时间间隔是否小于6个月大于1个月
                if (((jo.get("lifeTime").getAsLong() - System.currentTimeMillis()) / 1000 < 15552000) && ((jo.get("lifeTime").getAsLong() - System.currentTimeMillis()) / 1000 > 2678400)) {
                    jo.addProperty("alarm", "orange");

                }
                //小于一个月
                if ((jo.get("lifeTime").getAsLong() - System.currentTimeMillis()) / 1000 < 2678400) {
                    jo.addProperty("alarm", "red");
                }
            }catch (Exception e){
                continue;
            }
        }
        return array.toString();
    }


}
