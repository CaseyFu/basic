/*
 * 各种组件
 */

package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;		//静态类
import javax.swing.JTextField;		//输入框,单行
import javax.swing.JTextArea;
import javax.swing.JPasswordField;	//密码输入框,密码变成小黑点
import javax.swing.JProgressBar;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
//import java.io.FileFilter;	
import javax.swing.filechooser.FileFilter;//文件过滤器
public class Components {
	public void execute() {
		JFrame frame = new JFrame("这里是组件");
		frame.setSize(1000,600);
		frame.setLocation(50, 50);
		frame.setLayout(new FlowLayout());
		//单选框
		JRadioButton radiobutton0 = new JRadioButton("单选0"); 
		JRadioButton radiobutton1 = new JRadioButton("单选1");
		//frame.add(radiobutton0);
		//frame.add(radiobutton1);
		ButtonGroup team = new ButtonGroup();	//把两个单选放在一个按钮组下
		team.add(radiobutton0);
		team.add(radiobutton1);
		
		//复选框
		JCheckBox checkbox0 = new JCheckBox("A选项");
		JCheckBox checkbox1 = new JCheckBox("B选项");
		//frame.add(checkbox0);
		//frame.add(checkbox1);
		
		//下拉框
		String []combo = {"沙皇","盖伦","亚索","瞎子"};
		JComboBox<String> combobox = new JComboBox<>(combo);
		//frame.add(combobox);
		
		//消息提示框,优先出现	
		JOptionPane.showInputDialog(frame,"这里是输入框");
		JOptionPane.showConfirmDialog(frame, "这里是确认框,请点击是,谢谢");
		JOptionPane.showMessageDialog(frame, "这里是展示信息的框", "来自网页的消息", 1);
		
		//输入框,单行,多行用JTextArea
		JTextField userField = new JTextField("");
		userField.setText("请输入账号");	//设置值
		userField.setPreferredSize(new Dimension(300,400));
		//frame.add(userField);
		
		//文本域JTextArea
		JTextArea area = new JTextArea();
		area.setText("这里是文本域");
		area.setPreferredSize(new Dimension(100,500));
		area.append("这里是追加的内容");
		area.setLineWrap(true);  	//自动换行
		//frame.add(area);
		
		//密码输入框,获取密码是返回一个[]char
		JPasswordField password = new JPasswordField("");
		password.setPreferredSize(new Dimension(100,50));
		password.setText("FFF38524");
		//char []c = new char[30];
		//c = password.getPassword();
		//System.out.println(new String(c));
		//frame.add(password);
		
		//进度条JProgressBar
		JProgressBar progressbar = new JProgressBar();
		progressbar.setMaximum(100);	//设置最大
		new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<=100; i++) {			
					progressbar.setValue(i);		//设置当前
					progressbar.setStringPainted(true);//显示当前
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();	
		frame.add(progressbar);	
		
		//文件选择
		JFileChooser fc = new JFileChooser();
		FileFilter filefilter = new FileFilter() {
			public boolean accept(File arg0) {			
				return arg0.getName().toLowerCase().endsWith(".txt");
			}
			public String getDescription() {
				return ".txt";
			}			
		};
		fc.setFileFilter(filefilter); 	 //筛选文件
		
		JButton b0 = new JButton("打开文件");
		ActionListener bl0 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = fc.showOpenDialog(frame);
				File f = fc.getSelectedFile();
				if(option == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(frame, "打开文件"+f.getAbsolutePath());
				}
			}		
		};
		//b0.addActionListener(bl0);
		//frame.add(b0);
		
		JButton b1 = new JButton("保存文件");
		ActionListener bl1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = fc.showOpenDialog(frame);
				File f = fc.getSelectedFile();
				if(option == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(frame, "保存文件"+f.getAbsolutePath());
				}
			}
		};
		//b1.addActionListener(bl1);
		//frame.add(b1);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
