package com.sdu.wh.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.dal.PlanDAL;

/**
 * �����ƻ����߼���z
 * @author Gale
 *
 */
public class PlanDepartmentBLL {

	public Object[][] mData = new Object[][] {};
	public Object[][] mTemp = new Object[][] {};
	
	/**
	 * ���ṩ�����ı����ṩһ��
	 * @param PNO
	 * @param PAmount
	 * @param PDate
	 * @param data
	 * @return
	 */
	public Object[][] addProductionSchedule(String PNO,String PAmount,String PDate,Object[][] data) {
		Object[][] temp = new Object[data.length + 1][3];
		for (int i = 0; i < data.length; ++i) {
			temp[i] = data[i];
		}
		temp[temp.length - 1] = new Object[]{PNO, PAmount, PDate};
		return temp;
	}
	
	/**
	 * ��mTemp��������ݿ�
	 * @return
	 */
	public boolean createPlan() {
		
		/**
		 * ����mTemp�����������¼
		 */
		boolean flag = true;
		for(int i = 0; i < mTemp.length; i++){
			try {
				PlanDAL.buildProductionSchedule(Date.valueOf(mTemp[i][2].toString()), Integer.parseInt(mTemp[i][1].toString()), Integer.parseInt(mTemp[i][0].toString()), /*Integer.parseInt(MainForm.getPersonId())*/ 0);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "����ʧ��");
				flag = false;
				e.printStackTrace();
			}
		}
		if (flag) {
			JOptionPane.showMessageDialog(null, "����ɹ�");
		}
		
		return true;
	}
	
	/**
	 * ˢ�´�������
	 * @param table
	 * @return
	 * @throws Exception
	 */
	public JTable refreshOrderTable(JTable table){
		
		int flag = 0;
		mData = null;
		try {
			mData = PlanDAL.CheckOrderFormTable();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e1.printStackTrace();
		}
		if (mData == null || mData.length == 0) {
			mData = new Object[][]{{"null","��ȫ������"}};
			flag = 1;
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"�������","��Ʒ���","��Ʒ����","�������","ȷ�ϴ���"}) {
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
                        "ȷ���Ѵ���?" , "ȷ�ϲ���",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0 && mData[editor.mRow][0] != "null") {
                	try {
						PlanDAL.changeOrderFormState(Integer.parseInt(mData[editor.mRow][0].toString()));
						JOptionPane.showMessageDialog(null, "����ɹ�");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "����ʧ��");
						e1.printStackTrace();
					}
                	refreshOrderTable(table);
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
	 * ��ȡ�����
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreshStockTable(JTable table){

		mData = null;
		try {
			mData = PlanDAL.checkInventory();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}
		if (mData == null || mData.length == 0) {
			mData = new Object[][] {{}};
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"���ʱ��","��Ʒ���","��ǰ���","��������"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        });
        
        table.setRowSelectionAllowed(false);
		
		return table;
	}

	/**
	 * ��������ˢ�±��
	 * @param table
	 * @param data
	 * @return
	 */
	public JTable refreshTable(JTable table, Object[][] data) {
		table.setModel(new DefaultTableModel(data, new String[] { "��Ʒ���", "��������", "��������" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		return table;
	}
}
