package org.casey.basic.file;
//自动化创建一个类

import java.util.*;
import java.io.*;
public class AutoClass {
	public static void main(String []args)	throws IOException
	{
		AutoClass.Create();
	}
	public static void Create()	throws IOException
	{
		Scanner s = new Scanner(System.in);
		System.out.println("类名");
		String name = s.nextLine();
		System.out.println("属性类型");
		String type = s.nextLine();
		System.out.println("数据");
		String data = s.nextLine();
		System.out.println("属性名称");
		String typename = s.nextLine();
		s.close();
		File f0 = new File("f:/src.java");	//源模板文件
		FileReader r = new FileReader(f0);
		BufferedReader edr = new BufferedReader(r);

		File f1 = new File("f:/work.java");	//目标文件
		PrintWriter w = new PrintWriter(f1);
		BufferedWriter edw = new BufferedWriter(w);
		String s0 = "";
		while((s0 = edr.readLine())!=null)
		{
			if(s0.contains("@class@"))
				s0 = s0.replaceAll("@class@", name);
			if(s0.contains("@type@"))
				s0 = s0.replaceAll("@type@", type);
			if(s0.contains("@property@"))
				s0 = s0.replaceAll("@property@", typename);
			if(s0.contains("@Uproperty@"))
			{
				char c0 = Character.toUpperCase(typename.charAt(0));
				String s1 = typename.substring(1);
				String s2 = c0 + s1;
				s0 = s0.replaceAll("@Uproperty@", s2);
			}
			if(s0.contains("@data@"))
				s0 = s0.replaceAll("@data@", data);		
			w.println(s0);
			System.out.println(s0);
		}
		edr.close();
		r.close();
		System.out.println("读取成功");
		edw.close();
		w.close();
		System.out.println("已写入文件");
	}
}
