/*
 * 打开一个包中的所有java文件,用TabbedPane
 * 
 */
package org.casey.basic.gui;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
public class OpenPacage {
	public void execute() {
		//选择包
		File file = new File("H:/eclipse/workspace/class/src/LinkList");
		File []f = file.listFiles(new FileFilter() {
			public boolean accept(File arg0) {
				return arg0.getName().endsWith(".java");
			}
		});

		JFrame frame = new JFrame("eclipse");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(new FlowLayout());
		
		JTabbedPane tbp = new JTabbedPane();
		
		for(int i=0; i<f.length; i++) {
			JTextArea t = new JTextArea();
			t = new JTextArea();
			t.setLineWrap(true);
			String s = "";
			FileReader print = null;
			BufferedReader edr = null;
			try {
				print = new FileReader(f[i]);
				edr = new BufferedReader(print);	
				while( (s=edr.readLine()) != null) {
					t.append(s + "\n");		//读一行,追加进TextArea一行
				}	
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					edr.close();
					print.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			JScrollPane scroll = new JScrollPane(t);	
			scroll.setPreferredSize(new Dimension(950, 450));
			ImageIcon icon = new ImageIcon("G:/lol/j.png");
			tbp.add(f[i].getName(), scroll);
			
			tbp.setIconAt(i, icon);
		}
		
		frame.getContentPane().add(tbp);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
