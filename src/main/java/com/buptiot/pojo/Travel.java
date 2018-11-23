package com.buptiot.pojo;

/**
 * Created by zyf on 2018/11/15.
 */
public class Travel extends IdBased {

    private Integer travelId;
    private Long Date;
    private String Traveler;
    private String Reason;
    private Integer UserId;

    public Integer getTravelId() {
        return travelId;
    }

    public void setTravelId(Integer travelId) {
        this.travelId = travelId;
    }


    public Long getDate() {
        return Date;
    }

    public void setDate(Long Date) {
        this.Date = Date;
    }

    public String getTraveler() {
        return Traveler;
    }

    public void setTraveler(String Traveler) {
        this.Traveler = Traveler;
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
        sb.append(",\"travelId\":\"")
                .append(travelId);
        sb.append(",\"Date\":\"")
                .append(Date).append('\"');
        sb.append(",\"Traveler\":\"")
                .append(Traveler).append('\"');
        sb.append(",\"Reason\":\"")
                .append(Reason).append('\"');
        sb.append(",\"UserId\":\"")
                .append(UserId);
        sb.append('}');
        return sb.toString();
    }
}
