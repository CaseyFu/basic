/*
 * 各种监听,按钮监听,鼠标监听,键盘监听
 * JDialog的使用练习
 */
package org.casey.basic.gui;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;		//
public class Listener {
	public void entrance_ActionListener() {
		//反应监听
		//主窗口
		JFrame frame = new JFrame();
		frame.setSize(1000, 600);		//设置大小
		frame.setLocation(100, 100);	//设置位置
		frame.setLayout(null);			//控制JFrame中组件的相对位置
		
		//图片标签
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("G:/lol/yasuo.png");
		label.setIcon(icon);
		label.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
		frame.add(label);		
		
		//按钮
		JButton button = new JButton("隐藏图片");
		frame.add(button);
		button.setBounds(400, 50, 300, 50);
			
		//按钮点击触发
		ActionListener hidden = new ActionListener() {
			boolean is = true;
			public void actionPerformed(ActionEvent e) {
				if(is == true) {				
					label.setVisible(false);
					button.setText("显示图片");
					is = false;
				}else {				
					label.setVisible(true);
					button.setText("隐藏图片");
					is = true;
				}			
			}
		};
		
		//在按钮中增加这两个时间的监听
		button.addActionListener(hidden);	
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void entrance_KeyListener() {
		JFrame frame = new JFrame("这里是键盘监听者");
		frame.setSize(1000,600);
		frame.setLocation(50, 50);
		frame.setLayout(null);
		
		JLabel label = new JLabel();
		frame.add(label);
		
		ImageIcon imgs = new ImageIcon("G:/lol/沙皇.png");
		label.setIcon(imgs);
		label.setBounds(50, 50, imgs.getIconWidth(), imgs.getIconHeight());
		
		KeyAdapter key_Adapter = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 37) {
					label.setLocation(label.getX()-1, label.getY());
				}else if(e.getKeyCode() == 38) {
					label.setLocation(label.getX(), label.getY()-1);
				}else if(e.getKeyCode() == 39) {
					label.setLocation(label.getX()+1, label.getY());
				}else if(e.getKeyCode() == 40) {
					label.setLocation(label.getX(), label.getY()+1);
				}
				if(e.getKeyCode()==65) {
					label.setLocation(label.getX()+3, label.getY()+3);
				}
			}
			
		};
		
		KeyListener key = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println("按下了"+e.getKeyCode());
			}
			public void keyReleased(KeyEvent e) {
				System.out.println("松开了"+e.getKeyChar());
			}
			public void keyTyped(KeyEvent e) {
				//System.out.println("hhh");
			}
		};
		
		frame.addKeyListener(key_Adapter);		//必须以窗口作为监听对象
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void entrance_MouseListener() {
		JFrame frame = new JFrame("这里是鼠标监听者");
		frame.setSize(1000, 600);
		frame.setLocation(50, 50);
		frame.setLayout(null);
		
		JLabel label = new JLabel();
		frame.add(label);
		ImageIcon icon = new ImageIcon("G://lol/剑魔.png");
		label.setIcon(icon);
		label.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
		
		//用MouseAdapter可以少复写无用的方法
		MouseAdapter mouse_Adapter = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Random r = new Random();
				int x = r.nextInt(frame.getWidth() - label.getWidth());
				int y = r.nextInt(frame.getHeight() - label.getHeight());
				label.setLocation(x, y);
			}
		};
		//用MouseListener必须复写所有方法
		MouseListener mouse_Listener = new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("抓到了");
			}
			public void mouseEntered(MouseEvent arg0) {
				Random r = new Random();
				int x = r.nextInt(frame.getWidth() - label.getWidth());
				int y = r.nextInt(frame.getHeight() - label.getHeight());
				label.setLocation(x, y);
				
			}
			public void mouseExited(MouseEvent arg0) {
				System.out.println("退出了指定区域");
			}
			public void mousePressed(MouseEvent arg0) {
				System.out.println("正在按压");
			}
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("松开了鼠标");
			}
			
		};
		label.addMouseListener(mouse_Adapter);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void entrance_JDialog() {
		//触发一个JDialog窗口,里面再触发一个锁定窗口尺寸的方法
		JFrame frame = new JFrame();
		frame.setSize(1000, 600);
		frame.setLocation(50, 50);
		frame.setLayout(null);
		
		JButton button = new JButton("弹出模态窗口");
		button.setBounds(50, 50, 200, 30);	
		
		ActionListener out = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog(frame);
				dialog.setTitle("警告18禁");
				dialog.setSize(400, 250);
				dialog.setLocation(100, 100);
				dialog.setLayout(null);	
				
				dialog.setModal(true);
				JButton button0 = new JButton("锁定尺寸");
				button0.setBounds(50, 50, 200, 50);
				
				ActionListener out0 = new ActionListener() {
					boolean is = true;
					public void actionPerformed(ActionEvent e) {
						if(is == true) {
							button0.setText("解锁尺寸");
							dialog.setResizable(false);
							is = false;
						}else {
							button0.setText("锁定尺寸");
							dialog.setResizable(true);
							is = true;
						}
					}
				};			
				button0.addActionListener(out0);
				dialog.add(button0);
				
				dialog.setVisible(true);
			}	
		};
		button.addActionListener(out);
		frame.add(button);
		
		//end
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
