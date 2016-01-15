package com.masterfan.cloudbook.activity.test.bean;

/**
 * Created by 13510 on 2016/1/15.
 */
public class Item {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item(int id, String name) {

        this.id = id;
        this.name = name;
    }
}
