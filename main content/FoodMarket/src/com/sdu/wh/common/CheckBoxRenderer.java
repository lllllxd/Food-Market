package com.sdu.wh.common;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
public class CheckBoxRenderer implements TableCellRenderer {
	  
	private JPanel panel;
	private JCheckBox checkbox;
	public CheckBoxRenderer(JCheckBox box) {
		panel=new JPanel(new BorderLayout(0,0));
		checkbox=box;
		panel.add(checkbox,BorderLayout.CENTER);
		panel.setOpaque(false);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		checkbox = (JCheckBox)value; 
		panel.removeAll();
		panel.add(checkbox,BorderLayout.CENTER);
		panel.setOpaque(false);
		if (value == null)
			return null;
		return (Component) panel;
	}
}
