
//字符串
package org.casey.basic.string;

public class Test5 {
	public static void main(String []args)
	{
		new Test5().sortArray();;
	}
	public void randomArray()	//长度为5的随机字符数组，转化为字符串输出，要求是数字、字母；
	{
		//0为48;9为57;A为65;Z为90;a为97;z为122;
		char []c = new char[5];
		byte b;
		int i=0;
		while(i<=4)
		{
			b = (byte)(Math.random()*75+48);
			if((b>=48&&b<=57)||(b>=65&&b<=90)||(b>=97&&b<=122))
			{
				System.out.println(b);
				System.out.println();
				c[i] = (char)b;
				i++;
			}
		}
		for(char c1 :c)
			System.out.format("%s\t",c1);
	}
	public String randomArray(int i)	//随机字符串i为字符串长度
	{
		//A为65;Z为90;a为97;z为122;
		int l=0;
		char []c = new char[i];
		while(i>l)
		{	
			byte b = (byte)(Math.random()*58+65);
			if((b>=65&&b<=90) || (b>=97&&b<=122) || (b>=48&&b<=57))
			{
				c[l] = (char)b;
				l++;
			}
		}
		return new String(c);
	}
		
	public void sortArray()	//按每个字符串的首字母进行排序，不区分大小写
	{
		String []s = new String[8];
		for(int i=0; i<s.length; i++)
			s[i] = new Test5().randomArray(8);
		for(int i=0; i<s.length-2; i++)
		{
			for(int j=0; j<(s.length-1)-i; j++)
			{
				char c1 = s[j].charAt(0);
				char c2 = s[j+1].charAt(0);
				c1 = Character.toUpperCase(c1);
				c2 = Character.toUpperCase(c2);
				String temp = "";
				if((int)c1 >= (int)c2)
				{
					temp = s[j];
					s[j] = s[j+1];
					s[j+1] = temp;
				}
			}
		}
		for(String s0:s)
			System.out.println(s0);
		
	}
	public void sub_String()	//截取字符串
	{
		String s = "abcdefghijklmnopqrstuvwyxz";
		String s1 = s.substring(1);		//截取第一个位置后的字符串
		String s2 = s.substring(3,7);	//截取3到7之间的字符串
		String s3 = s.substring(3);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
	}
	public void separation()
	{
		String s = "abc,def g,hijk.lmn/opq.rst a<uv/w<xyz";
		String []s0 = s.split("");		//任何字符进行分割
		for(String s1:s0)
			System.out.println(s1);
	}
	public void _trim()		//去掉字符串的首位空格
	{
		String s = " h, h ggooff ";
		System.out.println(s.trim());
	}
	public void locate()	//定位字符串中字符或字符串的位置，字符串中是否包含某字符或某字符串
	{
		String s = "abcdefghijklabcdefghijk  labcdefghijkl";
		System.out.println(s.indexOf('a'));
		System.out.println(s.indexOf('a',4));
		System.out.println(s.indexOf("ij"));
		System.out.println(s.indexOf("ij",9));
		System.out.println(s.lastIndexOf("hi"));
		System.out.println(s.contains("hijkl"));
		System.out.println(s.contains("hjikl"));
	}
	public void replace()	//替换字符串或字符
	{
		String s = "abcdefgabcdefgabcdefgabcdefg";
		String s0 = s.replace("abc","AAA");
		System.out.println(s0);
		String s1 = s.replaceAll("ab", "BBB");
		System.out.println(s1);
		String s2 = s.replaceFirst("cde", " ");
		System.out.println(s2);
		String s3 = s.replace('c', 'O');
		System.out.println(s3);
	}
}
