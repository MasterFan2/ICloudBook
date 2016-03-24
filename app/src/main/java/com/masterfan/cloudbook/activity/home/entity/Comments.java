package com.masterfan.cloudbook.activity.home.entity;

import com.masterfan.cloudbook.http.bean.BaseEntity;

/**
 * 图书评论列表
 * Created by sunzj on 2016/3/22.
 */
public class Comments extends BaseEntity {

    private String bookInfo;
    private int commend;
    private String comment;
    private String createtime;
    private String school;
    private String userBookCommentCommends;
    private UserInfo userInfo;

    public Comments() {
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public int getCommend() {
        return commend;
    }

    public void setCommend(int commend) {
        this.commend = commend;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUserBookCommentCommends() {
        return userBookCommentCommends;
    }

    public void setUserBookCommentCommends(String userBookCommentCommends) {
        this.userBookCommentCommends = userBookCommentCommends;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "bookInfo='" + bookInfo + '\'' +
                ", commend=" + commend +
                ", comment='" + comment + '\'' +
                ", createtime='" + createtime + '\'' +
                ", school='" + school + '\'' +
                ", userBookCommentCommends='" + userBookCommentCommends + '\'' +
                ", userInfo=" + userInfo.toString() +
                '}';
    }
}
