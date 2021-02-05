/*
 * Swing中3种线程的概念
 * 1、初始化线程,用于创建窗口中的各个组件并显示,
 * 2、事件调度线程,单线程,判断SwingUtilities.isEventDispathThread
 * 3、长耗时任务线程SwingWorker
 * 
 * SwingWorker练习进度条、查找文件
 * 
 */
package org.casey.basic.gui;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.io.File;
public class Swing_Thread {
	
	public Swing_Thread() {
		
		
	}
	
	public static void main(String[] args) {
		new Swing_Thread().SearchFile();
		//System.out.println(Swing_Thread.Traverse(new File("G:/java")));
	}
	public void skin() {
		try {
			//javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");//liquid
			//javax.swing.UIManager.setLookAndFeel("com.incors.plaf.alloy.AlloyLookAndFeel");//合金主题
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	static int count = 0;	//用于记录当前已搜索的.java文件
	static boolean searching = false;	//当前是否在搜索
	public void SearchFile() {
		//搜索是.java的文件,用SwingWorker
		skin();
		JFrame frame = new JFrame("搜索文件");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		
		JPanel p = new JPanel();
		//p.setBackground(Color.BLACK);
		p.setBounds(0, 0, 1000, 300);
		p.setLayout(null);
		
		JLabel label = new JLabel("地址:");
		label.setBounds(400, 10, 50, 50);
		p.add(label);
		
		JTextField text = new JTextField();
		text.setBounds(440, 20, 150, 30);
		p.add(text);
		
		JLabel label0 = new JLabel("进度:");
		label0.setBounds(400, 50, 50, 50);
		p.add(label0);
		
		JProgressBar bar = new JProgressBar();
		bar.setBounds(400, 90, 200, 15);
		bar.setMaximum(100);
		bar.setMinimum(0);
		bar.setValue(0);
		bar.setStringPainted(true);
		p.add(bar);
		
		JLabel label1 = new JLabel(".java文件个数:");
		label1.setBounds(400, 110, 500, 50);
		p.add(label1);
		
		JLabel label2 = new JLabel("共耗时:");
		label2.setBounds(400, 160, 500, 50);
		p.add(label2);
		
		frame.add(p);	
		
		JPanel p0 = new JPanel();
		p0.setBounds(0, 300, 1000, 300);
		JButton search_button = new JButton("搜索");
		JButton complete_button = new JButton("重新搜索");
		p0.add(search_button);
		p0.add(complete_button);
		frame.add(p0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		search_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//创建SwingWorker去搜索文件			
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					int total = 0;
					public void Traverse(File f) {
						if(f.isFile()) {
							if(f.getName().endsWith(".java")) {
								count++;
								//try {
								//	Thread.sleep(1);
								//}catch(InterruptedException e) {
								//	e.printStackTrace();
								//}
								label1.setText(".java文件个数:"+count);				
								double current = (double)count/(double)total;					
								bar.setValue((int)(current*100));			
								bar.setStringPainted(true);																																														
							}
						}else {
							for(File f0:f.listFiles()) {
								Traverse(f0);
							}
						}
					}
					protected Void doInBackground() throws Exception {	
						
						File file = new File(text.getText());
						if(!file.exists()) {
							JOptionPane.showMessageDialog(frame, "输入路径有误", "消息", 1);
							return null ;
						}
						complete_button.setText("正在搜索...");
						complete_button.setEnabled(false);
						total = Swing_Thread.Traverse(file);
												
						long start = System.currentTimeMillis();
						Traverse(file);
						long end = System.currentTimeMillis();
						label2.setText("共耗时:"+(end-start)/1000+"秒");
						complete_button.setText("重新搜索");
						complete_button.setEnabled(true);
						search_button.setEnabled(false);
						return null;
					}
				};
				
				worker.execute();
				
				
			}
		});
		complete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					count = 0;
					label1.setText(".java文件个数:");
					label2.setText("共耗时:");
					text.setText("");
					bar.setValue(0);
					search_button.setEnabled(true);			
			}
		});
	}
	public static int Traverse(File f) {
		int total = 0;
		if(f.isFile()) {
			if(f.getName().endsWith(".java")) {
				total++;
			}
		}else {
			for(File f0:f.listFiles()) {
				total += Traverse(f0);
			}
		}
		return total;
	}
}
