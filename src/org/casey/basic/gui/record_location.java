package org.casey.basic.gui;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class record_location {
	public void execute() {
		//开一个线程来记录位置
				JFrame f = new JFrame();
				File file = new File("G:/test.txt");
				int x0=0, y0=0, width0=0, height0=0;		
				FileReader r = null;
				try {	
					r = new FileReader(file);
					char []c = new char[(int)file.length()];
					r.read(c);
					String s1 = new String(c);
					String []s0 = s1.split("@");
					x0 = Integer.parseInt(s0[0]);	//解析为int型,不用ValueOf
					y0 = Integer.parseInt(s0[1]);
					width0 = Integer.parseInt(s0[2]);
					height0 = Integer.parseInt(s0[3]);
					r.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
				f.setSize(width0, height0);
				f.setLocation(x0, y0);
				f.setLayout(null);
				
				JButton button = new JButton("哈哈哈");
				JButton button0 = new JButton("hhh");
				button0.setBounds(50,100,300,30);//x,y控制位置,width,height控制大小尺寸
				button.setBounds(50,50,300,30);
				f.add(button0);
				f.add(button);
				
				//字符流,保存位置信息到文件
				Thread t = new Thread() {
					public void run() {
						while(true) {					
							FileWriter w = null;					
							int x = f.getX();
							int y = f.getY();
							int height = f.getHeight();
							int width = f.getWidth();								
							try {
								if(!file.exists())
									file.createNewFile();
								w = new FileWriter(file);			
								String s = x+"@"+y+"@"+width+"@"+height;	
								System.out.println(s);
								w.write(s);		
								w.flush();		//立即写入文件,很有必要
								Thread.sleep(500);
								w.close();
							}catch(InterruptedException e) {
								e.printStackTrace();
							}catch (IOException e) {
								e.printStackTrace();
							}				
						}
					}
				};
				t.start();
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setVisible(true);	
	}
}
