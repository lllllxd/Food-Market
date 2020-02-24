package com.sdu.wh.dal;

import java.sql.Date;

import javax.swing.JTable;

public class PlanDAL {
	
	private static Object[][] mData = null;
	
	/**
	 * 查询未确认订单
	 * "订单编号", "商品编号", "数量", "日期"
	 * @return
	 * @throws Exception 
	 */
	public static Object[][] CheckOrderFormTable() throws Exception{
		String sql = SqlSentense.getSelect("p_Pay", 1, "t_OrderForm");
		SqlExecute se = new SqlExecute();
		JTable jt = se.getTable(sql);
		
		int row = jt.getRowCount();
		int column = jt.getColumnCount();
		if(row == 0){
			Object[][] oj = new Object[][]{};
			mData = oj;
			return mData;
		}
		
		Object[][] data = new Object[row][];
		for(int i = 0; i < row; i++){
			Object[] oj = {jt.getValueAt(i, 0),jt.getValueAt(i, 1),jt.getValueAt(i, 2),jt.getValueAt(i, 4),"确认"};
			data[i] = oj;
		}
		mData = data;
		return mData;
	}
	
	/**
	 * 确认库存
	 * @throws Exception 
	 */
	public static Object[][] checkInventory() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_Inventory");
		SqlExecute se = new SqlExecute();
		Object[][] oj = se.getObject(sql);
		if(oj.length == 0){
			Object[][] data = new Object[1][];
			mData = data;
			return mData;
		}
		mData = oj;
		return mData;
		
	}
	
	/**
	 * 建立生产计划表
	 * @param ProductionBuildDate
	 * @param ProductionNum
	 * @param ProductNo
	 * @param ResponsiblePerson
	 * @return
	 * @throws Exception 
	 */
	public static boolean buildProductionSchedule(Date ProductionBuildDate,int ProductionNum,int ProductNo,int ResponsiblePerson) throws Exception{
		String sql = SqlSentense.getBuildProductionSchedule(ProductionNum, ProductionBuildDate, ResponsiblePerson, ProductNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 更新订单已被查看状态
	 * @throws Exception 
	 */
	public static boolean changeOrderFormState(int OrderFormNo) throws Exception{
		String sql = SqlSentense.getupdate("p_Pay", 2, "t_OrderForm", "p_No", OrderFormNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}

}
