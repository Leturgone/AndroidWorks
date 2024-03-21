package com.example.yasnecovfi3;

import java.io.Serializable;

public class MyObject implements Serializable {
    String name;
    String group;
    int age;
    int mark;

    public MyObject(String name, String group, int age, int mark) {
        this.name = name;
        this.group = group;
        this.age = age;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
