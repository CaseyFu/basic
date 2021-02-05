package org.casey.basic.file;
//数据流DataInputStream、DataOutputStream,必须建立在字节流的基础上
//数据流读取的文件必须是由数据流写的，其他流写的则会发生异常
//读取必须根据写入的顺序读取，不然会报错
//练习-向文件中写入两个数字，然后把这两个数字分别读取出来 
//分隔符@

import java.io.*;
public class DataStream {
	public static void main(String []args)	throws IOException
	{
		//DataStream.DataStream_Out();
		//DataStream.DataStream_In();
		//DataStream.exercise0();
		DataStream.exercise1();
	}
	public static void DataStream_Out()	throws IOException
	{
		//建立输出流，利用数据输出流把数据写入文件
		File f0 = new File("G:/FFF/KKK/DataStream.txt");
		FileOutputStream out = new FileOutputStream(f0);
		DataOutputStream Out = new DataOutputStream(out);
		int b = 5;
		char c = 'A';
		String s = "hhh";
		boolean b0 = true;
		Out.writeInt(b);
		Out.writeChar(c);
		Out.writeUTF(s);
		Out.writeBoolean(b0);
		Out.close();
		out.close();
		System.out.println("成功写入文件!");
	}
	public static void DataStream_In()	throws IOException
	{
		//建立输入流，利用数据输入流读取数据
		File f0 = new File("G:/FFF/KKK/DataStream.txt");
		FileInputStream in = new FileInputStream(f0);
		DataInputStream In = new DataInputStream(in);
		int i = In.readInt();
		char c = In.readChar();
		String s = In.readUTF();
		boolean b = In.readBoolean();
		
		In.close();
		in.close();
		System.out.println("整形是:"+i+"字符是:"+c+"字符串是:"+s+"布尔是:"+b);
		//System.out.println("整形是:"+i+"字符串是:"+s+"布尔是:"+b);
	}
	public static void exercise0()	throws IOException
	{
		//利用缓存流写入数字，再用缓存流读取
		File f0 = new File("G:/FFF/KKK/DataStream.txt");
		FileWriter w = new FileWriter(f0);
		BufferedWriter edw = new BufferedWriter(w);
		edw.write("99@56");//@为定义的分隔符
		edw.close();
		w.close();
		System.out.println("写入成功!");
		
		FileReader r = new FileReader(f0);
		BufferedReader edr = new BufferedReader(r);
		char []c = new char[(int)f0.length()];
		edr.read(c);
		edr.close();
		r.close();
		for(char c0:c)
		{
			if(c0 == '@') 
			{
				System.out.print(" ");
				continue;
			}	
			System.out.print(c0);
		}
	}
	public static void exercise1()	throws IOException
	{
		//数据流输入数字
		File f0 = new File("G:/FFF/KKK/DataStream.txt");
		FileOutputStream out = new FileOutputStream(f0);
		DataOutputStream Out = new DataOutputStream(out);
		Out.writeInt(89);
		Out.writeInt(67);
		Out.close();
		out.close();
		System.out.println("写入完成");
		 
		FileInputStream in = new FileInputStream(f0);
		DataInputStream In = new DataInputStream(in);
		int i = In.readInt();
		int j = In.readInt();
		In.close();
		in.close();
		System.out.println("读取的数字是:"+i+"、"+j);
	}
}
