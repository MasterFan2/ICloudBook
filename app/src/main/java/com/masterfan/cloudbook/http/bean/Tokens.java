package com.masterfan.cloudbook.http.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by sunzj on 2016/3/17.
 */
@Table(name="tokens")
public class Tokens  {
    @Column(name = "token")
    private String token;

    private static Tokens tokens;
    public Tokens() {
    }
    public static Tokens getTokens(){
        if(tokens == null){
            tokens = new Tokens();
        }
        return tokens;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
