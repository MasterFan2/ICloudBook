package com.masterfan.cloudbook.activity.personal.entity;

/**
 * 关注的人
 * Created by szj on 2016/1/21.
 */
public class Person {
    private String name;
    private String occupation;//职业
    private int collect;//收藏
    private int read;//己读
    private int comment;//评论
    private String forMessage;//寄语

    public Person(String name, String occupation, int collect, int read, int comment, String forMessage) {
        this.name = name;
        this.occupation = occupation;
        this.collect = collect;
        this.read = read;
        this.comment = comment;
        this.forMessage = forMessage;
    }

    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
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

    public String getForMessage() {
        return forMessage;
    }

    public void setForMessage(String forMessage) {
        this.forMessage = forMessage;
    }

}
