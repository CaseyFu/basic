/*
 * 计算器
 */
package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
public class Calculator {
	public void execute() {
		JFrame frame = new JFrame("计算器");
		frame.setSize(400, 500);
		frame.setLocation(50, 50);
		frame.setLayout(new FlowLayout(2,15,15));
		//参数(x,y,z)		x:0左对齐1居中2右对齐;	y:水平间距;	z:垂直间距
		
		String []s = {"0","1","2","3","4","5","6","7","8","9","+","-","*","/","="};
		for(int i=0; i<s.length; i++) {
			JButton b = new JButton(s[i]);
			b.setPreferredSize(new Dimension(90,50));
			frame.add(b);
		}
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
