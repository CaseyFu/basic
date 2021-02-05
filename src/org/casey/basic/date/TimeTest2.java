
//练习，利用数组获取1999~2018的随机时刻，并排序
package org.casey.basic.date;

import java.util.Date;
import java.util.Arrays;

public class TimeTest2 {
    public static void main(String[] args) {
        testyear1 time1 = new testyear1();
        long a2018end = new testyear1().TimeTest2_Mainmethod();
        long array[] = new long[50];
        System.out.println("1999年开始:" + new Date(time1.a1999start) + "\n2018年结束:" + new Date(a2018end));
        for (int i = 0; i <= 49; i++) {
            array[i] = (long) (Math.random() * (a2018end - time1.a1999start) + time1.a1999start);
        }
        Arrays.sort(array);
        for (long i : array) {
            System.out.println(new Date(i));
        }
    }
}
