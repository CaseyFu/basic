/*
 * 用于接收信息的线程
 */
package org.casey.basic.network;
import java.net.Socket;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.IOException;
public class Thread_recive implements Runnable{
	
	private Socket socket= null;
	
	public Thread_recive(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			InputStream in = socket.getInputStream();
			DataInputStream din = new DataInputStream(in);
			while(true) {
				String recive = din.readUTF();							
				System.out.println(socket.getPort()+":"+recive);
				
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
	
}
