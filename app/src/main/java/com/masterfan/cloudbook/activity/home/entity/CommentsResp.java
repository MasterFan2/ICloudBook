package com.masterfan.cloudbook.activity.home.entity;


import java.util.ArrayList;

/**
 *图书评论列表 返回集合
 * Created by sunzj on 2016/3/16.
 */
public class CommentsResp {
    private ArrayList<Comments> comments;

    public CommentsResp() {
    }

    public ArrayList<Comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }
}
