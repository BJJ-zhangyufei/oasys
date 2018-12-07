package com.buptiot.dao.Role;

import com.buptiot.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/29.
 */

@Mapper
public interface RoleRepository {

    @Select("select roleId as roleId,roleName as roleName from Role where roleId>0 limit #{index},#{pageSize}")
    List<Role> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select roleId as roleId,roleName as roleName from Role  where roleId = #{roleId}")
    Role findRoleByRoleId(Integer roleId);

    @Select("select count(*) from Role")
    Integer AllWorkCount();

    @Insert("insert into Role (roleId,roleName) values (#{roleId},#{roleName})")
    @Options(useGeneratedKeys = true, keyProperty = "roleId")
    void save(Role role);

    @Update("update Role set roleName = #{roleName} where roleId=#{roleId}")
    void update(Role role);

    @Delete("delete from Role where roleId=#{roleId}")
    void deleteByRoleId(Integer roleId);


    @Select("select roleId as roleId,roleName as roleName from Role  where roleId > 0")
    List<Role> findAll();

    @Select("SELECT * FROM UserInfo u,Role r,user_role ur WHERE u.Id = #{Id} AND u.Id=ur.userId AND ur.roleId=r.roleId;")
    List<Role> findRoleByUserId(Integer Id);

    @Select("SELECT * FROM Access a,role_access ra,Role r WHERE a.accessId = #{accessId} AND a.accessId=ra.accessId AND ra.roleId=r.roleId; ")
    List<Role> findRoleByAccessId(Integer accessId);
}
