package org.casey.basic.common;

import java.io.Serializable;

/**
 * @ClassName ArrayCopy
 * @Author Casey Fu
 * @Description 对象的序列化, 序列化即把对象写入文件或读取对象
 * @Date 2020/7/6 15:41
 */

public class Work implements Serializable {

    private static final long serialVersionUID = 1L;
    public String name;
    public int age;
    public int gonghao;

    public Work() {

    }

    public Work(String name, int age, int gonghao) {
        this.name = name;
        this.age = age;
        this.gonghao = gonghao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGonghao() {
        return gonghao;
    }

    public void setGonghao(int gonghao) {
        this.gonghao = gonghao;
    }

    @Override
    public String toString() {
        return "Work{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gonghao=" + gonghao +
                '}';
    }
}
