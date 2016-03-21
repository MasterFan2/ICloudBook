package com.masterfan.cloudbook.activity.home.entity;


import java.util.ArrayList;

/**
 *txt图书阅读 返回集合
 * Created by sunzj on 2016/3/16.
 */
public class BookResp {
    private String code;
    private ArrayList<Book> book;

    public BookResp() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Book> getBook() {
        return book;
    }

    public void setBook(ArrayList<Book> book) {
        this.book = book;
    }
}
