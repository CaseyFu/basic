
//字符串练习
package org.casey.basic.string;

public class Test6 {
	public static void main(String []args)
	{
		//String s = "Nature has given us that two ears, two eyes, and but one tongue, to the end that we should hear and see more than we speak";
		//new Test6().repetition();
		new Test6().repetition();
	}
	public void StartandEnd()	//是否以某一字符串开始和结束
	{
		String s = "this is java oxygen";
		String start = "thus";
		String end = "Oxygen";
		System.out.println(s.startsWith(start));
		System.out.println(s.endsWith(end));
	}
	public void repetition()		//100个随机字符串，检测其中重复的
	{
		//32~126
		String []s = new String[200];
		for(int i=0; i<s.length; i++)
		{
			s[i] = "";
			for(int j=0; j<2; j++)
			{
				byte b = (byte)(Math.random()*95+32);
				s[i] += (char)b;
			}
		}
		
		for(int i=0; i<s.length; i++)
		{
			if(i%5 == 0 && i!=0) System.out.println();
			System.out.printf("%s ",s[i]);
		}
		//重复的总数,找到相同的就给数组且把所有赋值为空
		int count = 0;
		String s0 = "";
		for(int i=0; i<s.length; i++)
		{
			for(int j=i+1; j<s.length; j++)
			{
				if(s[i].equals(s[j]) == true)
				{
					s0 += s[i] + " ";
					count++;
					for(int k=0; k<s.length; k++)
					{
						if(s[i].equals(s[k]) == true)
							s[k] = "";
					}					
				}					
			}			
		}
		System.out.printf("%n共%d种:%s%n",count,s0);
	}
	public void String_toUpperCase(String s)	//将每个单词首字母大写
	{
		String newString = "";
		String []s1 = s.split(" ");
		for(int i=0; i<s1.length; i++)
		{
			char c = Character.toUpperCase(s1[i].charAt(0));
			newString += String.valueOf(c) + s1[i].substring(1) + " ";
		}
		System.out.println(newString);
	}
	public void Count_Letter(String s,char x)	//统计字符串中以x开头的单词的个数
	{
		int count = 0;
		String []s0 = s.split(" ");
		for(int i=0; i<s0.length; i++)
		{
			if(s0[i].charAt(0) == x) count++;
		}
			
		System.out.format("以%s开头的单词有%d个",x,count);
	}
	public void UpperandLower(String s)		//间隔大小写模式，将单词school转化为ScHoOl
	{
		char []c = s.toCharArray();
		for(int i=0; i<c.length; i++)
		{
			if(i%2 == 0)
				c[i] = Character.toUpperCase(c[i]);
			else 
				c[i] = Character.toLowerCase(c[i]);
		}
		for(char c0:c)
		System.out.print(c0);
	}
	public void lastUpper(String s)		//最后一个字符变大写
	{
		char []c = s.toCharArray();
		c[c.length-1] = Character.toUpperCase(c[c.length-1]);
		for(char c0:c)
			System.out.print(c0);
	}
	public void lastvocabularyfirstUpper(String s)	//把最后一个指定单词首字母大写
	{
		char []c = s.toCharArray();
		c[s.lastIndexOf("two")] = Character.toUpperCase(s.charAt(s.lastIndexOf("two")));
		String s0 = "";
		for(char c0:c)
			s0 += c0; 
		System.out.print(s0);
	}
	public String create()	//生成一个3位数的随机字符串
	{
		//32~126
		char []c = new char[3];
		int i = 0;
		while(i<3)
		{
			byte b = (byte)(Math.random()*95+32); //[0,9)
			c[i] = (char)b;
			i++;
		}
		String s = "";
		for(char c0:c)
			s += c0;
		//System.out.format("生成随机密码是%s",s);
		return s;
	}
	public void unlock()
	{
		char []c = new Test6().create().toCharArray();
		char []c0 = new char[3];
		int k = 0;
		for(byte j=0; j<3; j++)
		{
			
			for(byte i=32; i<=126; i++)
			{
				if((char)i == c[j])
				{
					c0[k] = (char)i;
					k++;
				}
			}
		}
		System.out.print("匹配的密码是");
		for(char c1:c0) System.out.print(c1);
	}
}
