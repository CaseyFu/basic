
//练习StringBuffer
package org.casey.basic.string;

public class StringTest0 {
	public static void main(String []args) throws Exception
	{
		new StringTest0().performance();
	}
	public void basic()		//基本操作
	{
		//append追加、附加
		String s0 = "01234567890123456789";
		StringBuffer s1 = new StringBuffer(s0);
		//s1.append(" hello world!h");
		s1.insert(0,"hhh");
		//s1.delete(8,10);	//[8,10),范围是前闭后开
		//s1.reverse();
		System.out.println(s1);
		//System.out.println();
		//System.out.println(s1.capacity());
		//System.out.println(s1.length());
	}
	public void performance()	throws Exception
	{
		/*String s0 = "";
		long start = System.currentTimeMillis();
		for(int i=0; i<10000; i++)
		{
			s0 += getrandomstring();
		}
		long end = System.currentTimeMillis();
		System.out.printf("String连接字符串共计使用时间%d毫秒%n",end-start);
		
		System.out.println();*/
		StringBuffer s2 = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
			s2.append(getrandomstring());
		long end = System.currentTimeMillis();
		System.out.format("StringBuffer连接字符串共计使用时间%d毫秒%n",end-start);
	}
	public String getrandomstring()
	{
		String s0 = "";
		for(int i=0; i<10; i++)
		{
			byte b = (byte)(Math.random()*95+32);
			s0 += (char)b;
		}
		return s0;
	}
	public void test() throws InterruptedException	
	{
		long start = System.currentTimeMillis();
		Thread.sleep(1000);
		long end = System.currentTimeMillis();
		System.out.printf("共计时间%d毫秒",end-start);
	}
}
