package com.masterfan.cloudbook.http.bean;

/**
 * Created by masterfan on 16/3/11.
 */
public class UserResp {

    private int code;
    private String token;
    private UserInfo user;

    public UserResp() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
