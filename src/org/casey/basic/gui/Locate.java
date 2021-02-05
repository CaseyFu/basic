/*
 * 未设置Layout时，java默认为flowLayout布局的，设置为null即为清空布局管理器，之后添加组件，
常常是设置组件左上角坐标相对于容器左上角（0，0）的x,y值来确定组件的位置，
即使更改容器大小也不会改变位置。这种方式常常用于窗体大小固定的容器里。
 * 布局定位,组件的排列方式
 */
package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
public class Locate {
	public void locatedAbsolute() {
		JFrame frame = new JFrame("这里是绝对定位");
		frame.setSize(1000, 600);
		frame.setLocation(50, 50);
		//如未设置布局方式,组件的大小就需要自定义,
		frame.setLayout(null);
		
		JButton button = new JButton("1");
		button.setBounds(100, 100, 50, 50);	//对按钮的位置及大小进行定义,没有定义位置和大小就不会出现在窗口中
		frame.add(button);
		
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("G:/lol/沙皇.png");
		label.setIcon(icon);
		label.setBounds(150, 150, icon.getIconWidth(), icon.getIconHeight());
		frame.add(label);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void locatedFlowLayout() {
		JFrame frame = new JFrame("这里是水平定位,每个组件按水平顺序排列");
		frame.setSize(1000, 600);
		frame.setLocation(50, 50);
		//设置了布局方式就不设置组件大小
		frame.setLayout(new FlowLayout());
		
		JButton button = new JButton("1");
		//new Dimension()只在FlowLayout中起作用
		button.setPreferredSize(new Dimension(50,50));	//在设置了布局方式的窗口中设置组件大小用new Dimension(x,y)
		frame.add(button);
		
		JButton button0 = new JButton("2");
		frame.add(button0);
		
		JButton button1 = new JButton("3");
		frame.add(button1);
		
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("G:/lol/沙皇.png");
		label.setIcon(icon);
		frame.add(label);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void locatedBorderLayout() {
		JFrame frame = new JFrame("这里是水平定位,每个组件按水平顺序排列");
		frame.setSize(1000, 600);
		frame.setLocation(50, 50);
		//如未设置布局方式,组件的大小就需要自定义,设置了布局方式就不需要设置组件大小
		frame.setLayout(new BorderLayout());
		
		JButton button = new JButton("1");
		frame.add(button, BorderLayout.CENTER);
		
		JButton button0 = new JButton("2");
		frame.add(button0, BorderLayout.NORTH);
		
		JButton button1 = new JButton("3");
		frame.add(button1, BorderLayout.EAST);
		
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("G:/lol/沙皇.png");
		label.setIcon(icon);
		label.setBounds(50, 50, icon.getIconWidth(), icon.getIconHeight());
		frame.add(label, BorderLayout.WEST);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void locatedGridLayou() {
		JFrame frame = new JFrame("这里是网格定位");
		frame.setSize(1000, 600);
		frame.setLocation(50, 50);
		//如未设置布局方式,组件的大小就需要自定义,设置了布局方式就不需要设置组件大小
		frame.setLayout(new GridLayout(2,2));//无参构造默认排在一行(x,y)x行,y列
		
		JButton button = new JButton("1");
		frame.add(button);
		
		JButton button0 = new JButton("2");
		frame.add(button0);
		
		JButton button1 = new JButton("3");
		frame.add(button1);
		
		JLabel label = new JLabel();
		ImageIcon icon = new ImageIcon("G:/lol/沙皇.png");
		label.setIcon(icon);
		frame.add(label);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
