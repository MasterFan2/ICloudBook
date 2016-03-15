package com.masterfan.cloudbook.http.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by masterfan on 16/3/11.
 */
@Table(name = "UserInfo")
public class UserInfo extends BaseEntity {
    @Column(name = "birthday")
    private String birthday;

    @Column(name = "sex")
    private int sex;

    @Column(name = "phone")
    private String phone;

    @Column(name = "bookfavoritecount")
    private int bookfavoritecount;

    @Column(name = "focucount")
    private int focucount;

    @Column(name = "studentNumber")
    private String studentNumber;

    @Column(name = "state")
    private int state;

    @Column(name = "bookuploadcount")
    private int bookuploadcount;

    @Column(name = "type")
    private int type;

    @Column(name = "password")
    private String password;

    @Column(name = "bookrecommendcount")
    private int bookrecommendcount;


    @Column(name = "readtimecount")
    private int readtimecount;

    @Column(name = "commentcount")
    private int commentcount;

    @Column(name = "QQ")
    private String QQ;

    @Column(name = "bookdownloadcount")
    private int bookdownloadcount;

    @Column(name = "name")
    private String name;

    @Column(name = "chatcount")
    private int chatcount;

    @Column(name = "head")
    private String head;

    @Column(name = "readtotalsec")
    private int readtotalsec;

    @Column(name = "classes")
    private int classes;

    @Column(name = "byfocucount")
    private int byfocucount;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "readbookcount")
    private int readbookcount;

    @Column(name = "favorite")
    private String favorite;

    @Column(name = "student_number")
    private String student_number;

    @Column(name = "school")
    private int school;

    @Column(name = "email")
    private String email;

    @Column(name = "account")
    private String account;

    @Column(name = "motto")
    private String motto;

    @Column(name = "introduction")
    private String introduction;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBookfavoritecount() {
        return bookfavoritecount;
    }

    public void setBookfavoritecount(int bookfavoritecount) {
        this.bookfavoritecount = bookfavoritecount;
    }

    public int getFocucount() {
        return focucount;
    }

    public void setFocucount(int focucount) {
        this.focucount = focucount;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBookuploadcount() {
        return bookuploadcount;
    }

    public void setBookuploadcount(int bookuploadcount) {
        this.bookuploadcount = bookuploadcount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBookrecommendcount() {
        return bookrecommendcount;
    }

    public void setBookrecommendcount(int bookrecommendcount) {
        this.bookrecommendcount = bookrecommendcount;
    }



    public int getReadtimecount() {
        return readtimecount;
    }

    public void setReadtimecount(int readtimecount) {
        this.readtimecount = readtimecount;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public int getBookdownloadcount() {
        return bookdownloadcount;
    }

    public void setBookdownloadcount(int bookdownloadcount) {
        this.bookdownloadcount = bookdownloadcount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChatcount() {
        return chatcount;
    }

    public void setChatcount(int chatcount) {
        this.chatcount = chatcount;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getReadtotalsec() {
        return readtotalsec;
    }

    public void setReadtotalsec(int readtotalsec) {
        this.readtotalsec = readtotalsec;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public int getByfocucount() {
        return byfocucount;
    }

    public void setByfocucount(int byfocucount) {
        this.byfocucount = byfocucount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getReadbookcount() {
        return readbookcount;
    }

    public void setReadbookcount(int readbookcount) {
        this.readbookcount = readbookcount;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", bookfavoritecount=" + bookfavoritecount +
                ", focucount=" + focucount +
                ", studentNumber='" + studentNumber + '\'' +
                ", state=" + state +
                ", bookuploadcount=" + bookuploadcount +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", bookrecommendcount=" + bookrecommendcount +
                ", readtimecount=" + readtimecount +
                ", commentcount=" + commentcount +
                ", QQ='" + QQ + '\'' +
                ", bookdownloadcount=" + bookdownloadcount +
                ", name='" + name + '\'' +
                ", chatcount=" + chatcount +
                ", head='" + head + '\'' +
                ", readtotalsec=" + readtotalsec +
                ", classes=" + classes +
                ", byfocucount=" + byfocucount +
                ", nickname='" + nickname + '\'' +
                ", readbookcount=" + readbookcount +
                ", favorite='" + favorite + '\'' +
                ", student_number='" + student_number + '\'' +
                ", school=" + school +
                ", email='" + email + '\'' +
                ", account='" + account + '\'' +
                ", motto='" + motto + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }

    public UserInfo() {
    }
}
