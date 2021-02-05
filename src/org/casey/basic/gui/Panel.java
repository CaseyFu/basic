//各种面板
package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Dimension;

public class Panel {
	public void execute() {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(1000,600));
		frame.setLocation(50, 50);
		frame.setLayout(new FlowLayout());
		
		//把按钮放在Panel中
		JPanel p = new JPanel();
		p.setBackground(Color.BLUE);	
		JButton b0 = new JButton("button0");
		JButton b1 = new JButton("button1");
		JButton b2 = new JButton("button2");
		p.add(b0);
		p.add(b1);
		p.add(b2);
		p.setVisible(true);
		//frame.getContentPane().add(p);等价于frame.add(p);
		
		//分离的面板
		JPanel p0 = new JPanel();
		p0.setBackground(Color.cyan);
		JButton b3 = new JButton("button3");
		JButton b4 = new JButton("button4");
		JButton b5 = new JButton("button5");
		p0.add(b3);
		p0.add(b4);
		p0.add(b5);
		//JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,p,p0);	//设置锤子分离,还有水平分离
		//splitpane.setDividerLocation(70);	//设置分割线的位置
		//frame.getContentPane().add(splitpane);
		
		//滚动面板JScrollPane
		JTextArea text = new JTextArea();
		for(int i=0; i<1000; i++) {
			text.append(String.valueOf(i)+"   ");
		}
		text.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(text);		//利用文本域组件来构建JScrollPane
		scroll.setPreferredSize(new Dimension(200,100));
		//frame.getContentPane().add(scroll);
		
		//标签面板JTabbedPane,如eclipse的页面
		JTabbedPane tab = new JTabbedPane();	
		tab.add("blue", p);			//添加一个标签
		tab.add("cany", p0);
		ImageIcon icon = new ImageIcon("G:/lol/j.png");
		tab.setIconAt(0, icon);		//标签的图标
		tab.setIconAt(1, icon);
		//frame.getContentPane().add(tab);
		
		
		frame.setVisible(true);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void testCardLayout() {
		//CardLayout
		JFrame frame = new JFrame("CardLayout");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(new FlowLayout(0, 50, 50));
		
		//面板1
		JPanel p1 = new JPanel();
		p1.setBackground(Color.blue);
		p1.setLayout(new FlowLayout(1, 30, 30));
		p1.setPreferredSize(new Dimension(400, 150));
		p1.add(new JButton("button1"));
		p1.add(new JButton("button2"));
		p1.add(new JButton("button3"));
		
		//面板2
		JPanel p2 = new JPanel();
		p2.setBackground(Color.cyan);
		p2.setLayout(new FlowLayout(1, 30, 30));
		p2.setPreferredSize(new Dimension(400, 150));
		p2.add(new JButton("button4"));
		p2.add(new JButton("button5"));
		p2.add(new JButton("button6"));
		
		//下拉面板
		JPanel combo = new JPanel();
		String []s = {"blue", "cyan"};
		JComboBox<String> box = new JComboBox<>(s);
		combo.add(box);
			
		//显示面板,使用CardLayout
		JPanel p3 = new JPanel(new CardLayout());
		p3.add("blue",p1  );
		p3.add("cyan",p2  );
		
		frame.add(combo, BorderLayout.NORTH);
		frame.add(p3, BorderLayout.SOUTH);
		frame.setVisible(true);
		 frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				CardLayout c = (CardLayout)p3.getLayout();
				c.show(p3, (String)arg0.getItem());
			}		
		});	
	}
	public static void main(String []args) {
		new Panel().testCardLayout();
	}
}
