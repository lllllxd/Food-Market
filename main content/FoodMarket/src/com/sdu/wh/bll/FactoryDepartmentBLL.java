package com.sdu.wh.bll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sdu.wh.common.MyButtonEditor;
import com.sdu.wh.common.MyButtonRenderer;
import com.sdu.wh.dal.FactoryDAL;
import com.sdu.wh.dal.FinanceDAL;
import com.sdu.wh.dal.MarketingDAL;
import com.sdu.wh.dal.SqlExecute;
import com.sdu.wh.dal.SqlSentense;
import com.sdu.wh.ui.MainForm;

/**
 * 车间逻辑类
 * @author Gale
 *
 */
public class FactoryDepartmentBLL {

	private Object[][] mData = null;
	
	/**
	 * 获取待生产记录
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreashPlanTable(JTable table){	
		Object[][] edata = null;
		try {
			edata = FactoryDAL.getProductionState("p_State",0);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e1.printStackTrace();
		}	
		if (edata == null || edata.length == 0) {
			mData = new Object[][] {{"null"}};
		}else {
			int row=edata.length;
			int col=edata[0].length;
			mData = new Object[row][col - 2];
			for(int i=0;i<row;i++) {
				for(int j=0;j<col - 2;j++) {
					if(j==3) {
						mData[i][j]=edata[i][4];
					}
					else if(j==4) {
						mData[i][j]="开始生产";
					}
					else {
						mData[i][j]=edata[i][j];
					}
				}
			}	
		}
		
		table.setModel(new DefaultTableModel(mData,new String[]{"生产计划编号","产品编号","生产数量","生产日期","开始生产"}) {
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
                        "确认开始生产?" , "开始生产",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
            		String sql=SqlSentense.getUpdateProductRecord(
            				Integer.parseInt((String)mData[editor.mRow][0]), 
            				Integer.parseInt((String)MainForm.getPersonId()));
            		SqlExecute seExecute =new SqlExecute();
            		try {
						seExecute.executeSql(sql);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		refreashPlanTable(table);
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
	 * 获取生产中记录
	 * @param table
	 * @return
	 * @throws Exception 
	 */
	public JTable refreashProduceTable(JTable table){
		Object[][] edata = null;
		try {
			edata = FactoryDAL.getProductionState("p_State", 1);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "连接数据库失败！");
			e1.printStackTrace();
		}
		if (edata == null || edata.length == 0) {
			mData = new Object[][] {{"null"}};
		}else {
			int row=edata.length;
			int col=edata[0].length;
			mData = new Object[row][col-1];
			for(int i=0;i<row;i++) {
				for(int j=0;j<col - 1;j++) {
					if(j==2) {
						mData[i][j]=edata[i][4];
					}
					else if(j==3) {
						mData[i][j]=edata[i][5];
					}
					else if(j==4) {
						mData[i][j]=edata[i][3];
					}
					else if(j==5) {
						mData[i][j]="确认完成";
					}
					else {
						mData[i][j]=edata[i][j];
					}
				}
			}	
		}
		
		table.setModel(new DefaultTableModel(mData, new String[]{"计划编号", "产品编号", "生产日期", "结束日期", "生产状态", "确认结束"}) {
            
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
                        "确认生产结束?" + editor.mValue, "结束确认",
                        JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                	String sql=SqlSentense.getUpdateProductRecord(
            				Integer.parseInt((String)mData[editor.mRow][0]), 
            				Integer.parseInt((String)MainForm.getPersonId()));
            		SqlExecute seExecute =new SqlExecute();
            		try {
						seExecute.executeSql(sql);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
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

}
