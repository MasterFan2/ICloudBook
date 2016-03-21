package com.masterfan.cloudbook.activity.home.entity;


import java.util.ArrayList;

/**
 *图书列表 返回集合
 * Created by sunzj on 2016/3/16.
 */
public class BooksResp {
    private ArrayList<Books> books;

    public BooksResp() {
    }

    public ArrayList<Books> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Books> books) {
        this.books = books;
    }
}
