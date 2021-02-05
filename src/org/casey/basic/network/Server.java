/*
 * Socket
 */
package org.casey.basic.network;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
public class Server {
	public static void main(String []args) throws IOException{
		
			ServerSocket server = new ServerSocket(8888);
			Socket s = server.accept();		
			new Thread (new Thread_send(s)).start();
			new Thread (new Thread_recive(s)).start();
			
	}
}
