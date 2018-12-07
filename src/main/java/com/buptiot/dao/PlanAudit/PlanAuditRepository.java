package com.buptiot.dao.PlanAudit;

import com.buptiot.pojo.PlanAudit;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/12/5.
 */
@Mapper
public interface PlanAuditRepository {

    @Select("select Id as Id,planId as planId,userId as userId,userName as userName,auditInfo as auditInfo from PlanAudit where id>0 limit #{index},#{pageSize}")
    List<PlanAudit> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select Id as Id,planId as planId,userId as userId,userName as userName,auditInfo as auditInfo from PlanAudit  where id = #{id}")
    PlanAudit findPlanAuditById(Integer id);

    @Select("select count(*) from PlanAudit")
    Integer AllWorkCount();

    @Insert("insert into PlanAudit(id,planId,userId,userName,auditInfo) values (#{id},#{planId},#{userId},#{userName},#{auditInfo})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(PlanAudit planAudit);

    @Update("update PlanAudit set planId = #{planId},userId = #{userId},userName = #{userName},auditInfo = #{auditInfo} where Id=#{id}")
    void update(PlanAudit planAudit);

    @Delete("delete from PlanAudit where Id=#{id}")
    void deleteById(Integer Id);

    @Select("select Id as Id,planId as planId,userId as userId,userName as userName,auditInfo as auditInfo from PlanAudit  where id>0")
    List<PlanAudit> findAll();
}