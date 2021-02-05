package org.casey.basic.reflection;

/**
 * @ClassName Student
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description Student类
 * @Date 2020/8/30
 */

public class Student {
    private Long id;
    public String name;
    protected Integer age;
    String email;

    //---------------构造方法-------------------

    /**
     * 无参构造方法
     */
    public Student() {
        this.name = "anonymous";
    }

    /**
     * 默认的构造方法
     */
    Student(String name) {
        this.name = name;
    }

    /**
     * 有一个参数的构造方法
     */
    public Student(Long id) {
        this.id = id;
    }

    /**
     * 受保护的构造方法
     */
    protected Student(Integer age) {
        this.age = age;
    }

    /**
     * 私有构造方法
     */
    private Student(int age) {
        this.age = age;
    }

    /**
     * 有多个参数的构造方法
     */
    public Student(String name, int age) {
        //这的执行效率有问题, 以后解决
        System.out.println("姓名：" + name + "年龄：" + age);
        this.name = name;
        this.age = age;
    }

    public Student(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void show1() {
        System.out.println("public void show1()");
    }

    protected int show2() {
        System.out.println("protected void show2()");
        return -1;
    }

    void show3() {
        System.out.println("show3()");
    }

    private void show4(String name) {
        System.out.println("public void show1()");
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}