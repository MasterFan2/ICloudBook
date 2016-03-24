package com.masterfan.cloudbook.activity.home.entity;

/**
 * 图书评论列表 里面的 UserInfo
 * Created by sunzj on 2016/3/22.
 */
public class UserInfo {
    private int id;
    private String head;
    private String nickname;

    public UserInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", head='" + head + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
