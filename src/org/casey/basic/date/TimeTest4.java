
//练习，找出两个月之后的倒数第四天
package org.casey.basic.date;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeTest4 {
    public static void main(String[] args) {
        new TimeTest4().secendmethod();
    }

    public void secendmethod() {
        SimpleDateFormat t0 = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
        Calendar t1 = Calendar.getInstance();
        Date now = t1.getTime();
        System.out.println("现在的时间是:" + t0.format(now));
        t1.setTime(now);
        t1.add(Calendar.MONTH, +3);
        t1.set(Calendar.DAY_OF_MONTH, -3);//1为这个月第一天，0为上个月最后一天
        System.out.println("下两个月的倒数第四天为:" + t0.format(t1.getTime()));
    }

}
