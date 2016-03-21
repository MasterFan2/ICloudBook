package com.masterfan.cloudbook.activity.home.entity;

/**
 * 图书列表
 * Created by sunzj on 2016/3/16.
 */
public class Books {
    private int id;
    private String author;
    private String title;
    private String cover_imgs;
    private String nationality;
    private String type_name;
    private int type;
    private String dynasty;

    public Books() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_imgs() {
        return cover_imgs;
    }

    public void setCover_imgs(String cover_imgs) {
        this.cover_imgs = cover_imgs;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", cover_imgs='" + cover_imgs + '\'' +
                ", nationality='" + nationality + '\'' +
                ", type_name='" + type_name + '\'' +
                ", type=" + type +
                ", dynasty='" + dynasty + '\'' +
                '}';
    }
}
