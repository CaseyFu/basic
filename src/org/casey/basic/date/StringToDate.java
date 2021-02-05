package org.casey.basic.date;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 把字符串格式的时间转化为时间标准格式
 */

public class StringToDate {
    public static void main(String[] args) {
        String str = "2017-4-23 23:48:35.2";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(str);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
