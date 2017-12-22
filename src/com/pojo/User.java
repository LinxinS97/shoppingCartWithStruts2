package com.pojo;

import javax.persistence.*;

@Entity
@IdClass(UserPK.class)
public class User {
    private int userId;
    private String userName;
    private String teleNum;
    private String password;
    private String userImg;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Id
    @Column(name = "teleNum")
    public String getTeleNum() {
        return teleNum;
    }

    public void setTeleNum(String teleNum) {
        this.teleNum = teleNum;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "userImg")
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (teleNum != null ? !teleNum.equals(user.teleNum) : user.teleNum != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (userImg != null ? !userImg.equals(user.userImg) : user.userImg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (teleNum != null ? teleNum.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userImg != null ? userImg.hashCode() : 0);
        return result;
    }
}
