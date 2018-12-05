package com.buptiot.pojo;

import java.util.Date;

/**
 * Created by zyf on 2018/12/5.
 */
public class ReservePlan {

    private Integer id;
    private String planName;
    private Integer userId;
    private String userName;
    private String addDate;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"planName\":\"")
                .append(planName).append('\"');
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"addDate\":\"")
                .append(addDate).append('\"');
        sb.append(",\"state\":")
                .append(state);
        sb.append('}');
        return sb.toString();
    }
}
