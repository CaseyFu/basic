/*
 * 组件练习
 * 复制文件,字节流基本单位为单个字节,用字节流不然会出现乱码,
 * FileInputStream会频繁请求IO效率不高,用BufferedInputStream
 * ,显示进度条execute5()
 */
package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
public class Component_exercise {
	public void execute0() {
		/*
		 * 判断输入的内容
		 */
		JFrame frame = new JFrame("组件的练习,判断内容是否为空");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(new FlowLayout());
		
		JTextField text = new JTextField();
		text.setPreferredSize(new Dimension(180,30));
		frame.add(text);
		
		JButton button = new JButton("检测");
		frame.add(button);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text.getText().equals("")) {
					JOptionPane.showMessageDialog(frame, "输入的内容为空", "消息", 1);
				}else if(text.getText().startsWith("A") || text.getText().startsWith("a")) {
					JOptionPane.showMessageDialog(frame, "该账号已存在", "通知", 1);
				}else {
					JOptionPane.showMessageDialog(frame, "该账号可用", "通知", 1);
				}
			}
		});*/
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char []c = text.getText().toCharArray();
				boolean b = true;
				for(char c0:c) {
					if(Character.isDigit(c0))
						continue;
					else {
						JOptionPane.showMessageDialog(frame, "不是一串数字", "通知", 1);
						b = false;
						break;
					}
				}
				if(b == true) {
					JOptionPane.showMessageDialog(frame, "可用", "通知", 1);
				}
			}
		});
	}
	public void execute1() {
		/*
		 * 账号密码登录
		 */
		JFrame frame = new JFrame("登录");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		
		JLabel lab0 = new JLabel();
		lab0.setText("用户名:");
		JTextField text0 = new JTextField();
		text0.setPreferredSize(new Dimension(170,30));
		JPanel p0 = new JPanel();
		p0.add(lab0);
		p0.add(text0);
		p0.setBounds(330,10,300,30);
		frame.getContentPane().add(p0, BorderLayout.NORTH);
		
		JLabel lab1 = new JLabel();
		lab1.setText("密码    :");
		JPasswordField pass0 = new JPasswordField();
		pass0.setPreferredSize(new Dimension(170,30));
		JPanel p1 = new JPanel();
		p1.add(lab1);
		p1.add(pass0);
		p1.setBounds(330,50,300,30);
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel();
		JButton button0 = new JButton("登录");
		JButton button1 = new JButton("注册");
		p2.add(button0);
		p2.add(button1);
		p2.setBounds(330,90,300,30);
		frame.getContentPane().add(p2, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = new String(pass0.getPassword());
				if(text0.getText().equals("FFF38524") && s.equals("2168230078..")) {
					JOptionPane.showMessageDialog(frame, "登录成功", "通知", 1);
				}else {
					JOptionPane.showMessageDialog(frame, "密码错误,登录失败", "通知", 1);
				}
			}
		});
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(frame, "该接口未开放", "通知", 1);				
			}
		});
	}
	public void execute2() {
		/*
		 * 黄鹤
		 */
		JFrame frame = new JFrame("黄鹤");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		
		JPanel p0 = new JPanel();
		JLabel lab0 = new JLabel("公司名称:");
		JTextField text0 = new JTextField();
		text0.setPreferredSize(new Dimension(170, 30));
		p0.add(lab0);
		p0.add(text0);
		p0.setBounds(20, 20, 250, 40);
		frame.getContentPane().add(p0);
		
		JPanel p1 = new JPanel();
		JLabel lab1 = new JLabel("老板名称:");
		JTextField text1 = new JTextField();
		text1.setPreferredSize(new Dimension(170, 30));
		p1.add(lab1);
		p1.add(text1);
		p1.setBounds(280, 20, 250, 40);
		frame.getContentPane().add(p1);
		
		JPanel p2 = new JPanel();
		JLabel lab2 = new JLabel("金额:");
		JTextField text2 = new JTextField();
		text2.setPreferredSize(new Dimension(170, 30));
		p2.add(lab2);
		p2.add(text2);
		p2.setBounds(540, 20, 250, 40);
		frame.getContentPane().add(p2);
		
		JButton button0 = new JButton("提交");
		button0.setBounds(240, 100, 80, 45);
		frame.getContentPane().add(button0);
		
		JPanel p3 = new JPanel();
		JLabel lab3 = new JLabel("详细:");
		JTextArea text3 = new JTextArea();
		text3.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(text3);
		scroll.setPreferredSize(new Dimension(200, 100));
		p3.add(lab3);
		p3.add(scroll);
		p3.setBounds(100, 170, 300, 100);
		frame.getContentPane().add(p3);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjj最大的互联网公司:"+text0.getText()+"hhhhhhhhhhhhhhhhhhhhhhhh的老板"+text1.getText()+"gksdfjaelifjadfj欠了"+text2.getText()+"美元，跑路了已经";
				text3.setText(s);
			}
		});
	}
	public void execute4() {
		/*
		 * 进度条,越到最后越慢
		 */
		JFrame frame = new JFrame("进度条");
		frame.setBounds(50, 50, 1000, 600);
		//frame.setLayout(null);	//窗口默认放置不是null,也不是FlowLayout
		JProgressBar progress = new JProgressBar();
		progress.setMaximum(100);
		progress.setPreferredSize(new Dimension(300,30));
		Thread t = new Thread() {
			public void run() {
				for(int i=0; i<100; i++) {
					if(i<=20) {
						progress.setValue(i);
						progress.setStringPainted(true);
						try {
							Thread.sleep(100);
							
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(i<=60 && i>20) {
						progress.setValue(i);
						progress.setStringPainted(true);
						try {
							Thread.sleep(900);
							
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(i<=80 && i>60) {
						progress.setValue(i);
						progress.setStringPainted(true);
						try {
							Thread.sleep(1900);							
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(i<=99 && i>80) {
						progress.setValue(i);
						progress.setStringPainted(true);
						try {
							Thread.sleep(3000);					
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		t.start();
		frame.getContentPane().add(progress);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void execute5() {
		/*
		 * 复制文件
		 */
		JFrame frame = new JFrame("copy");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		
		JPanel p0 = new JPanel();
		JLabel lab0 = new JLabel("sourceFile:");
		JTextField text0 = new JTextField();
		text0.setText("G:/java");
		text0.setPreferredSize(new Dimension(200,30));
		p0.add(lab0);
		p0.add(text0);
		p0.setBounds(50, 50, 200, 70);
		frame.getContentPane().add(p0, BorderLayout.WEST);
		
		JPanel p1 = new JPanel();
		JLabel lab1 = new JLabel("destinationFile:");
		JTextField text1 = new JTextField();
		text1.setText("G:/temp");
		text1.setPreferredSize(new Dimension(200,30));
		p1.add(lab1);
		p1.add(text1);
		p1.setBounds(350, 50, 200, 70);
		frame.getContentPane().add(p1, BorderLayout.WEST);
		
		JButton button = new JButton("开始复制");
		button.setBounds(50, 150, 100, 50);
		frame.getContentPane().add(button);
		
		JPanel p2 = new JPanel();
		JLabel lab2 = new JLabel("进度:");
		JProgressBar bar = new JProgressBar();
		bar.setStringPainted(true);
		bar.setPreferredSize(new Dimension(320,20));
		p2.add(lab2);
		p2.add(bar);
		p2.setBounds(200,160,390,50);
		frame.getContentPane().add(p2);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.addActionListener(new ActionListener() {		
			
			File src = new File(text0.getText()); 
			File des = new File(text1.getText());
			long all = Size(src);
			long current = 0;	//当前已复制文件的大小
			public void recursion(File source, File destination) {
				if(source.isFile()) {
					File newFile = new File(destination, source.getName());
					try {
						newFile.createNewFile();
					}catch(IOException e) {
						e.printStackTrace();
					}
					copyFile(source, newFile);
					
				}else {
					for(File file:source.listFiles()) {
						if(file.isDirectory()) {
							File newFolder = new File(destination,file.getName());
							newFolder.mkdirs();
							recursion(file, newFolder);
						}
						if(file.isFile()) {
							File newFile0 = new File(destination, file.getName());
							try {
								newFile0.createNewFile();
							}catch(IOException e) {
								e.printStackTrace();
							}
							copyFile(file, newFile0);
							current += file.length();
							double dou = (double)current/(double)all;
							int progress = (int)(dou*100);
							bar.setValue(progress);
							if(progress == 100) {
								JOptionPane.showMessageDialog(frame, "复制完成", "告知", 1);
								button.setEnabled(true);
							}
						}
					}
				}				
			}
			
			public void copyFile(File f1, File f2) {	//f1为源文件,f2为目标文件
				BufferedInputStream input = null;
				BufferedOutputStream output = null;
				byte []buffer = new byte[1024];		//缓冲区
				try {
					input = new BufferedInputStream(new FileInputStream(f1));
					output = new BufferedOutputStream(new FileOutputStream(f2));
					while(true) {
						int isover = input.read(buffer);	//获取长度,没有数据了就返回-1
						if(isover == -1) {
							break;
						}
						output.write(buffer, 0, isover);//把字节数组buffer从0的位置开始,读取isover的长度到f2中
						output.flush();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}finally {
					try {
						input.close();
						output.close();
					}catch(IOException e) {
						e.printStackTrace();
					}			
				}
			}
			public long Size(File f) {	//递归返回一个文件夹的大小
				long length = 0;
				if(f.isFile()) {
					length = f.length();
				}else {
					for(File f0:f.listFiles()) {
						length += Size(f0);
					}
				}
				return length;
			}
			public void actionPerformed(ActionEvent e) {
				//调用另一个方法去创建,
				//is a file?
				
				if (!src.exists()){
					JOptionPane.showMessageDialog(frame, "源文件路径错误", "消息", 1);
					return ;
				}
				if (des.isFile()){
					JOptionPane.showMessageDialog(frame, "目标文件路径错误", "消息", 1);
					return ;
				}
				if(!des.exists()) {
					des.mkdirs();
				}	
				button.setEnabled(false);
				//时间调度是单线程,所以开启另一个线程去执行任务,最好用SwingWorker
				Thread t = new Thread() {												
					public void run() {	
						recursion(src, des);																
					}				
				};
				t.start();								
			}			
		});
		
	}
	public static void main(String []args) {
		new Component_exercise().execute5();
	}
}
