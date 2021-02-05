package org.casey.basic.annotation;

/**
 * @ClassName Student
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description todo
 * @Date 2020/9/6
 */

public class Student {
    private String username;
    private Integer age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
