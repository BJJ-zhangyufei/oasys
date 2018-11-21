package com.buptiot.dao.TravelDetail;

import com.buptiot.pojo.TravelDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */
@Mapper
public interface TravelDetailRepository {

    @Select("select detailId as detailId,startDate as startDate,endDate as endDate,UserId as UserId from TravelDetail where detailId>0 limit #{index},#{pageSize}")
    List<TravelDetail> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select detailId as detailId,startDate as startDate,endDate as endDate,UserId as UserId from TravelDetail  where detailId = #{detailId}")
    TravelDetail findTravelDetailByDetailId(Integer detailId);

    @Select("select detailId as detailId,startDate as startDate,endDate as endDate,UserId as UserId from TravelDetail where startDate = #{startDate}")
    List<TravelDetail> findTravelDetailByStartDate(Long startDate);

    @Select("select detailId as detailId,startDate as startDate,endDate as endDate,UserId as UserId from TravelDetail where endDate = #{endDate}")
    List<TravelDetail> findTravelDetailByEndDate(Long endDate);

    @Select("select count(*) from TravelDetail")
    Integer AllWorkCount();

    @Insert("insert into TravelDetail (startDate,endDate,UserId) values (#{startDate},#{endDate},#{UserId})")
    @Options(useGeneratedKeys = true, keyProperty = "detailId")
    void save(TravelDetail travelDetail);

    @Update("update TravelDetail set startDate = #{startDate},endDate = #{endDate},UserId = #{UserId} where detailId=#{detailId}")
    void update(TravelDetail travelDetail);

    @Delete("delete from TravelDetail where detailId=#{detailId}")
    void deleteByDetailId(Integer detailId);

    @Delete("delete from TravelDetail where startDate=#{startDate}")
    void deleteByStartDate(Long startDate);

    @Delete("delete from TravelDetail where endDate=#{endDate}")
    void deleteByEndDate(Long endDate);

    @Select("select detailId as detailId,startDate as startDate,endDate as endDate,UserId as UserId from TravelDetail where detailId > 0")
    List<TravelDetail> findAll();
}
