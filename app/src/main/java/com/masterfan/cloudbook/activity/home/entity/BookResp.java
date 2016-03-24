package com.masterfan.cloudbook.activity.home.entity;


import java.util.ArrayList;

/**
 *txt图书阅读 返回集合
 * Created by sunzj on 2016/3/16.
 */
public class BookResp {
    private int code;
    private Book book;
    private String content;

    public BookResp() {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
