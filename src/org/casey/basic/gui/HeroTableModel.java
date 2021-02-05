package org.casey.basic.gui;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.util.List;
import java.util.LinkedList;
public class HeroTableModel extends AbstractTableModel{

	String []ColumnNames = {"id","name","hp"}; 
	List<Hero> L = new HeroDAO().list();
	@Override
	public int getColumnCount() {
		//×Ö¶ÎÊý
		return ColumnNames.length;
	}
	
	public String getColumnName(int columnindex) {
		//×Ö¶ÎÃû
		return ColumnNames[columnindex];
	}
	@Override
	public int getRowCount() {
		//ÐÐÊý
		return L.size();
	}

	@Override
	public Object getValueAt(int rowindex, int columnindex) {
		// TODO Auto-generated method stub
		Hero h = L.get(rowindex);
		if(columnindex == 0)
			return h.getId();
		if(columnindex ==1)
			return h.getName();
		if(columnindex == 2)
			return h.getHp();
		return null;
	}

}
