package com.buptiot.pojo;

/**
 * Created by zyf on 2018/11/29.
 */
public class Role {

    private Integer roleId;
    private String roleName;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"roleId\":")
                .append(roleId);
        sb.append(",\"roleName\":\"")
                .append(roleName).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
