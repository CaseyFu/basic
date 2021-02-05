package org.casey.basic.generic;


import java.util.ArrayList;
import java.util.Iterator;

//容器的遍历
public class Iterator_Test {
	public static void main(String []args)
	{
		ArrayList<Hero> List = new ArrayList<Hero>();
		for(int i=0; i<100; i++)
		{
			List.add(new Hero("HeRo"+i));
		}
		Iterator_Test.method3(List);	
	}
	public static void method0(ArrayList<Hero> L)
	{
		//普通for遍历容器
		for(int i=0; i<L.size(); i++)
		{
			Hero h = L.get(i);
			System.out.println(h.getName());
		}
	}
	public static void method1(ArrayList<Hero> L)
	{
		//Iterator遍历容器,迭代器是一个头结点为空的链表,初始指针指向头结点
		Iterator<Hero> diedai0 = L.iterator();
		while(diedai0.hasNext())
		{
			Hero h0 = (Hero)diedai0.next();
			System.out.println(h0.getName());
		}
		System.out.println();
		for(Iterator<Hero> diedai1 = L.iterator(); diedai1.hasNext();)
		{
			Hero h1 = (Hero)diedai1.next();
			System.out.println(h1.getName());
		}
	}
	public static void method2(ArrayList<Hero> L)
	{
		//foreach
		//Hero []h0 = new Hero[(int)L.size()];
		//L.toArray(h0);
		
		for(Hero h1:L)	//因为指定了L为Hero泛型,所以相当于一个Hero数组
		{
			System.out.println(h1.getName());
		}	
	}
	public static void method3(ArrayList<Hero> L)	//通过遍历,删除类名为8的倍数的Hero
	{
		//用迭代器Iterator
		ArrayList<Hero> De = new ArrayList<Hero>();
		for(Iterator<Hero> diedai = L.iterator(); diedai.hasNext();)
		{
			Hero h = (Hero)diedai.next();
			String s = h.getName();
			int i = Integer.valueOf(s.substring(4));
			//System.out.println(i);
			if(i%8 == 0)
			{
				De.add(h);//不能L.remove(h)因为h只是一个临时变量,它与容器只有单方面练习,只传出不传进
				//不能用L.remove(i)的原因是每删一个容器自动排列就像用ArrayList写的MyString的delete一样,每一个元素都从开头开始删
			}
		}
		//System.out.println(L.size());
		L.removeAll(De);
		for(Hero h0:L)
			System.out.println(h0.getName());
	}
}
