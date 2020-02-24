package com.sdu.wh.bll;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.ibm.icu.impl.Row;
import com.sdu.wh.common.CheckBoxEditor;
import com.sdu.wh.common.CheckBoxRenderer;
import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.dal.MarketingDAL;
import com.sdu.wh.dal.SqlExecute;
import com.sdu.wh.dal.SqlSentense;
import com.sdu.wh.dao.Customer;
import com.sdu.wh.dao.OrderForm;
import com.sdu.wh.dao.Product;

/**
 * ���۲��߼���
 * @author Gale
 *
 */
public class MarketingDepartmentBLL {

	private OrderForm mOrderForm = new OrderForm(); 	/*�洢���������Ϣ*/
	private Object[][] mData = null;
	
	public OrderForm getOrderForm() {
		return mOrderForm;
	}

	/**
	 * ���ݵ�ǰ�����ѡ���ݽ����ݱ����ڶ�����
	 */
	public void setOrderFormData(JTable table) {
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		for(int i = 0; i < mData.length; i++) {
			Object[] row = mData[i];
			if(((JCheckBox)row[0]).isSelected() == true) {
				row[5] = table.getModel().getValueAt(i, 5);
				arrayList.add(row);
			}
		}
		
		Object[][] data = new Object[arrayList.size()][];
		for(int i = 0; i < arrayList.size(); ++i) {
			Object[] row = arrayList.get(i);
			data[i] = row;
			data[i] = new Object[row.length - 1];
			data[i][0] = row[1];
			data[i][1] = row[2];
			data[i][2] = row[3];
			data[i][3] = row[5];
			data[i][4] = Float.parseFloat((String)row[3]) * Integer.parseInt((String)row[5]);
		}
		mOrderForm.setData(data);	
	}
	
	/**
	 * ��ȡˢ���б�����
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean refreashDate(JTable table){
		
		Object[][] edata = null;
		try {
			edata = MarketingDAL.getOrderTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ��");
			e.printStackTrace();
		}
		if (edata == null || edata.length == 0) {
			mData = new Object[][] {};
		}
		else { 
			int row=edata.length;
			int col=edata[0].length;
			mData = new Object[row][col+2];
			for(int i=0;i<row;i++) {
				for(int j=0;j<col+2;j++) {
					if(j==0) {
						mData[i][j]=new JCheckBox();
					}
					else if(j==5) {
						mData[i][j]="1";
					}
					else {
						mData[i][j]=edata[i][j-1];
					}
				}
			}	
		}
	    DefaultTableModel dm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if (mData.length == 0) {
					if (column == 0) {
						return true;
					} else {
						return false;
					}
				} else {
					if (column == 0 || column == mData[0].length - 1) {
						return true;
					} else {
						return false;
					}
				}
			}
	    };
		dm.setDataVector(mData, new String[]{"��ѡ��","  ��Ʒ���  ","  ��Ʒ����  ","  ��Ʒ���� (Ԫ/kg)  ","  �������(kg)  ","  ѡ������(kg)  "});
		table.setModel(dm);
		table.getColumn("��ѡ��").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("��ѡ��").setCellRenderer(new CheckBoxRenderer(new JCheckBox()));
		
		return true;
	}

	/**
	 * �����¿ͻ�
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String createCustomer(String name, String phone, int level){
		try {
			return MarketingDAL.createCustomer(name, level, phone);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ���ݵ�ǰ���ݴ�������
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean createOrderForm() {
		
		/**
		 * �߼�ʵ�֣��Ȼ�ȡ��󶩵��ţ���ѭ�������¼
		 */
		Object[][] data = mOrderForm.getData();
		String max = "0";
		try {
			max = MarketingDAL.getMaxNo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int row=data.length;
		Date edate;
		for(int i=0;i<data.length;i++) {
			java.sql.Date sqlDate=new java.sql.Date(mOrderForm.getTakingDate().getTime().getTime());
			createOrderForm(
					Integer.parseInt(max)+1,
					Integer.parseInt(mOrderForm.getCustomerNo()),
					Integer.parseInt((String)data[i][0]),
					Integer.parseInt((String)data[i][3]),
					sqlDate,
					Integer.parseInt(mOrderForm.getPersonId()));
		}
		return true;
	}
	
	/**
	 * ����һ�ж�����¼
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean createOrderForm(int no,int customerNo,int productNo,int num,Date date,int responsiblePerson){	
		try {
			return MarketingDAL.saveOrderForm(no, customerNo, productNo, num, date, responsiblePerson);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ˢ�¿��˻�����
	 * @throws Exception 
	 */
	public JTable refreshRefundTable(JTable table) {
		Object[][] edata = null;
		try {
			edata = MarketingDAL.getReturnTable();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e1.printStackTrace();
		}
		if (edata == null || edata.length == 0) {
			mData = new Object[][] {};
		}
		else {
			int row=edata.length;
			int col=edata[0].length;
			mData = new Object[row][col+1];
			for(int i=0;i<row;i++) {
				for(int j=0;j<col;j++) {
					if(j==4) {
						mData[i][j]="ȡ��";
					}
					else {
						mData[i][j]=edata[i][j];
					}
				}
			}	
		}
		table.setModel(new DefaultTableModel(mData, new String[]{"�������", "�ͻ����", "�տ����", "������������", "ȡ������"}) {
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
                        "ȷ��ȡ��?", "ȡ��ȷ��",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                	try {
						MarketingDAL.returnGoods(mData[editor.mRow][0]) ;
					} catch (Exception e1) {
						// TODO Auto-generated catch block
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
	 * �޸�Ӫ������
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public boolean changeSalesStrategy(int level, String discount, String scale ,int responsiblePerson) {
		if(discount.length() == 0 || scale.length() == 0) {
			return false;
		}
		try {
			return MarketingDAL.upSaleStrategy(level, Float.parseFloat(discount), Float.parseFloat(scale), responsiblePerson);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݿ�ʧ�ܣ�");
			e.printStackTrace();
		}
		return false;
	}
	
}
