package com.masterfan.cloudbook.activity.manamgment.entity;

import java.io.Serializable;

/**
 * 用户阅读
 * Created by Administrator on 2016/1/29 0029.
 */
public class UserRead implements Serializable {
    private String userName;
    private String userClass;
    private String ReadTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getReadTime() {
        return ReadTime;
    }

    public void setReadTime(String readTime) {
        ReadTime = readTime;
    }

    public UserRead(String userName, String userClass, String readTime) {
        this.userName = userName;
        this.userClass = userClass;
        ReadTime = readTime;
    }

    public UserRead() {
    }
}
