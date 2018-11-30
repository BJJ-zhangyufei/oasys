package com.buptiot.pojo;

/**
 * Created by zyf on 2018/11/30.
 */
public class Access {

    private Integer accessId;
    private String accessName;


    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"accessId\":")
                .append(accessId);
        sb.append(",\"accessName\":\"")
                .append(accessName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
