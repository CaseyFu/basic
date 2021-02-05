
//Map与Table的区别
//HashMap和Hashtable都是Map的子类,用法都是键值对
package org.casey.basic.hash;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;//Hashtable中的table是小写开头
import java.util.Set;
public class HashMap_vs_HashTable {

	public static void main(String []args)
	{
		HashMap_vs_HashTable fk = new HashMap_vs_HashTable();
		//fk.distinction();
		fk.key_reverse_value();
	}
	public void distinction()	//区别
	{
		//1.Map允许键和值为null,table则不行
		//2.Map为非线程安全类,table则是安全类
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> table = new Hashtable<String, String>();
		
		map.put(null, "hhhh");
		map.put("ff",null);
		table.put("ff","kk");
		table.put("kk", null);	//会出现异常
		//table.put(null, "qwer");
		
		System.out.println(map.get(null));
		System.out.println(map.get("ff"));
		//System.out.println(table.get(null));
		System.out.println(table.get("ff"));
	}
	public void key_reverse_value()	//将键与值调换
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("这是键0", "这是值0");
		map.put("这是键1", "这是值1");
		map.put("这是键2", "这是值2");
		map.put("这是键3", "这是值3");
		
		//遍历Map,shuffle
		System.out.println("改变前:");
		Iterator<Entry<String, String>> I = map.entrySet().iterator();
		while(I.hasNext())
		{
			Map.Entry<String, String> temp = (Map.Entry<String, String>)I.next();
			System.out.println("<"+temp.getKey()+","+temp.getValue()+">");
		}
		
		Set<String> K = map.keySet();	//获取Map中的key到Set中
		
		Map<String, String> map0 = new HashMap<String, String>();	//创建一个新Map
		for(String s:K)
		{
			String temp_value = map.get(s);		//在老Map中,根据key找值
			map0.put(temp_value, s);	//放入新Map中
		}
		
		System.out.println("改变后:");
		
		//遍历新Map
		Iterator<Entry<String, String>> I0 = map0.entrySet().iterator();
		while(I0.hasNext())
		{
			Map.Entry<String, String> temp = (Map.Entry<String, String>)I0.next();
			System.out.println("<"+temp.getKey()+","+temp.getValue()+">");
		}
		
		
	}
}
