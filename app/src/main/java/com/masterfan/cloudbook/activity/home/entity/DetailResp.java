package com.masterfan.cloudbook.activity.home.entity;


import java.util.ArrayList;

/**
 * 图书详情列表
 * Created by sunzj on 2016/3/16.
 */
public class DetailResp {
    private ArrayList<Detail> detail;

    public DetailResp() {
    }

    public ArrayList<Detail> getDetail() {
        return detail;
    }

    public void setDetails(ArrayList<Detail> detail) {
        this.detail = detail;
    }
}
