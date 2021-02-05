package org.casey.basic.regularexpression;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName MainTest
 * @Author Fu Kai
 * @Description 测试
 * @Date 2020/11/17
 */

public class MainTest {


    /**
     * 测试正则表达式
     */
    @Test
    public void test() {
        String str = "";
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            System.out.println("不是数字");
        }
    }

    /**
     * 测试正则表达式
     */
    @Test
    public void test1() {
        String str = "a";

        int a = Integer.parseInt(str);
        System.out.println(a);
    }

    /**
     * 测试字符串的剪切
     */
    @Test
    public void testCutString() {
        String str = "金科智慧服务渝西区域公司,第二城区公司,荣昌金科棠悦府,荣昌金科棠悦府后勤部";
        char delimiter = ',';
        int last = str.lastIndexOf(delimiter);
        System.out.println(last);
        System.out.println(str.substring(last + 1).trim().concat("hhh" + str));
    }

    @Test
    public void available() {
        int[] parent = {1, 2, 3, 4};
        int[] child = {2, 3, 4, 1};
        for (int eachParent : parent) {
            boolean isUnavailable = true;
            for (int eachChild : child) {
                if (eachChild == eachParent) {
                    isUnavailable = false;
                    break;
                }
            }
            if (isUnavailable) {
                System.out.println("isUnavailable");
            }
        }
    }

    /**
     * 测试 循环最后一个2后返回
     */
    @Test
    public void testAfterOut(){
        int target = 2;
        int[] arr = {1, 3, 5, 2, 2, 2, 5, 6, 7, 8, 9};
        for (int each:arr){
            boolean isOver = false;
            if(target == each){
                System.out.println("找到: "+each);
                isOver = true;
            }
            if(isOver && each!=target){
                break;
            }
            System.out.println(each);
        }
        System.out.println("完成");
    }
}
