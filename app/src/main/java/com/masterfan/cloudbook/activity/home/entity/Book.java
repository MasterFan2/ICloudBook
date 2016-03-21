package com.masterfan.cloudbook.activity.home.entity;

import com.masterfan.cloudbook.http.bean.BaseEntity;

/**
 * txt图书阅读
 * Created by sunzj on 2016/3/22.
 */
public class Book extends BaseEntity {
    private String txt_file_path;
    private String pdf_file_path;
    private String file_sufix;
    private String readfile_path;

    public Book() {
    }

    public String getTxt_file_path() {
        return txt_file_path;
    }

    public void setTxt_file_path(String txt_file_path) {
        this.txt_file_path = txt_file_path;
    }

    public String getPdf_file_path() {
        return pdf_file_path;
    }

    public void setPdf_file_path(String pdf_file_path) {
        this.pdf_file_path = pdf_file_path;
    }

    public String getFile_sufix() {
        return file_sufix;
    }

    public void setFile_sufix(String file_sufix) {
        this.file_sufix = file_sufix;
    }

    public String getReadfile_path() {
        return readfile_path;
    }

    public void setReadfile_path(String readfile_path) {
        this.readfile_path = readfile_path;
    }
}
