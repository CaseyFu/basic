package org.casey.basic.reflection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @ClassName ReflectFromFile
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 从外部配置文件中反射
 * @Date 2020/8/30
 */

public class ReflectFromFile {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(getValue("className"));
        Method method = clazz.getDeclaredMethod(getValue("methodName"));
        System.out.println(method);
        Object obj = clazz.getConstructor().newInstance();
        method.invoke(obj);
    }

    public static String getValue(String key) {
        Properties properties = new Properties();
        try (FileReader in = new FileReader("F:\\repository\\java\\basic\\src\\org\\casey\\basic\\reflection\\config.properties")) {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
