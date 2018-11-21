package com.buptiot.dao.Travel;


import com.buptiot.pojo.Travel;
import com.buptiot.pojo.TravelDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */

@Mapper
public interface TravelRepository {

    @Select("select travelId as travelId,Date as Date,Traveler as Traveler,Reason as Reason, UserId as UserId from Travel where travelId>0 limit #{index},#{pageSize}")
    List<Travel> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select travelId as travelId,Date as Date,Traveler as Traveler,Reason as Reason, UserId as UserId from Travel  where travelId = #{travelId}")
    Travel findTravelByTravelId(Integer travelId);

    @Select("select travelId as travelId,Date as Date,Traveler as Traveler,Reason as Reason, UserId as UserId from Travel where Date = #{Date}")
    List<Travel> findTravelByDate(Long Date);

    @Select("select count(*) from Travel")
    Integer AllWorkCount();

    @Insert("insert into Travel (Date,Traveler,Reason,UserId) values (#{Date},#{Traveler},#{Reason},#{UserId})")
    @Options(useGeneratedKeys = true, keyProperty = "travelId")
    void save(Travel travel);

    @Update("update Travel set Date = #{Date},Traveler = #{Traveler},Reason = #{Reason},UserId = #{UserId} where travelId=#{travelId}")
    void update(Travel travel);

    @Delete("delete from Travel where travelId=#{travelId}")
    void deleteByTravelId(Integer travelId);

    @Delete("delete from Travel where Date=#{Date}")
    void deleteByDate(Long Date);

    @Select("select travelId as travelId,Date as Date,Traveler as Traveler,Reason as Reason, UserId as UserId from Travel where travelId > 0")
    List<Travel> findAll();


}
