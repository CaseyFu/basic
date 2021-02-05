
//破解密码,用一条线程破解密码且把为匹配的密码放入容器L,用一条守护线程来取出打印这个容器中的密码,如果容器空就休息1s
package org.casey.basic.threadTest;
import java.util.ArrayList;
public class Test2{
	
	private ArrayList<String> L = new ArrayList<String>();
	
	public static void main(String []args) {
		Test2 fk = new Test2();
		fk.exe();
		
		
	}
	public void exe() {
		String password = createPassword(3);
		System.out.println("生成随机密码:"+password);
		
		Thread t = new Thread() {
			public void run() {
				//前提,不知道这个密码有多少位
				while(true) {
					String s = "";
					for(int i=0; i<3; i++) {
						byte b = (byte)(Math.random()*75+48);
						s += (char)b;
					}
					
					if(s.equals(password)) {
						System.out.println("找到匹配字符串:"+s);
						break;
					}
					
					L.add(s);
					
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
			
		Thread t0 = new Thread() {
			public void run() {
				while(true) {
					while(L.isEmpty()) {
						try {
							Thread.sleep(1000);
							System.out.println("休息1s");
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					String temp = L.remove(0);
					System.out.println(temp);
				}
			}
		};
		
		t.start();
		t0.setDaemon(true);
		t0.start();
		
	}
	
	public void print_Arr() {
		for(String s:L) {
			System.out.println(s);
		}
	}
	//生成匹配字符串
	public String createPassword(int num) {
		//i为需要生成密码的长度
		String s = "";
		for(int i=0; i<num; i++) {
			byte b = (byte)(Math.random()*75+48);
			s += (char)b;
		}
		return s;
	}
}
