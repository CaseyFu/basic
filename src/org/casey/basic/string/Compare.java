
//比较自己写的StringBuffer与系统的StringBuffer的性能
package org.casey.basic.string;

public class Compare {
	public static void main(String []args)
	{
		StringBuffer s0 = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
		{
			s0.append(new StringTest0().getrandomstring());
		}
		long end = System.currentTimeMillis();
		System.out.println("系统:"+(end-start)+"ms");
		
		StringTest1 s1 = new StringTest1();
		start = System.currentTimeMillis();
		for(int i=0; i<1000000; i++)
		{
			s1.append(new StringTest0().getrandomstring());
		}
		end = System.currentTimeMillis();
		System.out.println("自己:"+(end-start)+"ms");
	}
	
}
