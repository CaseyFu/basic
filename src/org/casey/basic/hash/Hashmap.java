
//HashMap,数组+链表结构;
//底层为数组,连接一个结点
package org.casey.basic.hash;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
public class Hashmap {
	
	public static void main(String []args)
	{
		Hashmap.compare();
	}
	public static void exe()
	{
		//键值对,<key,value>
		//唯一主键key,插入元素键相同时,会覆盖原有值
		Map<String,String> H = new HashMap<String,String>();
		H.put("38524", "this is fukai");
		H.put("385245","this is fukai5");
		H.put("3852","this is fukai2");
		H.put("3852456", "hg");
		//System.out.println(H.get("38524"));
		//遍历HashMap
		Iterator<Entry<String,String>> die = H.entrySet().iterator();
		while(die.hasNext())
		{
			//Map.Entry是一个类,专门存放Iterator的临时对象,以此来分别输出键和值
			Map.Entry<String, String> s = (Map.Entry<String, String>) die.next();
			String key = s.getKey();
			String value = s.getValue();
			System.out.println("<"+key+","+value+">");
		}
	}
	public static void compare()
	{
		//找出3000000个英雄对象中名字为Hero-5555的所有英雄
		//比较ArrayList与HashMap的性能
		int ArrayCount = 0;
		ArrayList<Hero> L = new ArrayList<Hero>();
		for(int i=1; i<=3000000; i++)
		{
			L.add(new Hero("Hero-"+(int)(Math.random()*9000+1000)));
		}
		long ArrayStart = System.currentTimeMillis();
		for(Hero H:L)
		{
			//System.out.println(H.GetName());
			if(H.GetName().equals("Hero-5555"))
				ArrayCount++;
		}
		long ArrayEnd = System.currentTimeMillis();
		System.out.println("ArrayList耗费的时间是:"+(float)(ArrayEnd-ArrayStart)+"Hero-5555的个数是:"+ArrayCount);
		
		//既然是插入同名的键会覆盖原有的值,那问题就是怎么插入同名的键不会覆盖原有的值
		//为每一个不同名字的Hero建一个容器;		键为英雄名、值为容器,把所有同名的英雄名放在一个容器下;相当于HTML的下拉表单
		//只要Map.get出相应英雄名的容器，即可统计有多少名为Hero-5555的英雄
		HashMap<String,ArrayList<Hero>> M = new HashMap<String,ArrayList<Hero>>();
		ArrayList<Hero> list = null;
		for(Hero H:L)
		{
			 list = M.get(H.GetName());	//键值查找,返回指定英雄名的容器
			if(list == null)	//如果是一个未加入Map的容器
			{
				list = new ArrayList<Hero>();	//为容器分配空间
				M.put(H.GetName(), list);	//把名字,容器;键值对加入Map
			}
			list.add(H);	//把英雄放入相应的容器中
		}
		
		long MapStart = System.currentTimeMillis();
		ArrayList<Hero> temp = M.get("Hero-5555");
		long MapEnd = System.currentTimeMillis();
		System.out.println("HashMap耗费的时间是:"+(float)(MapEnd-MapStart)+"Hero-5555的个数是:"+temp.size());
		
		
		
	}
}
