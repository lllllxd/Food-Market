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
 * 生产计划科逻辑类z
 * @author Gale
 *
 */
public class PlanDepartmentBLL {

	public Object[][] mData = new Object[][] {};
	public Object[][] mTemp = new Object[][] {};
	
	/**
	 * 在提供参数的表中提供一行
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
	 * 将mTemp表插入数据库
	 * @return
	 */
	public boolean createPlan() {
		
		/**
		 * 根据mTemp表插入生产记录
		 */
		boolean flag = true;
		for(int i = 0; i < mTemp.length; i++){
			try {
				PlanDAL.buildProductionSchedule(Date.valueOf(mTemp[i][2].toString()), Integer.parseInt(mTemp[i][1].toString()), Integer.parseInt(mTemp[i][0].toString()), /*Integer.parseInt(MainForm.getPersonId())*/ 0);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "插入失败");
				flag = false;
				e.printStackTrace();
			}
		}
		if (flag) {
			JOptionPane.showMessageDialog(null, "插入成功");
		}
		
		return true;
	}
	
	/**
	 * 刷新待处理订单
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
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e1.printStackTrace();
		}
		if (mData == null || mData.length == 0) {
			mData = new Object[][]{{"null","已全部处理"}};
			flag = 1;
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"订单编号","产品编号","产品数量","提货日期","确认处理"}) {
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
		editor.button.addActionListener(new ActionListener() {/*点击按钮监听*/
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null,
                        "确认已处理?" , "确认操作",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0 && mData[editor.mRow][0] != "null") {
                	try {
						PlanDAL.changeOrderFormState(Integer.parseInt(mData[editor.mRow][0].toString()));
						JOptionPane.showMessageDialog(null, "处理成功");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "处理失败");
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
	 * 获取库存表格
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreshStockTable(JTable table){

		mData = null;
		try {
			mData = PlanDAL.checkInventory();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e.printStackTrace();
		}
		if (mData == null || mData.length == 0) {
			mData = new Object[][] {{}};
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"入库时间","产品编号","当前库存","销毁日期"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        });
        
        table.setRowSelectionAllowed(false);
		
		return table;
	}

	/**
	 * 根据数据刷新表格
	 * @param table
	 * @param data
	 * @return
	 */
	public JTable refreshTable(JTable table, Object[][] data) {
		table.setModel(new DefaultTableModel(data, new String[] { "产品编号", "生产数量", "生产日期" }) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		return table;
	}
}
