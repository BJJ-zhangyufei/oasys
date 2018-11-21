package com.buptiot.pojo;

/**
 * Created by zyf on 2018/11/15.
 */
public class User extends IdBased {

    private Integer Id;
    private String UserName;
    private String LoginName;
    private String LoginPwd;
    private Integer Role;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserAName) {
        this.UserName = UserAName;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String LoginName) {
        this.LoginName = LoginName;
    }

    public String getLoginPwd() {
        return LoginPwd;
    }

    public void setLoginPwd(String LoginPwd) {
        this.LoginPwd = LoginPwd;
    }

    public Integer getRole() {
        return Role;
    }

    public void setRole(Integer Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"Id\":")
                .append(Id);
        sb.append(",\"UserName\":")
                .append(UserName);
        sb.append(",\"LoginName\":")
                .append(LoginName);
        sb.append(",\"LoginPwd\":\"")
                .append(LoginPwd).append('\"');
        sb.append(",\"Role\":\"")
                .append(Role).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
