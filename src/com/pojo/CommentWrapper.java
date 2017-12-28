package com.pojo;

public class CommentWrapper {

    public CommentWrapper() {
    }

    public CommentWrapper(User user, UserComment comment) {
        this.user = user;
        this.comment = comment;
    }

    private User user;
    private UserComment comment;

    public UserComment getComment() {
        return comment;
    }

    public void setComment(UserComment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
