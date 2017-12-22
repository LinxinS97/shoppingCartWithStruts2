package com.pojo;

import javax.persistence.*;

@Entity
@IdClass(UserCommentPK.class)
public class UserComment {
    private int userId;
    private int itemId;
    private int apprise;
    private String comment;

    @Id
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "itemId")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "apprise")
    public int getApprise() {
        return apprise;
    }

    public void setApprise(int apprise) {
        this.apprise = apprise;
    }

    @Basic
    @Column(name = "comment", insertable = false)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserComment that = (UserComment) o;

        if (userId != that.userId) return false;
        if (itemId != that.itemId) return false;
        if (apprise != that.apprise) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + itemId;
        result = 31 * result + apprise;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
