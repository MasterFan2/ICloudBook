package com.masterfan.cloudbook.activity.personal.entity;

import java.io.Serializable;

/**
 * 图书管理
 * Created by szj on 2016/1/22.
 */
public class BookManagement implements Serializable{
    private String bookNme;//书名
    private String writer;//作者
    private double hasRead;//已读
    private int collect;//收藏
    private int download;//下载
    private int read;//阅读
    private int comment;//评论
    private String time;//最近阅读时间
    private String introduction;//简介

    public BookManagement(String bookNme, String writer, double hasRead, int collect, int download, int read, int comment, String time) {
        this.bookNme = bookNme;
        this.writer = writer;
        this.hasRead = hasRead;
        this.collect = collect;
        this.download = download;
        this.read = read;
        this.comment = comment;
        this.time = time;
    }

    public String getBookNme() {
        return bookNme;
    }

    public void setBookNme(String bookNme) {
        this.bookNme = bookNme;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public double getHasRead() {
        return hasRead;
    }

    public void setHasRead(double hasRead) {
        this.hasRead = hasRead;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public BookManagement(String bookNme, String writer,double hasRead, String introduction) {
        this.bookNme = bookNme;
        this.writer = writer;
        this.hasRead = hasRead;
        this.introduction = introduction;
    }
}
