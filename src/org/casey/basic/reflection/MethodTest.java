package org.casey.basic.reflection;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName MethodTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 反射获取类方法例子
 * @Date 2020/8/30
 */

public class MethodTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = null;
        Method[] methods = null;
        Method method = null;
        Object obj = null;
        Student student = null;
        try {
            clazz = Class.forName("org.casey.basic.reflection.Student");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ObjectUtil.isNull(clazz)) {
            return;
        }

        System.out.println("----------所有方法(public protected default private)----------");
        methods = clazz.getDeclaredMethods();
        for (Method each : methods) {
            System.out.println(each);
        }

        System.out.println("----------所有公有方法(public)----------");
        methods = clazz.getMethods();
        for (Method each : methods) {
            System.out.println(each);
        }

        System.out.println("----------返回值protected int show2()----------");
        method = clazz.getDeclaredMethod("show2");
        obj = clazz.getConstructor().newInstance();
        // 调用方法
        Object newObj = method.invoke(obj);
        System.out.println("返回值: " + newObj);
        System.out.println(method);
        student = (Student) obj;
        System.out.println(student);

        System.out.println("----------加参数private void show4(String name)----------");
        method = clazz.getDeclaredMethod("show4", String.class);
        obj = clazz.getConstructor().newInstance();
        method.setAccessible(true);
        // 调用方法
        method.invoke(obj, "args");
        System.out.println(method);
        student = (Student) obj;
        System.out.println(student);


    }
}
