package com.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserPK implements Serializable {
    private int userId;
    private String teleNum;

    @Column(name = "userId")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "teleNum")
    @Id
    public String getTeleNum() {
        return teleNum;
    }

    public void setTeleNum(String teleNum) {
        this.teleNum = teleNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPK userPK = (UserPK) o;

        if (userId != userPK.userId) return false;
        if (teleNum != null ? !teleNum.equals(userPK.teleNum) : userPK.teleNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (teleNum != null ? teleNum.hashCode() : 0);
        return result;
    }
}
