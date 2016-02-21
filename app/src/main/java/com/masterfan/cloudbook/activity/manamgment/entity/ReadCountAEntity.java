package com.masterfan.cloudbook.activity.manamgment.entity;

/**
 * 阅读次数排行Entity
 * Created by sunzj on 2016/2/22.
 */
public class ReadCountAEntity {
    private String bookName;//书名
    private String writer;//作者
    private String type;//类别
    private String readCount;//阅读次数

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public ReadCountAEntity(String bookName, String writer, String type, String readCount) {
        this.bookName = bookName;
        this.writer = writer;
        this.type = type;
        this.readCount = readCount;
    }
}
