package com.buptiot.pojo;

/**
 * Created by zyf on 2018/11/28.
 */
public class UserInfo {

    private Integer Id;
    private String UserName;
    private String Position;
    private String Department;
    private String Gender;
    private Integer Age;
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

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
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
        sb.append(",\"UserName\":\"")
                .append(UserName).append('\"');
        sb.append(",\"Position\":\"")
                .append(Position).append('\"');
        sb.append(",\"Department\":\"")
                .append(Department).append('\"');
        sb.append(",\"Gender\":\"")
                .append(Gender).append('\"');
        sb.append(",\"Age\":")
                .append(Age);
        sb.append(",\"Role\":")
                .append(Role);
        sb.append('}');
        return sb.toString();
    }

}
