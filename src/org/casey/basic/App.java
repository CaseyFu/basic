package org.casey.basic;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName App
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description todo
 * @Date 2020/7/16
 */

public class App {

    @Test
    public void test1(){


    }

    public String hello() {
        this.test1();
        // try {
        //     throw new Exception("hhh");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        System.out.println("抛出了异常还执行吗?");
        return "h22";
    }

    @Test
    public void test() {
       int type = 200;
       switch (type){
           case 100:{
               System.out.println(100);
               break;
           }
           case 200:{
               System.out.println(200);
               break;
           }
           default:{
               System.out.println("default");
           }
       }

    }

    public static void tt(){
        System.out.println("ok");
    }

    @Test
    public void testGeneric(){
        generic(99L);
    }

    public <T> void generic(T t){
        System.out.println(t);
        System.out.println(t instanceof String);
    }


}
