package org.casey.basic.annotation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName Client
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 反射注入，
 * 1、注入方法的参数列表，参数类型为基础类型或String
 * 2、注入方法的参数列表，参数类型为一个对象
 * 3、注入类的成员变量，字段注入，注入类型为一个对象
 * @Date 2020/9/6
 */

public class Client {

    /**
     * 第 3 种，注入类的成员变量。注入other对象, 由反射通过字段进行注入
     */
    @MyAnnotation(username = "fu", age = 20)
    private Student other;

    public Student getOther() {
        return other;
    }

    /**
     * 注入成员变量的对象
     */
    public void injectField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 1. 得到想要注入的成员变量
        Field field = Client.class.getDeclaredField("other");
        // 2. 获取成员变量实例
        Student other = (Student) field.getType().newInstance();
        // 3. 获得成员变量上的注解
        Annotation annotation = field.getAnnotation(MyAnnotation.class);
        // 4. 获取注解的属性
        Method[] methods = annotation.getClass().getMethods();
        for (Method each : methods) {
            // 5. 获取注解的方法名, 即属性名
            String name = each.getName();
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(name, Student.class);
                Method method1 = descriptor.getWriteMethod();
                Object obj = each.invoke(annotation);
                method1.invoke(other, obj);
            } catch (Exception ignored) {
            }
        }
        Client client = new Client();
        field.setAccessible(true);
        field.set(client, other);
        System.out.println(client.getOther());
    }


    /**
     * 第 2 种，注入student对象, 由反射通过setter注入
     */
    private Student student;

    public Student getStudent() {
        return student;
    }

    @MyAnnotation(username = "kai", age = 19)
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * 注入参数的类型为一个对象, setStudent(Student student)
     */
    public void injectParameterObject() {
        try {
            // 1. 得到想要注入的属性
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("student", Client.class);
            // 2. 得到想要注入属性的具体对象
            Student student = (Student) propertyDescriptor.getPropertyType().newInstance();
            // 3. 得到该属性set方法,即setStudent()
            Method method = propertyDescriptor.getWriteMethod();
            // 4. 得到写方法的注解
            Annotation annotation = method.getAnnotation(MyAnnotation.class);
            // 5. 获取注解上各个字段的信息, 字段由方法实现
            Method[] methods = annotation.getClass().getMethods();
            for (Method each : methods) {
                // username、age
                String name = each.getName();
                try {
                    PropertyDescriptor descriptor = new PropertyDescriptor(name, Student.class);
                    Method method1 = descriptor.getWriteMethod();
                    Object obj = each.invoke(annotation);
                    method1.invoke(student, obj);
                } catch (Exception ignored) {
                }
            }
            // 实例化一个client对象
            Client client = new Client();
            // 将信息注入成功的student对象通过调用setStudent()方法注入
            method.invoke(client, student);
            System.out.println(client.getStudent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 第 1 种，注入普通的参数列表, 基本类型或String
     */
    @MyAnnotation(username = "kai", age = 19)
    public void add(String username, int age) {
        System.out.println(username);
        System.out.println(age);
    }

    /**
     * 注入参数的类型为String或基础类型, add(String username, int age)
     */
    public void injectParameterField() {
        Class<Client> clazz = Client.class;
        Method method = null;
        MyAnnotation myAnnotation = null;
        try {
            // 得到 add 方法
            method = clazz.getMethod("add", String.class, int.class);
            myAnnotation = method.getAnnotation(MyAnnotation.class);
            String username = myAnnotation.username();
            int age = myAnnotation.age();

            Object obj = clazz.newInstance();
            method.invoke(obj, username, age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // new Client().invokeInject();
        // new Client().injectParameterObject();
        new Client().injectField();
    }
}
