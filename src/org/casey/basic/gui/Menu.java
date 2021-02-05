/*
 * 菜单栏
 * 工具栏
 */
package org.casey.basic.gui;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
public class Menu {
	public void execute() {
		JFrame frame = new JFrame("Menu");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(new BorderLayout());
		
		//菜单
		JMenuBar menubar = new JMenuBar();
		String []menu = {"文件(F)","编辑(E)","格式(O)","查看(V)","帮助(H)"};
		JMenu []menu0 = new JMenu[menu.length];
		for(int i=0; i<menu.length; i++) {
			menu0[i] = new JMenu(menu[i]);
		
			menubar.add(menu0[i]);
		}	
		String [][]item = {{"新建","打开","保存","另存为","页面设置","打印","退出"},
				{"撤销","剪切","复制","粘贴","删除","查找","查找下一个","替换","转换","转到","全选","时间/日期"},
				{"自动换行","字体"},
				{"状态栏"},
				{"帮助","关于记事本"}
		};
		for(int i=0; i<item.length; i++) {
			for(int j=0; j<item[i].length; j++) {
				JMenuItem option = new JMenuItem(item[i][j]);
				if(i==0 && (j == 4 || j==6)) {	//设置分割线
					menu0[i].addSeparator();
				}
				if(i==1 && (j == 1 || j==5 || j==11)) {	//设置分割线
					menu0[i].addSeparator();
				}
				if(i== 4 && j == 1) {	//设置分割线
					menu0[i].addSeparator();
				}			
				menu0[i].add(option);
			}						
		}			
		frame.setJMenuBar(menubar);
		
		//工具栏
		JToolBar tool = new JToolBar();
		tool.setBounds(0,0,1000,100);
		JButton []b = new JButton[6];
		for(int i=0; i<6; i++) {
			b[i] = new JButton(new ImageIcon("G:/lol/"+(i+1)+".png"));
			if(i==0) {
				b[i].setToolTipText("滑板鞋");
			}
			tool.add(b[i]);
		}
		
		tool.setFloatable(false);	//prohibit drag
		frame.getContentPane().add(tool, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
