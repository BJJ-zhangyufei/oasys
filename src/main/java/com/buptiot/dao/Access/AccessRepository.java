package com.buptiot.dao.Access;

import com.buptiot.pojo.Access;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/30.
 */

@Mapper
public interface AccessRepository {

    @Select("select accessId as accessId,accessName as accessName from Access where accessId>0 limit #{index},#{pageSize}")
    List<Access> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select accessId as accessId,accessName as accessName from Access  where accessId = #{accessId}")
    Access findAccessByAccessId(Integer accessId);

    @Select("select count(*) from Access")
    Integer AllWorkCount();

    @Insert("insert into Access (accessName) values (#{accessName})")
    @Options(useGeneratedKeys = true, keyProperty = "accessId")
    void save(Access access);

    @Update("update Access set accessName = #{accessName} where accessId=#{accessId}")
    void update(Access access);

    @Delete("delete from Access where accessId=#{accessId}")
    void deleteByAccessId(Integer accessId);


    @Select("select accessId as accessId,accessName as accessName from Access where accessId > 0")
    List<Access> findAll();

    @Select("SELECT * FROM Access a,role_access ra,Role r WHERE r.roleId=ra.roleId AND ra.accessId=a.accessId AND  r.roleId IN (SELECT r.roleId FROM UserInfo u,Role r,user_role ur WHERE u.Id =#{Id} AND u.Id=ur.userId AND ur.roleId=r.roleId);")
    List<Access> findAccessByUserId(Integer Id);

    @Select("SELECT * FROM Access a,role_access ra,Role r WHERE r.roleId = #{roleId} AND r.roleId=ra.roleId AND ra.accessId=a.accessId; ")
    List<Access> findAccessByRoleId(Integer RoleId);
}
