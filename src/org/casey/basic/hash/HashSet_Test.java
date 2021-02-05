
//HashSet是作为Map大类的key而存在的,HashMap继承了Map,就有了键值对
package org.casey.basic.hash;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
public class HashSet_Test {
	public static void main(String []args)
	{
		
		//HashSet_Test.statistic();
		//HashSet_Test.generate();
		HashSet_Test.Onlyone();
	}
	public static void Onlyone()	//值的唯一性、顺序不确定性但固定,相同的代码不会执行一次改变一次顺序
	{
		//即使加入2个相同的值,也只会输出一个
		HashSet<String> S = new HashSet<String>();
		S.add("hhh");
		S.add("aaa");
		S.add("ddd");
		S.add("sss");
		S.add("www");
		S.add("rrr");
		S.add("hhh");
		S.add("ggg");
		System.out.println(S.size());
		
		/*遍历,无索引值,所以只能用foreach或iterator
		for(String s:S)
		{
			System.out.println(s);
		}
		System.out.println();
		Iterator<String> I = S.iterator();
		while(I.hasNext())
		{
			String s0 = I.next();
			System.out.println(s0);
		}*/
	}
	public static void statistic()
	{
		//重复字符串有多少种
		HashSet<String> S = new HashSet<String>();
		int count = 0;
		String s1 = "";
		String []s = new String[100];
		for(int i=0; i<100; i++)
		{
			s[i] = Crete_String();
			if(S.contains(s[i]) == true)
			{
				s1 += s[i] + " ";
				count++;
			}
			S.add(s[i]);	//成功增加返回1,如Set中原有则添加失败,返回0
		}
		System.out.println("重复的字符串有:"+s1+"有"+count+"种");
	}
	public static String Crete_String()	//创建2个字符的随机字符串
	{
		char []c = new char[2];
		String s0 = "";
		for(int i=0; i<2;)
		{
			//97+25
			//65+25
			//48+9
			byte b = (byte)(Math.random()*65+48);
			if((b>=48&&b<=57) || (b>=65&&b<=90) ||(b>=97&&b<=122))
			{
				c[i] = (char)b;
				i++;
			}
		}
		for(char c1:c)
			s0 += c1;
		return s0;
	}
	public static void generate()	//生成长度是0~9999的随机数字1000个,要求不能重复,用statistic检测
	{
		//数字长度必须大于个数,不然死循环
		Set<Integer> S = new HashSet<Integer>();
		int i=0;
		while(i<10)	//数字个数
		{
			int fk = (int)(Math.random()*12);	//数字的值
			boolean b = S.add(fk);
			if(b == true)
			{
				i++;
			}
		}
		System.out.println(S);
	}
}
