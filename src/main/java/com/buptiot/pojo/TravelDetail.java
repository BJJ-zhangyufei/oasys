package com.buptiot.pojo;

/**
 * Created by zyf on 2018/11/15.
 */
public class TravelDetail extends IdBased {

    private Integer detailId;
    private Long startDate;
    private Long endDate;
    private Integer UserId;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }


    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
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
        sb.append("\"detailId\":")
                .append(detailId).append('\"');
        sb.append(",\"startDate\":\"")
                .append(startDate).append('\"');
        sb.append(",\"endDate\":\"")
                .append(endDate).append('\"');
        sb.append(",\"UserId\":\"")
                .append(UserId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
