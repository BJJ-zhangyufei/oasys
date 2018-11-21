package com.buptiot.dao.CarFare;

import com.buptiot.pojo.CarFare;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */

@Mapper
public interface CarFareRepository {

    @Select("select Id as Id,Date as Date,Reason as Reason,UserId as UserId from CarFare where Id>0 limit #{index},#{pageSize}")
    List<CarFare> findAllByPage(@Param("index") Integer index, @Param("pageSize")Integer pageSize);

    @Select("select Id as Id,Date as Date,Reason as Reason,UserId as UserId  from CarFare where Id = #{Id}")
    CarFare findCarFareById(Integer Id);

    @Select("select Id as Id,Date as Date,Reason as Reason,UserId as UserId  from CarFare where Date = #{Date}")
    List<CarFare> findCarFareByDate(Long Date);

    @Select("select count(*) from CarFare")
    Integer AllWorkCount();

    @Insert("insert into CarFare (Date,Reason,UserId) values (#{Date},#{Reason},#{UserId})")
    @Options(useGeneratedKeys = true,keyProperty="Id")
    void save(CarFare carFare);

    @Update("update CarFare set Date = #{Date},Reason = #{Reason},UserId = #{UserId} where Id=#{Id}")
    void update(CarFare carFare);

    @Delete("delete from CarFare where Id=#{Id}")
    void deleteById(Integer Id);

    @Delete("delete from CarFare where Date=#{Date}")
    void deleteByDate(Long Date);

    @Select("select Id as Id,Date as Date,Reason as Reason,UserId as UserId from CarFare where Id > 0")
    List<CarFare> findAll();
}
