package com.masterfan.cloudbook.activity.personal.entity;

/**
 * 关注的人
 * Created by szj on 2016/1/21.
 */
public class Person {
    private String name;
    private String zhiye;
    private int shoucang;
    private int yuedu;
    private int pinglun;
    private String jiyu;

    public Person(String name, String zhiye, int shoucang, int yuedu, int pinglun, String jiyu) {
        this.name = name;
        this.zhiye = zhiye;
        this.shoucang = shoucang;
        this.yuedu = yuedu;
        this.pinglun = pinglun;
        this.jiyu = jiyu;
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

    public String getZhiye() {
        return zhiye;
    }

    public void setZhiye(String zhiye) {
        this.zhiye = zhiye;
    }

    public int getShoucang() {
        return shoucang;
    }

    public void setShoucang(int shoucang) {
        this.shoucang = shoucang;
    }

    public int getYuedu() {
        return yuedu;
    }

    public void setYuedu(int yuedu) {
        this.yuedu = yuedu;
    }

    public int getPinglun() {
        return pinglun;
    }

    public void setPinglun(int pinglun) {
        this.pinglun = pinglun;
    }

    public String getJiyu() {
        return jiyu;
    }

    public void setJiyu(String jiyu) {
        this.jiyu = jiyu;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", zhiye='" + zhiye + '\'' +
                ", shoucang=" + shoucang +
                ", yuedu=" + yuedu +
                ", pinglun=" + pinglun +
                ", jiyu='" + jiyu + '\'' +
                '}';
    }
}
