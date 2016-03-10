package com.masterfan.cloudbook.activity.manamgment.entity;

/**
 * 个人阅读
 * Created by Administrator on 2016/1/29 0029.
 */
public class PersonRead {
    private String name;
    private String time;
    private int readMinute;

    public PersonRead(String name, String time, int readMinute) {
        this.name = name;
        this.time = time;
        this.readMinute = readMinute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReadMinute() {
        return readMinute;
    }

    public void setReadMinute(int readMinute) {
        this.readMinute = readMinute;
    }
}
