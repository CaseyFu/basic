
//日期
package org.casey.basic.gui;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import org.jdesktop.swingx.JXDatePicker;

import java.util.Date;
import java.util.Locale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//import com.eltima.components.ui.DatePicker;
public class Date1 {
	public void testDatePicker() {
		//不可在初始时间之后再设置时间
		/*JFrame frame = new JFrame("DatePicker");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		class Date2{
			//public DatePicker getDate() {
				//构造,(当前时间,格式,字体,尺寸大小)
				String timeFormat = "yyyy:MM:dd  HH:mm:ss";
				Font font = new Font("Times New Roman", Font.ITALIC, 28);
				//Dimension dimension = new Dimension(300, 300);	//大小可以在setBounds中设定
				//DatePicker dp = new DatePicker(new Date(), timeFormat, font, null);
				//dp.setBounds(50, 50, 300, 100);
				
				int []HightLight = {1,2,3};
				int []Disable = {28};
				//dp.setHightlightdays(HightLight, Color.RED);	//设置高亮红色
				//dp.setDisableddays(Disable); //设置为灰色
				//dp.setLocale(Locale.CHINA);
				//dp.setTimePanleVisible(true);
				//return dp;
			}
		}
		
		//DatePicker dp = new Date2().getDate();
		//frame.add(dp);
		
		JPanel panel = new JPanel();
		Rectangle r = new Rectangle(50, 400, 400, 400);
		panel.setBounds(r);
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		JLabel label = new JLabel("获取当前时间:");
		panel.add(label);
		JButton button = new JButton("获取");
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(frame, "当前的时间为:"+dp.getValue(), "通知", 1);
			}	
		});
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String []args) {
		new Date1().testJXDatePicker();
	}
	public void testJXDatePicker() {
		JFrame frame = new JFrame("JXDatePicker");
		frame.setBounds(50, 50, 1000, 600);
		frame.setLayout(null);
		JXDatePicker dp = new JXDatePicker();
		
		
		
		dp.setFormats(new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss"));
		dp.setDate(new Date());
		
		dp.setBounds(50, 50, 300, 100);
		frame.add(dp);
		
		JPanel panel = new JPanel();
		Rectangle r = new Rectangle(50, 400, 400, 400);
		panel.setBounds(r);
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		JLabel label = new JLabel("统计最后修改时间大于指定时间的文件:");
		panel.add(label);
		JButton button = new JButton("统计");
		panel.add(button);
		button.addActionListener(new ActionListener() {
			int count = 0;
			public void Traverse(File file) {
				if(file.isFile()) {
					if(file.lastModified() > dp.getDate().getTime()) {
						count++;
					}
				}else {
					for(File f:file.listFiles()) {
						Traverse(f);
					}
				}
			}
			public void actionPerformed(ActionEvent e) {
				Traverse(new File("G:/web"));
				JOptionPane.showMessageDialog(frame, "文件数为:"+count, "通知", 1);
				count = 0;
			}	
		});
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
	}
}
