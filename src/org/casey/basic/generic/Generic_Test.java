package org.casey.basic.generic;
//练习,容器中只能存放整数和浮点数,不能存放字符串
//数字类Number


import java.util.List;
import java.util.ArrayList;
public class Generic_Test {
	
	
	
	public static void main(String []args) {
		List<Number> L = new ArrayList<Number>();
		int a = 5;
		double b = 22.3;
		String s = "hhhh";
		L.add(a);
		L.add(b);
	
	}
}
