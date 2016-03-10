package com.masterfan.cloudbook.activity.manamgment.entity;

/**
 * 阅读次数排行Entity
 * Created by sunzj on 2016/2/22.
 */
public class ReadCountCEntity {
    private String bookName;//书名
    private String writer;//作者
    private String type;//类别
    private String allTime;//总时长
    private String allComment;//总评论
    private String allCollect;//总收藏

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

    public String getAllTime() {
        return allTime;
    }

    public void setAllTime(String allTime) {
        this.allTime = allTime;
    }

    public String getAllComment() {
        return allComment;
    }

    public void setAllComment(String allComment) {
        this.allComment = allComment;
    }

    public String getAllCollect() {
        return allCollect;
    }

    public void setAllCollect(String allCollect) {
        this.allCollect = allCollect;
    }

    public ReadCountCEntity(String bookName, String writer, String type, String allTime, String allComment, String allCollect) {
        this.bookName = bookName;
        this.writer = writer;
        this.type = type;
        this.allTime = allTime;
        this.allComment = allComment;
        this.allCollect = allCollect;
    }
}
