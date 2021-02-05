/*
 * 获取ip地址
 * 查看网段中能连通的ip
 */
package org.casey.basic.network;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
public class Test0 {
	
	public static void derive_host() {
		//获取主机名和ip地址
		try {
			InetAddress host = InetAddress.getLocalHost();
			String ip = host.getHostAddress();
			String name = host.getHostName();
			System.out.println("主机名:"+name+"ip:"+ip);
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}	
	}
	public static void excute_command() {
		try {
			Process p = Runtime.getRuntime().exec("ping 192.168.43.26");
			BufferedReader edr = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = "";
			while((s = edr.readLine()) != null) {
				System.out.println(s);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}		
	}
	public static void judge() {
		//判断本网段的ip哪些能连通
		AtomicInteger count = new AtomicInteger(0);
		Thread []t0 = new Thread[255];
		for(int i = 1; i<=255; i++) {			
			String ip = "192.168.43."+i+"";
			Thread t = new Thread() {
				public void run() {
					try {					
						Process p = Runtime.getRuntime().exec("ping "+ip);
						BufferedReader edr = new BufferedReader(new InputStreamReader(p.getInputStream()));
						StringBuilder sk = new StringBuilder();
						String s0 = "";
						while((s0=edr.readLine()) != null) {
							sk.append(s0);
						}
						if(sk.indexOf("TTL") > 0) {
							System.out.println(ip);
							count.incrementAndGet();
						}
					}catch(IOException e) {
						e.printStackTrace();
					}	
				}
			};
			t.start();
			t0[i-1] = t;
		}
		for(Thread t1:t0) {
			try {
				t1.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(count);
	}
	
	public static void main(String []args) {
		Test0.judge();
	}
}
