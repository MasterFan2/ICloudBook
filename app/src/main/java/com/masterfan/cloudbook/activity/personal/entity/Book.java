package com.masterfan.cloudbook.activity.personal.entity;

import java.io.Serializable;

/**
 * 图书
 * Created by szj on 2016/1/22.
 */
public class Book implements Serializable {
    private String bookName;//书名
    private String writer;//作者
    private String introduction;//介绍
    private String bookType;//书箱类型
    private int status;//审核类型

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Book() {
        super();
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Book(String bookName, String writer, String introduction, String bookType, int status) {
        this.bookName = bookName;
        this.writer = writer;
        this.introduction = introduction;
        this.bookType = bookType;
        this.status = status;
    }
}
