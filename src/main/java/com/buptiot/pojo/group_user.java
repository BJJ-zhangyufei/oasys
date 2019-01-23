package com.buptiot.pojo;

/**
 * Created by zyf on 2019/1/22.
 */
public class group_user {

    private Integer groupId;
    private Integer userId;


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"groupId\":")
                .append(groupId);
        sb.append(",\"userId\":\"")
                .append(userId);
        sb.append('}');
        return sb.toString();
    }
}

