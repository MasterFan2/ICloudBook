package com.masterfan.cloudbook.activity.home.entity;

import com.masterfan.cloudbook.http.bean.BaseEntity;

/**
 * 图书评论列表
 * Created by sunzj on 2016/3/22.
 */
public class Comments extends BaseEntity {

    private String classes;
    private int id;
    private String bookInfo;
    private int commend;
    private String comment;
    private String createtime;
    private String school;
    private String userBookCommentCommends;
            private UserInfo userInfo;
}
