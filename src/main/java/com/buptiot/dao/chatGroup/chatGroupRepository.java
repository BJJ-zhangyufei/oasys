package com.buptiot.dao.chatGroup;

import com.buptiot.pojo.chatGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by zyf on 2019/1/23.
 */
@Mapper
public interface chatGroupRepository {

    @Select("select chatGroupId as chatGroupId,chatGroupName as chatGroupName,chatGroupInfo as chatGroupInfo from chatGroup where Id>0 limit #{index},#{pageSize}")
    List<chatGroup> findAllByPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

    @Select("select chatGroupId as chatGroupId,chatGroupName as chatGroupName,chatGroupInfo as chatGroupInfo from chatGroup  where chatGroupId = #{Id}")
    chatGroup findGroupById(Integer Id);

    @Select("SELECT c.chatGroupId,chatGroupName,chatGroupInfo FROM chatGroup c,group_user gr,user u WHERE u.id = #{id} AND u.id=gr.userId and gr.chatGroupId = c.chatGroupId;")
    List<chatGroup> findGroupByUserId(Integer userId);

    @Select("SELECT chatGroupId FROM chatGroup  WHERE chatGroupName = #{chatGroupName};")
    chatGroup findGroupIdByName(String chatGroupName);

    @Select("select count(*) from chatGroup")
    Integer AllWorkCount();

    @Insert("insert into chatGroup (chatGroupId,chatGroupName,chatGroupInfo) values (#{chatGroupId},#{chatGroupName},#{chatGroupInfo})")
    @Options(useGeneratedKeys = true, keyProperty = "Id")
    void save(chatGroup chatGroup);

    @Update("update chatGroup set chatGroupName = #{chatGroupName},chatGroupInfo = #{chatGroupInfo} where chatGroupId=#{chatGroupId}")
    void update(chatGroup chatGroup);

    @Delete("delete from chatGroup where chatGroupId=#{id}")
    void deleteById(Integer id);


    @Select("select chatGroupId as chatGroupId,chatGroupName as chatGroupName,chatGroupInfo as chatGroupInfo from chatGroup where chatGroupId>0")
    List<chatGroup> findAll();
}
