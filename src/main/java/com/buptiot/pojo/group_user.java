package com.buptiot.pojo;

/**
 * Created by zyf on 2019/1/22.
 */
public class group_user {

    private Integer chatGroupId;
    private Integer userId;


    public Integer getGroupId() {
        return chatGroupId;
    }

    public void setGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
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
        sb.append("\"chatGroupId\":")
                .append(chatGroupId);
        sb.append(",\"userId\":\"")
                .append(userId);
        sb.append('}');
        return sb.toString();
    }
}

