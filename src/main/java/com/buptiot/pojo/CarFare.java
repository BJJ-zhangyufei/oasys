package com.buptiot.pojo;

import java.util.Date;

/**
 * Created by zyf on 2018/11/15.
 */
public class CarFare extends IdBased{

    private Integer Id;
    private Long Date;
    private String Reason;
    private Integer UserId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = Id;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long Date) {
        this.Date = Date;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"Date\":\"")
                .append(Date).append('\"');
        sb.append(",\"Reason\":\"")
                .append(Reason).append('\"');
        sb.append(",\"UserId\":\"")
                .append(UserId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
