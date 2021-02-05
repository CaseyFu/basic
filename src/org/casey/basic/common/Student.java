package org.casey.basic.common;

import java.io.Serializable;

public class Student implements Serializable {

    public String name;
    public int age;
    public double height;
    private static final long serialVersionUID = 1L;

    public Student() {

    }

    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
