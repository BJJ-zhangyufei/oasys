package com.buptiot.dao.user;

import com.buptiot.pojo.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2019/1/22.
 */
@Mapper
public interface userRepository {

    @Select("select id as id,name as name,email as email from user where tenant_id = 73 and id!=209 and id != 210 and id != 211 and id != 212 and id != 213 order by id asc limit #{index},#{pageSize}")
    List<user> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select id as id,name as name,email as email from user where name = #{name} limit #{index},#{pageSize}")
    List<user> findAllByName(@Param("name") String name,@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select id as id,name as name,email as email from user  where id = #{id}")
    user findUserById(Integer Id);

    @Select("SELECT userId,name,email FROM user u,group_user gr,chatGroup c  WHERE c.chatGroupId = #{chatGroupId} AND c.chatGroupId=gr.chatGroupId AND gr.userId=u.id;;")
    List<user> findUserByChatGroupId(Integer ChatGroupId);

    @Select("select count(*) from user where tenant_id = 73 and id!=209 and id != 210 and id != 211 and id != 212 and id != 213 ")
    Integer AllWorkCount();

    @Select("select count(*) from user where name = #{name}")
    Integer findCountByName(String name);

    @Insert("insert into user (name,email) values (#{name},#{email})")
    @Options(useGeneratedKeys = true, keyProperty = "Id")
    void save(user user);

    @Update("update user set name = #{name},email = #{email} where id=#{id}")
    void update(user user);

    @Delete("delete from user where id=#{id}")
    void deleteById(Integer id);


    @Select("select id as id,name as name,email as email from user  where tenant_id = 73 and id!=209 and id != 210 and id != 211 and id != 212 and id != 213 and id != #{id}")
    List<user> findAll(Integer Id);

    @Select("select id as id,name as name,email as email from user  where tenant_id = 73 and id!=209 and id != 210 and id != 211 and id != 212 and id != 213 order by id asc")
    List<user> findAllUsers();

}
