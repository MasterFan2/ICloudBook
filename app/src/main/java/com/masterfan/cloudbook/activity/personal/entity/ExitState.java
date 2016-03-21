package com.masterfan.cloudbook.activity.personal.entity;

/**
 * 退出状态
 * Created by sunzj on 2016/3/17.
 */
public class ExitState {
    private static int isLogin = -1;//-1表示退出状态，1表示登陆状态
    private static ExitState exitState;

    public ExitState() {
    }

    public static ExitState getExitState(){
        if(exitState == null){exitState = new ExitState();}
        return exitState;
    }
    public static int getIsLogin() {
        return isLogin;
    }

    public static void setIsLogin(int isLogin) {
        ExitState.isLogin = isLogin;
    }
}
