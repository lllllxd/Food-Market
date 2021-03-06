package com.sdu.wh.common;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class MyButtonEditor extends AbstractCellEditor implements
        TableCellEditor {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6546334664166791132L;

    private JPanel panel;

    public JButton button;

    public Object mValue;
    public int mRow;
    public int mColumn;

    public MyButtonEditor() {
        initButton();
        initPanel();

        panel.setOpaque(false);
        panel.add(this.button, BorderLayout.CENTER);
    }

    private void initButton() {
        button = new JButton();
    }

    private void initPanel() {
        panel = new JPanel();

        panel.setLayout(new BorderLayout());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        mValue= value;
        mRow = row;
        mColumn = column;
        button.setText(String.valueOf(value));

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return mValue;
    }

}