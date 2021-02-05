package org.casey.basic;

import org.junit.Test;

/**
 * @ClassName EnumTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 枚举类型
 * 枚举类型是java5的新特征
 * 枚举类可以实现接口, 但不能继承, 也不能被继承
 * 枚举名用大写, 每个枚举之间用逗号comma隔开, 最后一个枚举名后分号semicolon结尾
 * 枚举类为抽象类
 * @Date 2020/8/22
 */
enum Method{
    /**
     * 任何请求方法
     */
    ANY(0),
    POST(1),
    GET(2),
    PUT(3),
    DELETE(4),
    HEAD(5),
    PATCH(6),
    OPTIONS(7),
    TRACE(8);
    Integer value;

    Method(Integer value) {
        this.value = value;
    }
}


public class EnumTest {
    @Test
    public void test(){
        System.out.println(Method.ANY);
    }
}

