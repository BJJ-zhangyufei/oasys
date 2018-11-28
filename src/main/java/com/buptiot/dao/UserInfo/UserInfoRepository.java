package com.buptiot.dao.UserInfo;

import com.buptiot.pojo.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2018/11/28.
 */
@Mapper
public interface UserInfoRepository {

    @Select("select Id as Id,UserName as UserName,Position as Position,Department as Department,Gender as Gender,Age as Age,Role as Role from UserInfo where Id>0 limit #{index},#{pageSize}")
    List<UserInfo> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select Id as Id,UserName as UserName,Position as Position,Department as Department,Gender as Gender,Age as Age,Role as Role from UserInfo  where Id = #{Id}")
    UserInfo findUserInfoById(Integer Id);

    @Select("select count(*) from UserInfo")
    Integer AllWorkCount();

    @Insert("insert into UserInfo (UserName,Position,Department,Gender,Age,Role) values (#{UserName},#{Position},#{Department},#{Gender},#{Age},#{Role})")
    @Options(useGeneratedKeys = true, keyProperty = "Id")
    void save(UserInfo userInfo);

    @Update("update UserInfo set UserName = #{UserName},Position = #{Position},Department = #{Department},Gender = #{Gender},Age = #{Age},Role = #{Role} where Id=#{Id}")
    void update(UserInfo userInfo);

    @Delete("delete from UserInfo where Id=#{Id}")
    void deleteById(Integer Id);


    @Select("select Id as Id,UserName as UserName,Position as Position,Department as Department,Gender as Gender,Age as Age, Role as Role from UserInfo  where Id > 0")
    List<UserInfo> findAll();
}
