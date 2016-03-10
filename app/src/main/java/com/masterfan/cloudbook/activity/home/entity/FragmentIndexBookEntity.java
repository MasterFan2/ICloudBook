package com.masterfan.cloudbook.activity.home.entity;

import java.io.Serializable;

/**
 * 首页图书数据
 * Created by sunzj on 2016/2/25.
 */
public class FragmentIndexBookEntity implements Serializable{
    private String bookImg;
    private String bookName;
    private String bookWriter;

    public FragmentIndexBookEntity(String bookImg, String bookName, String bookWriter) {
        this.bookImg = bookImg;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }

    public void setBookWriter(String bookWriter) {
        this.bookWriter = bookWriter;
    }
}
