package com.sdu.wh.common;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
 
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;
 
@SuppressWarnings("serial")
public class CheckBoxEditor extends DefaultCellEditor implements ItemListener {
	private JCheckBox button;
	private JPanel panel;
	public CheckBoxEditor(JCheckBox checkBox) {
		super(checkBox);
		
	}
 
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value == null)
			return null;
		button = (JCheckBox) value;
		button.addItemListener(this);
		return (Component) value;
	}
 
	public Object getCellEditorValue() {
		button.removeItemListener(this);
		return button;
	}
 
	public void itemStateChanged(ItemEvent e) {
		super.fireEditingStopped();
	}
}