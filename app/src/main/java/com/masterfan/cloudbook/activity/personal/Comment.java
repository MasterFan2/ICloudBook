package com.masterfan.cloudbook.activity.personal;

/**
 * 评论对象
 * Created by szj on 2016/1/21.
 */
public class Comment {
    private String bookName;//书名
    private String time;//时间
    private String content;//评论内容

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "bookName='" + bookName + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Comment(String bookName, String content, String time) {
        this.bookName = bookName;
        this.content = content;
        this.time = time;
    }
}
