package com.buptiot.pojo;

/**
 * Created by zyf on 2018/12/5.
 */
public class PlanAudit {

    private Integer id;
    private Integer planId;
    private Integer userId;
    private String userName;
    private String auditInfo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"Id\":")
                .append(id);
        sb.append(",\"planId\":")
                .append(planId);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"auditInfo\":\"")
                .append(auditInfo).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
