package com.buptiot.dao.ReservePlan;

import com.buptiot.pojo.ReservePlan;
import com.buptiot.pojo.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
@Mapper
public interface ReservePlanRepository {

    @Select("select Id as Id,planName as planName,userId as userId,userName as userName,addDate as addDate,state as state from ReservePlan where id>0 limit #{index},#{pageSize}")
    List<ReservePlan> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select Id as Id,planName as planName,userId as userId,userName as userName,addDate as addDate,state as state from ReservePlan where id = #{id}")
    ReservePlan findReservePlanById(Integer id);

    @Select("select Id as Id,planName as planName,userId as userId,userName as userName,addDate as addDate,state as state from ReservePlan where userId = #{user_id}")
    ReservePlan findReservePlanByUserId(Integer user_id);

    @Select("select count(*) from ReservePlan")
    Integer AllWorkCount();

    @Insert("insert into ReservePlan(id,planName,userId,userName,addDate,state) values (#{id},#{planName},#{userId},#{userName},#{addDate},0)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(ReservePlan reservePlan);

    @Update("update ReservePlan set planName = #{planName},userId = #{userId},userName = #{userName},addDate = #{addDate},state = #{state} where Id=#{id}")
    void update(ReservePlan reservePlan);

    @Delete("delete from ReservePlan where Id=#{id}")
    void deleteById(Integer id);

    @Select("select Id as Id,planName as planName,userId as userId,userName as userName,addDate as addDate,state as state from ReservePlan  where state = 0")
    List<ReservePlan> findNo();

    @Select("select Id as Id,planName as planName,userId as userId,userName as userName,addDate as addDate,state as state from ReservePlan  where state = 1 or state = 2")
    List<ReservePlan> findAlready();

    @Select("select Id as Id,planName as planName,userId as userId,userName as userName,addDate as addDate,state as state from ReservePlan  where state = 3")
    List<ReservePlan> findCantReservePlan();

    @Update("update ReservePlan set state = 1 where Id=#{id}")
    void agree(Integer id);

    @Update("update ReservePlan set state = 2 where Id=#{id}")
    void disagree(Integer id);

    @Update("update ReservePlan set state = 3 where Id=#{id}")
    void nextBoss(Integer id);

    @Select("SELECT u.id,u.name,u.email FROM user u,ReservePlan r,plan_user pr WHERE r.id = #{id} AND  r.id = pr.planId AND pr.userId = u.id;")
    user findUserInfoByPlanId(Integer id);
}
