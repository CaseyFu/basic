package org.casey.basic.exception;
//最简单的抛出异常，用try-catch机制，捕捉每一个对应的异常

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Test0 {
	public static void main(String []args)
	{
		//Test_Log4j.filenotfound();
		//Test_Log4j.parse();
		//System.out.println(Test_Log4j.re());
		Test0.throwable();
	}
	public static void throwable()	//捕捉Exception和Error的父类throwable
	{
		File f0 = new File("G:/java/hh.txt");
		try
		{
			System.out.println("用throwable捕捉异常");
			new FileInputStream(f0);
			
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	public static int re()
	{
		int a=2,b=1;
		
		try
		{
			int c = a/b;
			return 1;
		}
		catch(ArithmeticException e)
		{
			e.printStackTrace();
			return 2;
		}
		finally
		{
			return 3;
		}
	}
	public static void filenotfound()	//打开文件,体验异常
	{
		File f0 = new File("G:/java/hh.txt");
		FileInputStream in= null;
		byte []b = new byte[(int)f0.length()];
		try
		{
			System.out.println("打开hhh.txt");
			 in = new FileInputStream(f0);
			 in.read(b);
			System.out.println("打开成功");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("hhh.txt打开失败");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("读取文件是出现错误");
			e.printStackTrace();
		}
		finally
		{
			System.out.println(new String(b));
			try
			{
				in.close();
			}
			catch(IOException e)
			{
				System.out.println("关闭文件出现错误");
				e.printStackTrace();
			}
		}
	}
	public static void parse()	//将文本格式时间改为日期格式
	{
		//先把文本时间格式解析为日期时间格式,再将日期时间格式自定义为自己希望的格式
		String text0 = "2018年3月4日;9:44:33";
		SimpleDateFormat f0 = new SimpleDateFormat("yyyy年MM月dd日;HH:mm:ss");
		Date text1 = null;
		try
		{
			System.out.println("解析文本格式时间到日期格式时间");
			text1 = f0.parse(text0);
			System.out.println("解析成功");
			
		}
		catch(ParseException e)
		{
			System.out.println("解析文本时失败");
			e.printStackTrace();
		}
		
		//日期转文本
		SimpleDateFormat f1 = new SimpleDateFormat("这是yyyy年MM月的dd号,今天是个好日子.现在的时间是北京时间HH点整mm分ss秒");
		String text2 = f1.format(text1);
		
		System.out.println(text2);
		
	}
}
