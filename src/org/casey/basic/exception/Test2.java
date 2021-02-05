package org.casey.basic.exception;
//给自己写的StringBuffer自定义异常;



interface IsBufferString
{
	public void append(char c)	throws IndexIsNagetiveException,IndexIsOutofRangeException;
	public void append(String s)	throws IndexIsNagetiveException,IndexIsOutofRangeException;
	public void insert(int i,char c)	throws IndexIsNagetiveException,IndexIsOutofRangeException;
	public void insert(int i,String s)	throws IndexIsNagetiveException,IndexIsOutofRangeException;
	public void delete(int start)	throws IndexIsNagetiveException,IndexIsOutofRangeException;
	public void delete(int start,int end)	throws IndexIsNagetiveException,IndexIsOutofRangeException;
	public String toString();
	
	
}
public class Test2 implements IsBufferString{
		private char []c = new char[20];;
		private int index = 0;
		private int initlen = 20;
		public static void main(String []args)	
		{
			String s = "hhh";
			Test2 s0 = new Test2(s);
			String s1 = null;
			char c0 = 'a';
			s0.delete(5, 4);
			System.out.println(s0.toString());
		}
		public String toString()
		{
			String s = "";
			for(int i=0; i<index; i++ )
				s += c[i];
			return s;
		}
		public Test2()
		{
			
		}
		public Test2(String s)
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
			if(i<0)
			{
				try {
					throw new IndexIsNagetiveException("下标为负异常");
					
				}
				catch(IndexIsNagetiveException  e )
				{
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			if(i>index)
			{
				try
				{
					throw new IndexIsOutofRangeException("下标出界异常");
				}
				catch(IndexIsOutofRangeException e)
				{
					e.printStackTrace();
				}
			}
			if(s==null)
			{
				try
				{
					throw new NullPointerException("空指针异常");
				}
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
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
			if(start < 0 || end < 0)
			{
				try
				{
					throw new IndexIsNagetiveException("下标为负异常");
				}
				catch(IndexIsNagetiveException e)
				{
					e.printStackTrace();
				}
			}
			if(start > index || end > index ||  start >= end)
			{
				try
				{
					throw new IndexIsOutofRangeException("下标出界异常");
				}
				catch(IndexIsOutofRangeException e)
				{
					e.printStackTrace();
				}
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
