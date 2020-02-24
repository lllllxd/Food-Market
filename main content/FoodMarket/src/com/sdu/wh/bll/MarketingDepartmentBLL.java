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
 * 销售部逻辑类
 * @author Gale
 *
 */
public class MarketingDepartmentBLL {

	private OrderForm mOrderForm = new OrderForm(); 	/*存储订单相关信息*/
	private Object[][] mData = null;
	
	public OrderForm getOrderForm() {
		return mOrderForm;
	}

	/**
	 * 根据当前表格所选内容将数据保存在订单内
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
	 * 获取刷新列表数据
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean refreashDate(JTable table){
		
		Object[][] edata = null;
		try {
			edata = MarketingDAL.getOrderTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "连接数据库失败");
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
		dm.setDataVector(mData, new String[]{"勾选栏","  产品编号  ","  产品名称  ","  产品单价 (元/kg)  ","  库存数量(kg)  ","  选购数量(kg)  "});
		table.setModel(dm);
		table.getColumn("勾选栏").setCellEditor(new CheckBoxEditor(new JCheckBox()));
		table.getColumn("勾选栏").setCellRenderer(new CheckBoxRenderer(new JCheckBox()));
		
		return true;
	}

	/**
	 * 创建新客户
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String createCustomer(String name, String phone, int level){
		try {
			return MarketingDAL.createCustomer(name, level, phone);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据当前数据创建订单
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean createOrderForm() {
		
		/**
		 * 逻辑实现，先获取最大订单号，再循环加入记录
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
	 * 创建一行订单记录
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean createOrderForm(int no,int customerNo,int productNo,int num,Date date,int responsiblePerson){	
		try {
			return MarketingDAL.saveOrderForm(no, customerNo, productNo, num, date, responsiblePerson);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 刷新可退货订单
	 * @throws Exception 
	 */
	public JTable refreshRefundTable(JTable table) {
		Object[][] edata = null;
		try {
			edata = MarketingDAL.getReturnTable();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
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
						mData[i][j]="取消";
					}
					else {
						mData[i][j]=edata[i][j];
					}
				}
			}	
		}
		table.setModel(new DefaultTableModel(mData, new String[]{"订单编号", "客户编号", "收款情况", "订单创建日期", "取消订单"}) {
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
		editor.button.addActionListener(new ActionListener() {/*点击按钮监听*/
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null,
                        "确认取消?", "取消确认",
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
	 * 修改营销策略
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
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e.printStackTrace();
		}
		return false;
	}
	
}
