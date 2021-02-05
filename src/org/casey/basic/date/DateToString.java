
//日期格式转化为字符串格式
package org.casey.basic.date;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateToString {
    public static void main(String[] args) {
        //S,毫秒
        //s,秒
        //m,分
        //h,12小时
        //H,24小时
        //d,日
        //M,月
        //y,年
        String time1 = (new SimpleDateFormat("yyyy-MM-dd---SSS ss:mm:HH")).format(new Date());
        System.out.println("当前的时间格式是yyyy-MM-dd---SSS ss:mm:HH");
        System.out.println(time1);
        System.out.println("改变格式，乱改");

        SimpleDateFormat t1 = new SimpleDateFormat("yyyy年MM月dd日,北京时间HH点mm分ss秒");
        Date t2 = new Date();
        String time2 = t1.format(t2);
        System.out.println("当前的时间格式是yyyy年MM月dd日,北京时间HH点mm分ss秒" + time2);

    }
}
