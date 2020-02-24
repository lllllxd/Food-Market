package com.sdu.wh.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.dal.EndProductDAL;
import com.sdu.wh.ui.MainForm;

public class WarehouseDepartmentBLL {
	
	private Object[][] mData =null;
	
	/**
	 * ��ȡ������ɼ�¼��
	 * @param table
	 * @return
	 */
	public JTable refreashProduceTable(JTable table) {
	
		try {
			mData = EndProductDAL.getProductionSchedule();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "��ȡʧ��");
			e1.printStackTrace();
		}
		
		if (mData.length == 0) {
			Object[][] data = {{"null"}};
			mData = data;
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"�ƻ����","��Ʒ���","��������","��������","ȷ�����"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (mData.length == 0){
                	return false;
                }
            	if (column == mData[0].length - 1) {
                    return true;
                } else {
                    return false;
                }
            }
        });
		MyButtonEditor editor = new MyButtonEditor();
		editor.button.addActionListener(new ActionListener() {/*�����ť����*/
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null,
                        "ȷ�������?" + editor.mValue, "�˿�ȷ��",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                	try {
						EndProductDAL.inConfirmation(Integer.parseInt(mData[editor.mRow][1].toString()), Integer.parseInt(mData[editor.mRow][2].toString()), /*Integer.parseInt(MainForm.getPersonId())*/ 0);
						EndProductDAL.changeProductionSchedule(Integer.parseInt(mData[editor.mRow][0].toString()));
						JOptionPane.showMessageDialog(null, "���ɹ�");
					} catch (NumberFormatException e1) {
						
						JOptionPane.showMessageDialog(null, "���ʧ��");
						e1.printStackTrace();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "���ʧ��");
						e1.printStackTrace();
					}
                	refreashProduceTable(table);
            	}
            }
        });

        table.getColumnModel().getColumn(mData[0].length - 1).setCellEditor(
                editor);

        table.getColumnModel().getColumn(mData[0].length - 1).setCellRenderer(
                new MyButtonRenderer());
        
        table.setRowSelectionAllowed(false);
        
		return table;
	}

	/**
	 * ��ȡ��ǰ����
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreashOrderTable(JTable table)  {
		
		try {
			mData = EndProductDAL.checkInventory();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "��ȡʧ��");
			e1.printStackTrace();
		}
		if(mData.length == 0){
			Object[][] data = {{"null"}};
			mData = data;
		}
		table.setModel(new DefaultTableModel(mData,new String[]{"��Ʒ���","��ǰ���","��������","��������","ȷ�ϳ���"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3||column == 4) {
                    return true;
                } else {
                    return false;
                }
            }
        });
		
		MyButtonEditor editor = new MyButtonEditor();
		editor.button.addActionListener(new ActionListener() {/*�����ť����*/
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null,
                        "ȷ�ϳ���?" , "����ȷ��",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                	mData[editor.mRow][3] = table.getModel().getValueAt(editor.mRow, editor.mColumn - 1);
                	if(Integer.parseInt(mData[editor.mRow][1].toString()) == Integer.parseInt(mData[editor.mRow][3].toString())){
                		try {
							EndProductDAL.deleteInventory(Integer.parseInt(mData[editor.mRow][0].toString()), Date.valueOf(mData[editor.mRow][2].toString()));
							EndProductDAL.createOutTable(Integer.parseInt(mData[editor.mRow][3].toString()), Integer.parseInt(mData[editor.mRow][0].toString()), /*Integer.parseInt(MainForm.getPersonId())*/ 0);
							JOptionPane.showMessageDialog(null, "����ɹ�");
							} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "����ʧ��");
							e1.printStackTrace();
						}
                	}else {
                		try {
							EndProductDAL.changeInventory(Integer.parseInt(mData[editor.mRow][0].toString()), Date.valueOf(mData[editor.mRow][2].toString()),Integer.parseInt(mData[editor.mRow][1].toString()) - Integer.parseInt(mData[editor.mRow][3].toString()));
							EndProductDAL.createOutTable(Integer.parseInt(mData[editor.mRow][3].toString()), Integer.parseInt(mData[editor.mRow][0].toString()), /*Integer.parseInt(MainForm.getPersonId())*/ 0);
							JOptionPane.showMessageDialog(null, "����ɹ�");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "����ʧ��");
							e1.printStackTrace();
						}
                	}
                	refreashOrderTable(table);
            	}
            }
        });

        table.getColumnModel().getColumn(mData[0].length - 1).setCellEditor(
                editor);

        table.getColumnModel().getColumn(mData[0].length - 1).setCellRenderer(
                new MyButtonRenderer());
        
        table.setRowSelectionAllowed(false);
        
        return table;
	}
	
	/**
	 * ��ѯ���������
	 * @param table
	 * @return
	 */
	public JTable refreshPendingTable(JTable table) {
		try {
			mData = EndProductDAL.getBillOfLading();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "��ȡʧ��");
			e.printStackTrace();
		}
		if(mData.length == 0){
			Object[][] data = {{"null"}};
			mData = data;
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"��������","�ͻ����","��Ʒ���","��Ʒ����","ȷ�ϴ���"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                } else {
                    return false;
                }
            }
        });
		MyButtonEditor editor = new MyButtonEditor();
		editor.button.addActionListener(new ActionListener() {/*�����ť����*/
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null,
                        "ȷ�������?" , "ȷ�����",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                	try {
						EndProductDAL.outAll(Integer.parseInt(mData[editor.mRow][0].toString()), Integer.parseInt(mData[editor.mRow][2].toString()));
						JOptionPane.showMessageDialog(null, "����ɹ�");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "���ʧ��");
						e1.printStackTrace();
					}
                	refreshPendingTable(table);
            	}
            }
        });

        table.getColumnModel().getColumn(mData[0].length - 1).setCellEditor(
                editor);

        table.getColumnModel().getColumn(mData[0].length - 1).setCellRenderer(
                new MyButtonRenderer());
        
        table.setRowSelectionAllowed(false);
		
		return table;
	}

	
}
