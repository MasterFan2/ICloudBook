package com.masterfan.cloudbook.activity.manamgment.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/5 0005.
 */
public class UploadBookAudit implements Serializable{
    private String bookName;
    private String writer;
    private String uploadClass;
    private String uploadReason;

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

    public String getUploadClass() {
        return uploadClass;
    }

    public void setUploadClass(String uploadClass) {
        this.uploadClass = uploadClass;
    }

    public String getUploadReason() {
        return uploadReason;
    }

    public void setUploadReason(String uploadReason) {
        this.uploadReason = uploadReason;
    }

    public UploadBookAudit(String bookName, String writer, String uploadClass, String uploadReason) {
        this.bookName = bookName;
        this.writer = writer;
        this.uploadClass = uploadClass;
        this.uploadReason = uploadReason;
    }
}
