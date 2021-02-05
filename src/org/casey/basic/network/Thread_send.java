/*
 * 用于发送消息的线程
 */

package org.casey.basic.network;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Thread_send implements Runnable{
	private Socket socket = null;
	
	public Thread_send(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			OutputStream out = socket.getOutputStream();
			DataOutputStream din = new DataOutputStream(out);
			while(true) {
				Scanner scanner = new Scanner(System.in);
				String send = scanner.next();				
				din.writeUTF(send);		
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}	
	}
}
