package org.casey.basic.hash;
//练习,自定义hashcode

import org.casey.basic.string.Test5;
public class Hashcode {
	public static void main(String []args)
	{
		for(int i=0; i<100; i++)
		{
			String s = new Test5().randomArray((int)(Math.random()*9+2));
			System.out.println(s+"的自定义hashcode是"+hashcode(s));
		}
		
	}
	public static int hashcode(String S)
	{
		if(S.length() == 0) return 0;
		int i = 0;
		char []c = S.toCharArray();
		for(char c0:c)
		{
			i += (int)c0;
		}
		if(i<0)
			i *=-1;
		return (i*21)%2000;
	}
	
}
