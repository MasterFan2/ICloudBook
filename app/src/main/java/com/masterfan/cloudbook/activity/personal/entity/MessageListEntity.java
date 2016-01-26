package com.masterfan.cloudbook.activity.personal.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/24 0024.
 */
public class MessageListEntity implements Serializable{
    private String imgUrL;
    private String userName;
    private String content;
    private String time;
    private int number;

    public String getImgUrL() {
        return imgUrL;
    }

    public void setImgUrL(String imgUrL) {
        this.imgUrL = imgUrL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MessageListEntity(String imgUrL, String userName, String content, String time, int number) {
        this.imgUrL = imgUrL;
        this.userName = userName;
        this.content = content;
        this.time = time;
        this.number = number;
    }

    public MessageListEntity(String userName, String content, String time, int number) {
        this.userName = userName;
        this.content = content;
        this.time = time;
        this.number = number;
    }

    public MessageListEntity() {
        super();
    }
}
