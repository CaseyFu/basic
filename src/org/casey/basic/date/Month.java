package org.casey.basic.date;

public class Month {
	public static void main(String []args) {
		//计算一个月中有多少天,非闰年
		int i = 9;
		if(i == 2) {	//2月失控
			System.out.println(28);
			return ;
		}
			
		
		int j = (int) Math.ceil(Math.abs(i-7.5))%2+30;
		System.out.println(j);
	}
}
