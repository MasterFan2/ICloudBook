package com.masterfan.cloudbook.activity.manamgment.entity;

/**
 * 班级阅读
 * Created by Administrator on 2016/1/29 0029.
 */
public class ClassRead {
    private String name;
    private int readNum;
    private int readTime;
    private int commentNum;
    private int colledtNum;
    private int recommendNum;

    public ClassRead(String name, int readNum, int readTime, int commentNum, int colledtNum, int recommendNum) {
        this.name = name;
        this.readNum = readNum;
        this.readTime = readTime;
        this.commentNum = commentNum;
        this.colledtNum = colledtNum;
        this.recommendNum = recommendNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getColledtNum() {
        return colledtNum;
    }

    public void setColledtNum(int colledtNum) {
        this.colledtNum = colledtNum;
    }

    public int getRecommendNum() {
        return recommendNum;
    }

    public void setRecommendNum(int recommendNum) {
        this.recommendNum = recommendNum;
    }
}
