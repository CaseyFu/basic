package org.casey.basic.reflection;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName Field
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 反射获取类成员变量例子
 * @Date 2020/8/30
 */

public class FieldTest {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = null;
        Field[] fields = null;
        Field field = null;
        Object obj = null;
        Student student = null;
        try {
            clazz = Class.forName("org.casey.basic.reflection.Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (ObjectUtil.isNull(clazz)) {
            return;
        }

        System.out.println("----------所有公共字段(public)----------");
        fields = clazz.getFields();
        for (Field each : fields) {
            System.out.println(each);
        }

        System.out.println("----------所有字段----------");
        fields = clazz.getDeclaredFields();
        for (Field each : fields) {
            System.out.println(each);
        }

        System.out.println("----------指定公共字段----------");
        field = clazz.getField("name");
        System.out.println(field);

        System.out.println("----------指定字段----------");
        field = clazz.getDeclaredField("id");
        System.out.println(field);
        obj  = clazz.getConstructor().newInstance();
        field.setAccessible(true);
        field.set(obj, 99999L);
        student = (Student) obj;
        System.out.println(student);
    }
}
