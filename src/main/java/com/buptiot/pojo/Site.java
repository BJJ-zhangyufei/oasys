package com.buptiot.pojo;

/**
 * Created by zyf on 2019/1/8.
 */
public class Site {

    private Integer siteId;
    private String siteName;


    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String name) {
        this.siteName = siteName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"siteId\":")
                .append(siteId);
        sb.append(",\"siteName\":\"")
                .append(siteName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
