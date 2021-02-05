package org.casey.basic;

import org.casey.basic.annotation.UserAnnotation;
import org.casey.basic.common.User;
import org.junit.Test;

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

public class AnnotationTest {

    /**
     * 第 1 种, 注入普通的参数列表, 基本类型或String
     */
    @UserAnnotation(id = 1L, name = "Fu Kai - 1", age = 22, enabled = false)
    public void add(Long id, String name, Integer age, Boolean enabled) {
        System.out.println(id);
        System.out.println(name);
        System.out.println(age);
        System.out.println(enabled);
    }

    /**
     * 注入参数的类型为String或基础类型
     * add(Long id, String name, Integer age, Boolean enabled)
     */
    @Test
    public void injectParameterField() {
        Class<AnnotationTest> clazz = AnnotationTest.class;
        Method method = null;
        UserAnnotation userAnnotation = null;
        try {
            // 得到 add 方法
            method = clazz.getMethod("add", Long.class, String.class, Integer.class, Boolean.class);
            userAnnotation = method.getAnnotation(UserAnnotation.class);
            long id = userAnnotation.id();
            String name = userAnnotation.name();
            int age = userAnnotation.age();
            boolean enabled = userAnnotation.enabled();
            Object obj = clazz.newInstance();
            method.invoke(obj, id, name, age, enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 第 2 种, 注入student对象, 由反射通过setter注入
     */
    private User user2;

    public User getUser2() {
        return user2;
    }

    @UserAnnotation(id = 2L, name = "Fu Kai - 2", age = 22)
    public void setUser2(User user2) {
        this.user2 = user2;
    }

    /**
     * 注入参数的类型为一个对象, setUser(User user2)
     */
    @Test
    public void injectParameterObject() {
        try {
            // 1. 得到想要注入的属性
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("user2", AnnotationTest.class);
            // 2. 得到想要注入属性的具体对象
            User user = (User) propertyDescriptor.getPropertyType().newInstance();
            // 3. 得到该属性set方法,即setStudent()
            Method method = propertyDescriptor.getWriteMethod();
            // 4. 得到写方法的注解
            Annotation annotation = method.getAnnotation(UserAnnotation.class);
            // 5. 获取注解上各个字段的信息, 字段由方法实现
            Method[] methods = annotation.getClass().getMethods();
            for (Method each : methods) {
                // name为各个成员变量名
                String name = each.getName();
                try {
                    PropertyDescriptor descriptor = new PropertyDescriptor(name, User.class);
                    Method method1 = descriptor.getWriteMethod();
                    Object obj = each.invoke(annotation);
                    method1.invoke(user, obj);
                } catch (Exception ignored) {
                }
            }
            // 实例化一个client对象
            AnnotationTest annotationTest = new AnnotationTest();
            // 将信息注入成功的student对象通过调用setUser()方法注入
            method.invoke(annotationTest, user);
            System.out.println(annotationTest.getUser2());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 第 3 种, 注入类的成员变量 注入user对象, 由反射通过字段进行注入
     */
    @UserAnnotation(id = 3L, name = "Fu Kai - 3", age = 23, enabled = false)
    private User user3;

    public User getUser3() {
        return user3;
    }

    /**
     * 注入成员变量的对象
     */
    @Test
    public void injectField() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 1. 得到想要注入的成员变量
        Field field = AnnotationTest.class.getDeclaredField("user3");
        // 2. 获取成员变量实例
        User user = (User) field.getType().newInstance();
        // 3. 获得成员变量上的注解
        Annotation annotation = field.getAnnotation(UserAnnotation.class);
        // 4. 获取注解的属性
        Method[] methods = annotation.getClass().getMethods();
        for (Method each : methods) {
            // 5. 获取注解的方法名, 即属性名
            String name = each.getName();
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(name, User.class);
                Method method1 = descriptor.getWriteMethod();
                Object obj = each.invoke(annotation);
                method1.invoke(user, obj);
            } catch (Exception ignored) {
            }
        }
        AnnotationTest annotationTest = new AnnotationTest();
        field.setAccessible(true);
        field.set(annotationTest, user);
        System.out.println(annotationTest.getUser3());
    }

}
