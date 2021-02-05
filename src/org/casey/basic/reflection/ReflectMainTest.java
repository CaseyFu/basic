package org.casey.basic.reflection;

import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName ReflectMainTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 反射获取静态的main方法
 * @Date 2020/8/30
 */

public class ReflectMainTest {
    public static void main(String[] args) {
        System.out.println("这里测试反射main");
        System.out.println(Arrays.toString(args));
    }

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("org.casey.basic.reflection.ReflectMainTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (ObjectUtil.isNull(clazz)) {
            return;
        }
        Method method = clazz.getMethod("main", String[].class);
        // main是静态方法, 所以不用实例化, 直接null
        method.invoke(null, (Object) new String[]{"a", "b", "c"});
    }

}
