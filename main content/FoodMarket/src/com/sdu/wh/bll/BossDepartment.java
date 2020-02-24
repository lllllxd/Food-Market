package com.sdu.wh.bll;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import com.sdu.wh.dal.FinanceDAL;
import com.sdu.wh.dal.Manager;

/**
 * 企业管理者逻辑类
 * 
 * @author Gale
 *
 */
public class BossDepartment {

	private Object[][] mData = null;

	private String[] str1 = new String[] { "订单编号", "客户编号", "交易金额", "生成日期", "负责人" };
	private String[] str2 = new String[] { "提货单号", "客户编号", "提货日期", "生成日期", "负责人" };
	private String[] str3 = new String[] { "计划编号", "产品数量", "生产日期", "生成日期", "负责人", "产品编号" };
	private String[] str4 = new String[] { "入库编号", "产品数量", "生成日期", "产品编号", "负责人" };
	private String[] str5 = new String[] { "出库编号", "产品数量", "生成日期", "产品编号", "负责人" };
	private String[] str6 = new String[] { "计划编号", "生产状态", "负责人" };
	private String[] str7 = new String[] { "入库时间", "产品编号", "产品数量", "销毁日期" };

	/**
	 * 根据所选类型刷新表格
	 * 
	 * @param table
	 * @param selectStr
	 * @return
	 */
	public JTable refreashTable(JTable table, String selectStr) {

		String[] tableHeader = str1;

		if (selectStr.equals("订单记录")) {
			tableHeader = str1;
			Object[][] edata = null;
			try {
				edata = FinanceDAL.getFinanceTableData();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e1.printStackTrace();
			}
			try {
				mData = Manager.getOrderForm();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
			int row = edata.length;
			for (int i = 0; i < row; i++) {
				mData[i][2] = edata[i][4];
			}

		}
		if (selectStr.equals("提货单记录")) {
			tableHeader = str2;
			try {
				mData = Manager.getBillOfLading();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("生产计划记录")) {
			tableHeader = str3;
			try {
				mData = Manager.getProductionSchedule();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("入库记录")) {
			tableHeader = str4;
			try {
				mData = Manager.getInTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("出库记录")) {
			tableHeader = str5;
			try {
				mData = Manager.getOutTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("生产记录")) {
			tableHeader = str6;
			try {
				mData = Manager.getProductRecord();
				String[] s = {"未生产","正在生产","生产完毕","已入库"};
				int row=mData.length;
				for(int i=0;i<row;i++) {
					mData[i][1]=s[Integer.parseInt((String)mData[i][1])];
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
		}
		if (selectStr.equals("库存")) {
			tableHeader = str7;
			try {
				mData = Manager.getInventory();
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "获取失败");
				e.printStackTrace();
			}
		}

		if (mData == null || mData.length == 0) {
			mData = new Object[][] {};
		}

		table.setModel(new DefaultTableModel(mData, tableHeader) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		return table;
	}
}
