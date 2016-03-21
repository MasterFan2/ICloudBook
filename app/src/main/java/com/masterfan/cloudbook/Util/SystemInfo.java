package com.masterfan.cloudbook.Util;

import com.masterfan.cloudbook.http.bean.BaseEntity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 系统版本号，等信息
 * Created by sunzj on 2016/3/17.
 */
@Table(name="systemInfo")
public class SystemInfo extends BaseEntity {
    @Column(name = "schoolId")
    private String schoolId;//当前app对应的学校的id，此id为固定死的值，直接在代码中写死
    @Column(name = "os")
    private String os;//当前app所在的手机系统名称
    @Column(name = "os_version")
    private String os_version;//当前app所在的手机系统版本
    @Column(name = "app_version")
    private String app_version;//当前安装的app的版本号
    @Column(name = "token")
    private String token;//当前登录用户的token值
    @Column(name = "userId")
    private String userId;//当前登录用户的id
    @Column(name = "gradeId")
    private String gradeId;//当前登录用户的年级id
    @Column(name = "classesId")
    private String classesId;//当前登录用户的班级id

    private static SystemInfo systemInfo;

    public SystemInfo() {
    }
    public static SystemInfo getSystemInfo(){
        if(systemInfo == null){
            systemInfo = new SystemInfo();
        }
        return systemInfo;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId;
    }

    @Override
    public String toString() {
        return "SystemInfo{" +
                "schoolId='" + schoolId + '\'' +
                ", os='" + os + '\'' +
                ", os_version='" + os_version + '\'' +
                ", app_version='" + app_version + '\'' +
                ", token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", classesId='" + classesId + '\'' +
                '}';
    }
}
