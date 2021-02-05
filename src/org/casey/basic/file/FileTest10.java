package org.casey.basic.file;
//练习,遍历c:/windows，找出最大的文件和最小的文件


import java.io.*;
public class FileTest10 {
	
	static long maxvalue = 0;
	static long minvalue = Integer.MAX_VALUE;
	static File Max = null,Min = null;
	public static void main(String []args)
	{
		long start = System.currentTimeMillis();
		File f = new File("C:/Windows");
		new FileTest10().exe0(f);
		long end = System.currentTimeMillis();
		
		System.out.printf("最大的文件是:%s大小是%d字节路径是:%s",Max.getName(),Max.length(),Max.getAbsolutePath().toString());
		System.out.printf("最小的文件是:%s大小是%d字节路径是:%s",Min.getName(),Min.length(),Min.getAbsolutePath().toString());
		System.out.printf("共耗时%ds",(end-start)/1000);
	}
	public void exe()
	{
		int min = 0,max = 0;
		File f0 = new File("C:/Windows");
		File []f1 = f0.listFiles();		//返回当前目录下的所有文件和文件夹
		System.out.println("Windows目录下的文件有:");
		long maxvalue = 0,minvalue = Integer.MAX_VALUE;
		for(int i=0; i<f1.length; i++)
		{
			if(f1[i].isDirectory())
				continue;
			System.out.println(f1[i].getName());
			if(f1[i].length() >= maxvalue) 
			{
				maxvalue = f1[i].length();
				max = i;
			}
			if(f1[i].length() <= minvalue && f1[i].length() > 0)
			{
				minvalue = f1[i].length();
				min = i;
			}
		}
		System.out.format("最大的文件是:%s大小是:%d字节%n",f1[max].getPath().toString(),maxvalue);
		System.out.printf("最小的文件是:%s大小是:%d字节%n",f1[min].getPath().toString(),minvalue);
		
	}
	
	public void exe0(File f)
	{
		//对当前目录f及子目录进行遍历，并找出最大与最小文件
		if(f.isFile())
		{
			if(f.length() >= maxvalue)
			{
				maxvalue = f.length();
				Max = f;
			}
			if(f.length() <= minvalue && f.length() > 0)
			{
				minvalue = f.length();
				Min = f;
			}
			return ;
		}
	
		if(f.isDirectory())
		{
			File []f0 = f.listFiles();
			if(f0!=null)
			{
				for(File f1:f0)
				exe0(f1);
			}
		}
		
	}
}
