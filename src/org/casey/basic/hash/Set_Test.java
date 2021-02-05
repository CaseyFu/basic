
//几种常用的Set容器
package org.casey.basic.hash;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.HashSet;
public class Set_Test {
	
	public static void main(String []args)
	{
		Set_Test fk = new Set_Test();
		//fk.the_Set();
		fk.print_PI();
	}
	public void the_Set()
	{
		//几种常用的Set
		Set<Integer> s = new LinkedHashSet<Integer>();	//插入顺序
		s.add(3);
		s.add(44);
		s.add(1);
		System.out.println("LinkedHashSet:"+s);
		
		Set<Integer> s0 = new TreeSet<Integer>();	//从小到大顺序
		s0.add(3);
		s0.add(44);
		s0.add(1);
		System.out.println("TreeSet:"+s0);
		
		Set<Integer> s1 = new HashSet<Integer>();	//乱序shuffle
		s1.add(3);
		s1.add(44);
		s1.add(1);
		System.out.println("HashSet:"+s1);
	}
	public void print_PI()	//打印PI
	{
		//打印PI,利用LinkedHashSet的按插入顺序和值的唯一性
		Set<Character> S = new LinkedHashSet<Character>();
		String s = String.valueOf(Math.PI);
		//System.out.println(s);
		char []c = s.toCharArray();
		for(char c0:c)
		{
			if(c0 == '.') continue;
			S.add(c0);
		}
		System.out.println(S);
	}
}
