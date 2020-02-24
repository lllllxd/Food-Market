package com.sdu.wh.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.dal.FinanceDAL;
import com.sdu.wh.dao.OrderForm;

/**
 * �����߼���z
 * @author Gale
 *
 */
public class FinanceDepartmentBLL {
	
	private Object[][] mData = new Object[1][5];
	
	/**
	 * ˢ���˿��
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreshRefundTable(JTable table) {
		mData = null;
		try {
			mData = FinanceDAL.getRefundTableData();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e1.printStackTrace();
		}
		if (mData == null || mData.length == 0) {
			mData = new Object[][] {{"null"}};
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"�������","�ͻ����","��������","�˿���"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
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
                        "ȷ���˿�?" , "�˿�ȷ��",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                	try {
						FinanceDAL.returnGoods(Integer.parseInt(mData[editor.mRow][0].toString()));
						FinanceDAL.buildCashier(-(float)Double.parseDouble(mData[editor.mRow][3].toString().substring(1)), "�˿�",/*Integer.parseInt(MainForm.getPersonId())*/ 0, Integer.parseInt(mData[editor.mRow][0].toString()));
						JOptionPane.showMessageDialog(null, "�˿�ɹ�");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "�˿�ʧ��");
						e1.printStackTrace();
					}
                	refreshRefundTable(table);
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
	 * ˢ���տ��
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreshFinanceTable(JTable table){

		mData = null;
		try {
			mData = FinanceDAL.getFinanceTableData();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e1.printStackTrace();
		}
		if (mData == null || mData.length == 0) {
			mData = new Object[][] {{"null"}};
		}
		table.setModel(new DefaultTableModel(mData,new String[]{"�������","�ͻ����","�տ����","Ԥ����","ȫ��"}) {
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
                        "ȷ���տ�?" , "�տ�ȷ��",
                        JOptionPane.YES_NO_OPTION);
            	if (res == 0) {
            		if(editor.mColumn == 3){
            			try {
							FinanceDAL.payLittle(Integer.parseInt(mData[editor.mRow][0].toString()));
							FinanceDAL.buildCashier((float)Double.parseDouble(mData[editor.mRow][3].toString().substring(1)), "�ն���",/*Integer.parseInt(MainForm.getPersonId())*/ 0, Integer.parseInt(mData[editor.mRow][0].toString()));
							JOptionPane.showMessageDialog(null, "�ն���ɹ�");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "�ն���ʧ��");
							e1.printStackTrace();
						}
            		}else{
            			try {
							FinanceDAL.payAll(Integer.parseInt(mData[editor.mRow][0].toString()));
							FinanceDAL.buildCashier((float)Double.parseDouble(mData[editor.mRow][3].toString().substring(1)), "�ն���",/*Integer.parseInt(MainForm.getPersonId())*/ 0, Integer.parseInt(mData[editor.mRow][0].toString()));
							JOptionPane.showMessageDialog(null, "��ȫ��ɹ�");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "��ȫ��ʧ��");
							e1.printStackTrace();
						}
            		}
            		refreshFinanceTable(table);
            	}
            }
        });

        table.getColumnModel().getColumn(mData[0].length - 1).setCellEditor(
                editor);

        table.getColumnModel().getColumn(mData[0].length - 1).setCellRenderer(
                new MyButtonRenderer());
        
        table.getColumnModel().getColumn(mData[0].length - 2).setCellEditor(
                editor);

        table.getColumnModel().getColumn(mData[0].length - 2).setCellRenderer(
                new MyButtonRenderer());
        
        table.setRowSelectionAllowed(false);
		
		return table;
	}
}
