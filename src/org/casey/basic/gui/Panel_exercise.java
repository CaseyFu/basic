
//没有设置Layout就必须setBounds,不然不能显示
//设置了Layout可以设置size,位置由面板决定
//垂直排列要靠面板设置的大小来限制,明白了说垂直排列就是面板挤出来的
package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
public class Panel_exercise {

	public void execute() {
		JFrame frame = new JFrame("play");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.setBounds(30,50,30,30);
		p.setBackground(Color.cyan);
		String []s = {"亚索","沙皇","剑魔","盖伦","火女","提莫"};
		JButton []b = new JButton[s.length];
		for(int i=0; i<s.length; i++) {
			b[i] = new JButton(s[i]);
			b[i].setPreferredSize(new Dimension(70,30));
			p.add(b[i]);
		}
		//显示图片的面板		
		JPanel p0 = new JPanel();
		JLabel label = new JLabel();
		p0.add(label);
		for(int i=0; i<s.length; i++) {
			show(b[i],label,s[i]);
		}
			
		//结合的两个面板
		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p,p0);
		splitpane.setBounds(50, 50, 800, 400);
		splitpane.setDividerLocation(100);
		frame.getContentPane().add(splitpane);	
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void show(JButton b, JLabel tabel, String name) {
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon("G:/lol/"+name+".png");
				tabel.setIcon(icon);
			}		
		});
	}
}
