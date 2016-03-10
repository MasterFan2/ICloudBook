package com.masterfan.cloudbook.activity.manamgment.entity;

import java.io.Serializable;

/**
 * 老师账号
 * Created by sunzj on 2016/2/23.
 */
public class TeacherAccount implements Serializable {

    private String name;
    private String nickName;
    private String sex;
    private String state;

    public TeacherAccount( String name, String nickName, String sex, String state) {
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
