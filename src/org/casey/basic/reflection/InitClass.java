/*
 * 初始化一个类,先加载声明部分,再加载块部分,最后加载构造部分
 * 获取一个类对象有3种方法,
 */
package org.casey.basic.reflection;

public class InitClass {
	static String s = "声明部分";
	static{
		s = "块部分";
		System.out.println("块部分已被执行"+s);
	}
	public InitClass() {
		s = "构造部分";
		System.out.println("构造部分已被执行"+s);
	}
	public void printf() {
		System.out.println(s);
	}
	public static void main(String []args) {
		//获取类对象的3种方式
		/*try {
			Class<?> c = Class.forName("reflection.Test");	//会初始化类		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		//Class<?> c0 = Test.class;					//不会初始化类
		//Class<?> c1 = new Test().getClass();		//会初始化类
	}
}

