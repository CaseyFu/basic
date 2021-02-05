/*
 * Socket
 */
package org.casey.basic.network;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
public class Client {
	public static void main(String []args) {
		Socket client = null;
		try {
			 client = new Socket("localhost",8888);
		}catch(IOException e) {
			e.printStackTrace();
		}
		new Thread (new Thread_send(client)).start();
		new Thread (new Thread_recive(client)).start();
	}
}
