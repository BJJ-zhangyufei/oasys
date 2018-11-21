package com.buptiot.dao.User;


import com.buptiot.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/15.
 */
@Mapper
public interface UserRepository {

    @Select("select Id as Id,UserName as UserName,LoginName as LoginName,LoginPwd as LoginPwd, Role as Role from user where Id>0 limit #{index},#{pageSize}")
    List<User> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select Id as Id,UserName as UserName,LoginName as LoginName,LoginPwd as LoginPwd, Role as Role from user  where Id = #{Id}")
    User findUserById(Integer Id);

    @Select("select count(*) from user")
    Integer AllWorkCount();

    @Insert("insert into user (UserName,LoginName,LoginPwd,Role) values (#{UserName},#{LoginName},#{LoginPwd},#{Role})")
    @Options(useGeneratedKeys = true, keyProperty = "Id")
    void save(User user);

    @Update("update user set UserName = #{UserName},LoginName = #{LoginName},LoginPwd = #{LoginPwd},Role = #{Role} where Id=#{Id}")
    void update(User user);

    @Delete("delete from user where Id=#{Id}")
    void deleteById(Integer Id);


    @Select("select Id as Id,UserName as UserName,LoginName as LoginName,LoginPwd as LoginPwd, Role as Role from user  where Id > 0")
    List<User> findAll();
}
