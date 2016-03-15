package com.masterfan.cloudbook.activity.manamgment.entity;

/**
 * 班级
 * Created by sunzj on 2016/3/15.
 */
public class Classes {
    private int id;
    private int grade;
    private int number;
    private int school;
    private String teacher;
    private String  name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classes() {
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", grade=" + grade +
                ", number=" + number +
                ", school=" + school +
                ", teacher='" + teacher + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
