package org.casey.basic.reflection;

import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName ConstructorTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 反射获取类构造方法例子
 * @Date 2020/8/30
 */

public class ConstructorTest {
    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = null;
        Constructor<?>[] constructors = null;
        Constructor<?> constructor = null;
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

        System.out.println("----------所有构造方法(public protected default private)----------");
        constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> each : constructors) {
            System.out.println(each);
        }

        System.out.println("----------公有构造方法(public)----------");
        constructors = clazz.getConstructors();
        for (Constructor<?> each : constructors) {
            System.out.println(each);
        }

        System.out.println("----------一个公有且无参构造方法(public)----------");
        constructor = clazz.getDeclaredConstructor();
        System.out.println(constructor);
        obj = constructor.newInstance();
        student = (Student) obj;
        System.out.println(student);

        System.out.println("----------一个公有且多参构造方法(public)----------");
        constructor = clazz.getDeclaredConstructor(Long.class, String.class, Integer.class, String.class);
        System.out.println(constructor);
        obj = constructor.newInstance(10L, "懒羊羊", 9, "162@qq.com");
        student = (Student) obj;
        System.out.println(student);

        System.out.println("----------一个私有构造方法(private)----------");
        constructor = clazz.getDeclaredConstructor(int.class);
        // 提升private的访问级别
        constructor.setAccessible(true);
        System.out.println(constructor);
        obj = constructor.newInstance(10);
        Student student1 = (Student) obj;
        System.out.println(student1);
    }
}
