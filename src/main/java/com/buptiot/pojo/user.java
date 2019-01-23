package com.buptiot.pojo;

/**
 * Created by zyf on 2019/1/22.
 */
public class user {

    private Integer userId;
    private String name;
    private String email;
//    private String Department;
//    private String Gender;
//    private Integer Age;
//    private Integer Role;

    public Integer getId() {
        return userId;
    }

    public void setId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getDepartment() {
//        return Department;
//    }
//
//    public void setDepartment(String Department) {
//        this.Department = Department;
//    }
//
//    public String getGender() {
//        return Gender;
//    }
//
//    public void setGender(String Gender) {
//        this.Gender = Gender;
//    }
//
//    public Integer getAge() {
//        return Age;
//    }
//
//    public void setAge(Integer Age) {
//        this.Age = Age;
//    }
//
//    public Integer getRole() {
//        return Role;
//    }
//
//    public void setRole(Integer Role) {
//        this.Role = Role;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(userId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append('}');
        return sb.toString();
    }

}
