package org.casey.basic.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PassGenericsCheck
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 反射绕过泛型检查
 * 向String泛型的集合中添加Integer
 * @Date 2020/8/30
 */

public class PassGenericsCheck {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        // 获取list的Class对象
        Class<?> clazz = list.getClass();
        // 获取Method
        Method method = clazz.getMethod("add", Object.class);
        // 证明可以用泛型绕开泛型检查向String集合中添加Integer
        method.invoke(list, 100);

        // 类型Object
        for (Object each : list) {
            if (each instanceof Integer) {
                System.out.println(" 整型: " + each);
            } else if (each instanceof String) {
                System.out.println(" 字符串: " + each);
            }
        }
    }
}
