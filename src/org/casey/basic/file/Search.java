package org.casey.basic.file;

import java.io.*;

//遍历一个文件夹，寻找包含某数据的数据

public class Search {
	public static void main(String []args)	throws IOException
	{
		//遍历G:/FFF
		File f0 = new File("G:/FFF");
		Search.Find(f0);
	}
	public static void Find(File f)	throws IOException
	{
		for(File f0:f.listFiles())
		{
			if(f0.isDirectory())
				Find(f0);
			
			if(f0.isFile())
			{
				String s = "";
				FileReader r = new FileReader(f0);
				BufferedReader edr = new BufferedReader(r);
				while((s = edr.readLine())!=null)
				{
					if(s.contains("java"))
						System.out.println("包含java的文件有:"+f0.getAbsolutePath());
				}
				edr.close();
				r.close();
			}
		}
	}
}
