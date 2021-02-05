
//练习，翻日历，找到2020年的今天2015年的今天，上个月的今天，上个月的第5天，字符串格式化输出
package org.casey.basic.date;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeTest3 {

    private static SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");

    public static void main(String[] args) {
        Calendar t = Calendar.getInstance();
        Date now = t.getTime();
        System.out.println("现在:" + toString(now));

        t.setTime(now);
        t.add(Calendar.YEAR, +2);
        System.out.println("2020年的今天:" + toString(t.getTime()));

        t.setTime(now);
        t.add(Calendar.YEAR, -3);
        System.out.println("2015年的今天:" + toString(t.getTime()));

        t.setTime(now);
        t.add(Calendar.MONTH, -1);
        System.out.println("上个月的今天:" + toString(t.getTime()));

        t.setTime(now);
        t.add(Calendar.MONTH, -1);
        t.set(Calendar.DATE, +5);
        System.out.println("上个月的第5天:" + toString(t.getTime()));
    }

    public static String toString(Date t) {
        return sdt.format(t);
    }
}
