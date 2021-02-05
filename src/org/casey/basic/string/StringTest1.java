
//自己写StringBuffer;
//StringBuffer中有append、insert、delete、reverse、length
package org.casey.basic.string;

public class StringTest1 {
	private char []c = new char[20];;
	private int index = 0;
	private int initlen = 20;
	public static void main(String []args)	
	{
		//String s0 = "01234567890123456789"; //20个标志字符
		StringTest1 s1 = new StringTest1();
		//s1.append('s');
		//s1.append("012ggggggggggggggggggggggggggggggggggggggggggggg");
		//s1.insert(0,'@');
		//s1.insert(20, "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		//s1.reverse();
		//s1.delete(3);
		System.out.println(s1.toString());
		System.out.println("长度"+s1.length());
	}
	public String toString()
	{
		String s = "";
		for(int i=0; i<index; i++ )
			s += c[i];
		return s;
	}
	public StringTest1()
	{
		
	}
	public StringTest1(String s)
	{
		while(initlen <= s.length())
		{
			initlen = initlen+s.length();
			char []c = new char[initlen];
			this.c = c;
		}
		char []c = s.toCharArray();
		for(int i=0; i<c.length; i++)
		{
			this.c[i] = c[i];
			index++;
		}	
	}
	public void append(char c)	//追加字符
	{
		insert(index,String.valueOf(c));
	}
	public void append(String s)	//追加字符串
	{
		insert(index,s);
	}
	public void insert(int i,char c)//插入第i个位置，值为c
	{
		insert(i,String.valueOf(c));
	}
	public void insert(int i,String s)
	{
		if(i<0 || i>index)
		{
			System.out.println("输入位置错误!");
			return ;
		}
		if(s==null)
		{
			System.out.println("空字符串!");
			return ;
		}
		while(initlen - index <= s.length())
		{
			initlen = (int)((s.length()+index)*(1.5f));
			char []c = new char[initlen];
			System.arraycopy(this.c, 0, c, 0, index);
			this.c = c;
		}
		char []c = s.toCharArray();
		System.arraycopy(this.c, i, this.c, i+c.length, index-i);
		System.arraycopy(c, 0, this.c, i, c.length);
		index += c.length;
	}
	public void delete(int start)	//删除start以后的，包括start
	{
		delete(start,index);
	}
	public void delete(int start,int end)	//删除[start,end)的
	{
		if(start<0 || start>index || end<0 || end>index || start>=end)	//让end可以等于index则可以删除最后一个元素
		{
			System.out.println("输入错误!");
			return ;
		}
		//删除即减少index，再把元素移位
		System.arraycopy(c, end, c, start, index - end);
		index -= end-start;		
	}
	public void reverse()	//颠倒字符串
	{	
		char tempchar;
		for(int i=0; i<index/2; i++)
		{
			tempchar = this.c[i];
			this.c[i] = this.c[index - i - 1];
			this.c[index - i - 1] = tempchar;
		}	
	}
	public int length()	//获取字符串长度
	{
		int i = index;
		return i;
	}
}
