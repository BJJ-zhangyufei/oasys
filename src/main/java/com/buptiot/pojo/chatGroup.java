package com.buptiot.pojo;

/**
 * Created by zyf on 2019/1/22.
 */
public class chatGroup {

    private Integer chatGroupId;
    private String chatGroupName;
    private String chatGroupInfo;

    public Integer getChatGroupId() {
        return chatGroupId;
    }

    public void setChatGroupId(Integer chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public void setChatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName;
    }

    public String getChatGroupInfo() {
        return chatGroupInfo;
    }

    public void setChatGroupInfo(String chatGroupInfo) {
        this.chatGroupInfo = chatGroupInfo;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(chatGroupId);
        sb.append(",\"name\":\"")
                .append(chatGroupName).append('\"');
        sb.append(",\"chatGroupInfo\":\"")
                .append(chatGroupInfo).append('\"');
        sb.append('}');
        return sb.toString();
    }
}