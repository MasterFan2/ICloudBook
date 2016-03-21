package com.masterfan.cloudbook.activity.home.entity;

/**
 * 图书详情
 * Created by sunzj on 2016/3/21.
 */
public class Detail {
    private String pdf_file_path;
    private int total_page;
    private int readuser;
    private String file_sufix;
    private int download;
    private int favorite;
    private int recommend;
    private String intro;
    private int id;
    private int school;
    private int myreadcount;
    private int book;
    private String txt_file_path;
    private int favoriteid;
    private int comment;
    private int readtotaltime;
    private int readcount;

    public Detail() {
    }

    public String getPdf_file_path() {
        return pdf_file_path;
    }

    public void setPdf_file_path(String pdf_file_path) {
        this.pdf_file_path = pdf_file_path;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getReaduser() {
        return readuser;
    }

    public void setReaduser(int readuser) {
        this.readuser = readuser;
    }

    public String getFile_sufix() {
        return file_sufix;
    }

    public void setFile_sufix(String file_sufix) {
        this.file_sufix = file_sufix;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public int getMyreadcount() {
        return myreadcount;
    }

    public void setMyreadcount(int myreadcount) {
        this.myreadcount = myreadcount;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public String getTxt_file_path() {
        return txt_file_path;
    }

    public void setTxt_file_path(String txt_file_path) {
        this.txt_file_path = txt_file_path;
    }

    public int getFavoriteid() {
        return favoriteid;
    }

    public void setFavoriteid(int favoriteid) {
        this.favoriteid = favoriteid;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getReadtotaltime() {
        return readtotaltime;
    }

    public void setReadtotaltime(int readtotaltime) {
        this.readtotaltime = readtotaltime;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "pdf_file_path='" + pdf_file_path + '\'' +
                ", total_page=" + total_page +
                ", readuser=" + readuser +
                ", file_sufix='" + file_sufix + '\'' +
                ", download=" + download +
                ", favorite=" + favorite +
                ", recommend=" + recommend +
                ", intro='" + intro + '\'' +
                ", id=" + id +
                ", school=" + school +
                ", myreadcount=" + myreadcount +
                ", book=" + book +
                ", txt_file_path='" + txt_file_path + '\'' +
                ", favoriteid=" + favoriteid +
                ", comment=" + comment +
                ", readtotaltime=" + readtotaltime +
                ", readcount=" + readcount +
                '}';
    }
}
