/*
 * 表格
 */

package org.casey.basic.gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Table {
	public void execute() {
		HeroDAO hero = new HeroDAO();
		
		JFrame frame = new JFrame("表格");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		
		
			
		
		//实例化一个HeroTableModel作为参数去构造这个表,很好的表现的数据与显示的分离
		HeroTableModel tm = new HeroTableModel();
		JTable table = new JTable(tm);		//JTable JScrollPane必须提供参数构造,不能用方法add构造
		
		table.getColumnModel().getColumn(2).setPreferredWidth(1000);//设置第0列的宽度为10
		
		JLabel label = new JLabel();	//该标签显示当前选中的英雄名
		label.setBounds(10,10,800,25);
		frame.getContentPane().add(label);	
		//增加监选择听器,
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				Hero h = tm.L.get(row);
				label.setText("当前选中的英雄名是:"+h.getName());				
			}			
		});
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().setSelectionInterval(0, 0);	//Default选中第一行
		
		JScrollPane scroll = new JScrollPane(table);	//加入到JScrollPane中,以显示字段名
		scroll.setBounds(10, 40, 400, 200);
		frame.getContentPane().add(scroll, BorderLayout.CENTER);
		
		JPanel p1 = new JPanel();	//增加英雄面板
		p1.setLayout(new FlowLayout(0, 15, 20));
		JLabel label0 = new JLabel("name:");
		JTextField text0 = new JTextField();
		text0.setPreferredSize(new Dimension(100, 25));
		p1.add(label0);
		p1.add(text0);
		
		JLabel label1 = new JLabel("hp:");
		JTextField text1 = new JTextField();
		text1.setPreferredSize(new Dimension(100, 25));
		p1.add(label1);
		p1.add(text1);
		
		JButton button = new JButton("增加");
		p1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = text0.getText();
				if(name.equals("")) {
					JOptionPane.showMessageDialog(frame, "输入name为空", "消息", 2);
					return ;
				}
				String temp = text1.getText();
				if(temp.equals("")) {
					JOptionPane.showMessageDialog(frame, "输入hp为空", "消息", 2);
					return ;
				}
				double hp = 0;
				try {
					hp = Double.parseDouble(text1.getText());
					
				}catch(NumberFormatException e0) {
					JOptionPane.showMessageDialog(frame, "输入的hp非数字", "消息", 1);
					return ;
				}							
				hero.add(new Hero(0, name, hp));
				
				//将添加完成后的表传给TableModel中的L
				tm.L = new HeroDAO().list();
				
				//在此更新表格
				table.updateUI();
				
				//增加数据后选中新增加的行
				table.getSelectionModel().setSelectionInterval(0, tm.L.size()-1);
				
				//滚动到新增加的数据
				Rectangle rectangle = table.getCellRect(table.getSelectedRow(), table.getSelectedColumn(), true);
				table.scrollRectToVisible(rectangle);
			}		
		});
		p1.setBounds(10, 250, 400, 90);
		frame.getContentPane().add(p1);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	
	
	//只要确定start就可以确定每一页
	static int start = 0;		//分页开始的位置
	static int num = 10;		//每页显示10条数据
	static boolean showAll = true;	//初始显示全部
	static boolean cbListener = true;
	public void Table_exercise() {
		//只是一个简单的表格练习
		HeroDAO hero = new HeroDAO();
		JFrame frame = new JFrame("表格练习");
		frame.setBounds(250, 100, 700, 500);
		frame.setLayout(null);		
		
		//表格部分
		JPanel p0 = new JPanel();
		p0.setLayout(null);
		HeroTableModel htm = new HeroTableModel();
		JTable table = new JTable(htm);
		JScrollPane scroll = new JScrollPane(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setBounds(100, 35, 500, 184);
		p0.add(scroll);
		//显示选中的行信息
		JLabel label0 = new JLabel("当前选中的英雄信息为:");
		label0.setBounds(100, 5, 700, 30);
		p0.add(label0);
		
		//按钮部分
		JPanel p1 = new JPanel();
		JButton add_button = new JButton("增加");
		JButton delete_button = new JButton("删除");
		JButton update_button = new JButton("修改");
		JButton home_button = new JButton("首页");
		JButton prior_button = new JButton("上一页");			
		JButton next_button = new JButton("下一页");
		JButton end_button = new JButton("尾页");		
		JButton showAll_button = new JButton("取消显示全部");
		class temp_Page{
			public Integer[] getPage(){
				int i = (last()/num)+1;
				Integer []index = new Integer[i];
				for(int j=0; j<i; j++) {
					index[j] = j+1;
				}
				return index;
			}
			public int last() {
				//最后一页,返回的是start的位置
				//如果是35个数据,那么返回30,只要确定了start就确定了一页
				int last = 0;
				int count = hero.getCount();
				if(count%num == 0) {
					last = count - num;
				}else {
					last = count - count%num;
				}
				return last;
			}
		}
		JComboBox<Integer> box = new JComboBox<Integer>(new temp_Page().getPage());
		home_button.setEnabled(false);	//默认点击进去时是显示全部,关掉所有连接
		prior_button.setEnabled(false);
		next_button.setEnabled(false);
		end_button.setEnabled(false);
		box.setEnabled(false);
		
		class temp_update{
			public void updateTable() {
				htm.L = hero.list(start, num);
				table.getSelectionModel().setSelectionInterval(0, 0);
				table.updateUI();
			}
			public void updateButton() {
				int last = last();
				if(start == 0) {
					home_button.setEnabled(false);
					prior_button.setEnabled(false);
				}else {
					home_button.setEnabled(true);
					prior_button.setEnabled(true);
				}
				if(start == last) {
					end_button.setEnabled(false);
					next_button.setEnabled(false);
				}
				if(start < last){
					end_button.setEnabled(true);
					next_button.setEnabled(true);
				}
				int AllPage = (last/num)+1;
				cbListener = false;
				
				box.removeAllItems();
				for(int i=0; i<AllPage; i++) {
					box.addItem(i+1);
				}
				cbListener = true;
				System.out.println("退出");
				int a = (start/num) + 1;
				box.setSelectedItem(a);		//更新下拉框的值
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(frame, "未选定行", "消息", 1);
					return ;
				}
				Hero h = htm.L.get(row);
				label0.setText("当前选中的英雄信息为:   id:"+h.getId()+"   name:"+h.getName()+"   hp:"+h.getHp());
			}
			public int last() {
				//最后一页,返回的是start的位置
				int last = 0;
				int count = hero.getCount();
				if(count%num == 0) {
					last = count - num;
				}else {
					last = count - count%num;
				}
				return last;
			}
		}
		
		p1.setLayout(null);
			
		JPanel p2 = new JPanel();
		p2.setBounds(100, 20, 500, 38);
		p2.add(add_button);
		p2.add(delete_button);
		p2.add(update_button);
		p1.add(p2);
		JPanel p3 = new JPanel();
		p3.setBounds(100, 80, 500, 38);
		p3.add(home_button);
		p3.add(prior_button);
		p3.add(box);
		p3.add(next_button);
		p3.add(end_button);
		p3.add(showAll_button);
		p1.add(p3);
		
		//分离面板
		JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p0, p1);
		split.setBounds(0, 0, 700, 450);
		split.setDividerLocation(250);
		frame.add(split);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
					
		
		//增加按钮监听
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.setBounds(400, 220, 400, 300);
				dialog.setLayout(null);
				dialog.setTitle("增加英雄");
				JPanel p = new JPanel();
				p.setLayout(new FlowLayout());
				p.setBounds(50, 30, 300, 50);
				JLabel label = new JLabel("name:");
				JTextField text = new JTextField();
				text.setPreferredSize(new Dimension(150, 30));
				p.add(label);
				p.add(text);
				dialog.add(p);
				
				JPanel p0 = new JPanel();
				p0.setLayout(new FlowLayout());
				p0.setBounds(50, 80, 300, 50);
				JLabel label0 = new JLabel("hp     :");
				JTextField text0 = new JTextField();
				text0.setPreferredSize(new Dimension(150, 30));
				p0.add(label0);
				p0.add(text0);
				dialog.add(p0);
				
				JButton add_dialog_button = new JButton("增加");
				add_dialog_button.setBounds(180, 160, 85, 40);
				dialog.add(add_dialog_button);
				
				dialog.setVisible(true);
				add_dialog_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(text.getText().equals("")) {
							JOptionPane.showMessageDialog(dialog, "name不可为空", "消息", 1);
							return ;
						}
						if(text0.getText().equals("")) {
							JOptionPane.showMessageDialog(dialog, "hp不可为空", "消息", 1);
							return ;
						}
						double i = 0;
						try {
							i = Double.parseDouble(text0.getText());
						}catch(NumberFormatException e0) {
							JOptionPane.showMessageDialog(dialog, "hp输入的是非数字", "通ZHi", 1);
							return ;
						}
						hero.add(new Hero(0, text.getText(), i));
						JOptionPane.showMessageDialog(dialog, "添加成功", "通ZHi", 1);
						//此处选定行,此处更新UI												
						htm.L = new HeroDAO().list();
						table.updateUI();
						table.getSelectionModel().setSelectionInterval(0, htm.L.size()-1);
						dialog.dispose();	//关闭对话框
					}
				});	
			}
		});
		//删除按钮监听
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(frame, "未选定行", "消息", 1);
					return ;
				}
				Hero h = htm.L.get(row);
				int mark = JOptionPane.showConfirmDialog(frame, "你想删除name:"+h.getName()+"的英雄吗?");
				if(JOptionPane.OK_OPTION == mark) {
					hero.delete(h);
					htm.L = hero.list();
					table.updateUI();
					table.getSelectionModel().setSelectionInterval(0, 0);
					JOptionPane.showMessageDialog(frame, "删除成功", "消息", 1);
				}
			}
		});
		//修改按钮监听
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(frame, "未选定行", "消息", 1);
					return ;
				}
				Hero h = htm.L.get(row);
				JDialog dialog = new JDialog();
				dialog.setBounds(400, 220, 400, 300);
				dialog.setLayout(null);
				dialog.setTitle("修改英雄"+h.getName()+"的信息");
				JLabel label = new JLabel("输入该英雄更新的信息:");
				label.setBounds(70, 0, 150, 30);
				dialog.add(label);
				
				JPanel p = new JPanel();
				p.setLayout(new FlowLayout());
				p.setBounds(20, 30, 300, 50);
				JLabel label0 = new JLabel("name:");
				JTextField text0 = new JTextField();
				text0.setPreferredSize(new Dimension(150, 30));
				p.add(label0);
				p.add(text0);
				dialog.add(p);
				
				JPanel p0 = new JPanel();
				p0.setLayout(new FlowLayout());
				p0.setBounds(20, 80, 300, 50);
				JLabel label1 = new JLabel("hp     :");
				JTextField text1 = new JTextField();
				text1.setPreferredSize(new Dimension(150, 30));
				p0.add(label1);
				p0.add(text1);
				dialog.add(p0);
				
				JButton update_dialog_button = new JButton("修改");
				update_dialog_button.setBounds(140, 160, 85, 40);
				dialog.add(update_dialog_button);
				
				dialog.setVisible(true);
				update_dialog_button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						if(text0.getText().equals("")) {
							JOptionPane.showMessageDialog(dialog, "name不可为空", "消息", 1);
							return ;
						}
						if(text1.getText().equals("")) {
							JOptionPane.showMessageDialog(dialog, "hp不可为空", "消息", 1);
							return ;
						}
						double d = 0;
						try {
							d = Double.parseDouble(text1.getText());
						}catch(NumberFormatException e1) {
							JOptionPane.showMessageDialog(dialog, "你输入的是非数字类型", "消息", 1);
							return ;
						}		
						hero.update(h, new Hero(0,text0.getText(),d));
						JOptionPane.showMessageDialog(dialog, "修改成功", "通ZHi", 1);
						//此处选定行,此处更新UI		
						htm.L = new HeroDAO().list();
						table.updateUI();
						table.getSelectionModel().setSelectionInterval(0, row);
						dialog.dispose();
					}
				});
			}
		});
		
		//首页,首页失效
		home_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = 0;
				new temp_update().updateTable();
				new temp_update().updateButton();
			}
		});
		//尾页,尾页失效
		end_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start = new temp_update().last();
				new temp_update().updateTable();
				new temp_update().updateButton();
			}
		});		
		//上一页,在首页时失效
		prior_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start -= num;
				new temp_update().updateTable();
				new temp_update().updateButton();
			}
		});
		//下一页,在尾页是失效
		next_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start += num;
				new temp_update().updateTable();
				new temp_update().updateButton();
			}
		});
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbListener == false) {
					
					return ;
				}
				System.out.println("hhhhhh");
				int currentPage = (int)box.getSelectedItem();
				start = (currentPage - 1)*num;
				new temp_update().updateTable();
				new temp_update().updateButton();
			}
		});
		//显示全部,所有连接按钮失效; 点击后边为取消显示全部,所有连接按钮恢复
		showAll_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showAll == true) {	
					home_button.setEnabled(true);
					prior_button.setEnabled(true);
					next_button.setEnabled(true);
					end_button.setEnabled(true);
					box.setEnabled(true);
					showAll_button.setText("显示全部");
					showAll = false;
					start = 0;
					new temp_update().updateTable();
					new temp_update().updateButton();
				}else {	
					//显示全部,禁用所有连接
					htm.L = hero.list();
					home_button.setEnabled(false);
					prior_button.setEnabled(false);
					next_button.setEnabled(false);
					end_button.setEnabled(false);
					box.setEnabled(false);
					showAll_button.setText("取消显示全部");
					showAll = true;
				}
				
				table.getSelectionModel().setSelectionInterval(0, 0);
				table.updateUI();
			}
		});
		//显示选中行
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(frame, "未选定行", "消息", 1);
					return ;
				}
				Hero h = htm.L.get(row);
				label0.setText("当前选中的英雄信息为:   id:"+h.getId()+"   name:"+h.getName()+"   hp:"+h.getHp());
			}
		});
	}
	public static void main(String []args) {
		new Table().Table_exercise();
	}
}
