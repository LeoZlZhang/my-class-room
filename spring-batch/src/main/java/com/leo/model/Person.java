package com.leo.model;

/**
 * Created by sh00514 on 2017/1/22.
 * model person
 */
@SuppressWarnings("unused")
public class Person {
    private String name;
    private String age;
    private String married;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }
}
