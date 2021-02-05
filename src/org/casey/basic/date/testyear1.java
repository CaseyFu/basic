
//练习，输出1995年中的一个随机时刻
package org.casey.basic.date;
import java.util.*;
public class testyear1 {
	long secends = 1000;
	long min = 60*secends; 
	long hour = 60*min;
	long day = hour*24;
	long year = day*365;
	long a8hour = hour*8;
	long leapyear_testyear1 = (1995 - 1970)/4*day;
	long leapyear_TimeTest2 = (1999 - 1970)/4*day;
	long a1999start = (1999 - 1970)*year+leapyear_TimeTest2-a8hour;//与TimeTest2相关联
	long a1995start = (1995 - 1970)*year+leapyear_testyear1-a8hour;
	public long TimeTest2_Mainmethod()
	{
		long a2018end = a1999start + year*20 -1;
		return a2018end;
	}
	public void testyear1_Mainmethod()
	{
		System.out.println("1995年开始"+new Date(a1995start));
		long a1995end = a1995start + year - 1;
		System.out.println("1995年结束"+new Date(a1995end));
		
		for(int i=1;i<=10;i++)
		{
			long temp = (long)(Math.random()*(a1995end - a1995start) + a1995start);
			System.out.println("1995这一年中随机的一个时刻是:"+new Date(temp));
		}
	}
	public static void main(String []args)
	{
		new testyear1().testyear1_Mainmethod();
	}
}
