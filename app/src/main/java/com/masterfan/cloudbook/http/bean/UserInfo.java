package com.masterfan.cloudbook.http.bean;

/**
 * Created by masterfan on 16/3/11.
 */
public class UserInfo {
    private String birthday;
    private int         sex;
    private String phone;
    private int bookfavoritecount;
    private int focucount;
    private String studentNumber;
    private int state;
    private int bookuploadcount;
    private int type;
    private String password;
    private int bookrecommendcount;
    private long id;
    private int readtimecount;
    private int commentcount;
    private String QQ;
    private int bookdownloadcount;
    private String name;
    private int chatcount;
    private String head;
    private int readtotalsec;
    private int classes;
    private int byfocucount;
    private String nickname;
    private int readbookcount;
    private String favorite;
    private String student_number;
    private int school;
    private String email;
    private String account;
    private String motto;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                ", id=" + id +
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
