package com.sdu.wh.dal;

import java.sql.Date;

import javax.swing.JTable;

public class EndProductDAL {

	private static Object[][] mData = null;
	
	/**
	 * ��ȡ������������¼
	 * @throws Exception 
	 */
	public static Object[][] getProductionSchedule() throws Exception{
		String sql = SqlSentense.getSelect("p_State", 2, "t_ProductRecord");
		SqlExecute se = new SqlExecute();
		JTable jt = se.getTable(sql);
		
		int row = jt.getRowCount();
		int column = jt.getColumnCount();
		
		Object[][] oj = new Object[row][];
		for(int i = 0; i < row; i++){
			Object[] data = {jt.getValueAt(i, 0),jt.getValueAt(i, 1),jt.getValueAt(i, 2),jt.getValueAt(i, 5),"ȷ�����"};
			oj[i] = data;
		}
		
		mData = oj;
		return mData;
	}
	
	/**
	 * ��ȡ�Ѹ�ȫ��Ķ���
	 * @return
	 * @throws Exception 
	 */
	public static Object[][] getBillOfLading() throws Exception{
		String sql = SqlSentense.getSelect("p_Pay", 3, "t_OrderForm");
		SqlExecute se = new SqlExecute();
		JTable jt = se.getTable(sql);
		
		int row = jt.getRowCount();
		Object[][] data = new Object[row][];
		for(int i = 0; i < row; i++){
			Object[] oj = {jt.getValueAt(i, 0),jt.getValueAt(i, 1),jt.getValueAt(i, 2),jt.getValueAt(i, 3),"ȷ��"};
			data[i] = oj;
		}
		
		mData = data;
		return mData;
	}
	
	/**
	 * ȷ�����
	 * @throws Exception 
	 */
	public static boolean inConfirmation(int ProductNo,int Num,int ResponsiblePerson) throws Exception{
		String sql = SqlSentense.getInTableSave(ProductNo, Num, ResponsiblePerson);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * ����������¼״̬
	 * @param ProductionScheduleNo
	 * @return
	 * @throws Exception
	 */
	public static boolean changeProductionSchedule(int ProductionScheduleNo) throws Exception{
		String sql = SqlSentense.getupdate("p_State", 3, "t_ProductRecord", "p_ProductionSchedule_No", ProductionScheduleNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * ��ѯ���
	 * @throws Exception 
	 */
	public static Object[][] checkInventory() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_Inventory");
		SqlExecute se = new SqlExecute();
		JTable jt = se.getTable(sql);
		
		int row = jt.getRowCount();
		int column = jt.getColumnCount();
		
		Object[][] oj = new Object[row][];
		for(int i = 0; i < row; i++){
			Object[] data = {jt.getValueAt(i, 1),jt.getValueAt(i, 2),jt.getValueAt(i, 0),0,"ȷ�ϳ���"};
			oj[i] = data;
		}
		mData = oj;
		return mData;
	}
	
	/**
	 * ɾ������¼
	 * @throws Exception 
	 */
	public static boolean deleteInventory(int ProductNo,Date InventoryDate) throws Exception{
		String sql = "delete t_Inventory where p_Product_No = " + ProductNo + "and p_Date = '" + InventoryDate.toString() + "'";
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * �޸Ŀ���¼
	 * @throws Exception 
	 */
	public static boolean changeInventory(int ProductNo,Date InventoryDate,int Num) throws Exception{
		String sql = "update t_Inventory set p_Num = " + Num + " where p_Product_No = " + ProductNo + "and p_Date = '" + InventoryDate.toString() + "'";
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ���ɳ����¼
	 * @throws Exception 
	 */
	public static boolean createOutTable(int Num,int ProductNo,int ResponsiblePerson) throws Exception{
		/**
		 * ��ȡ�������
		 */
		String sql = "select max(p_No )from t_OutTable";
		SqlExecute se = new SqlExecute();
		String[] mreturn = se.getClass(sql);
		int OutTableNo = Integer.parseInt(mreturn[0]) + 1;
		
		String sql2 = "insert into t_OutTable (p_No,p_Product_No,p_Num,p_Date,p_ResponsiblePerson) values (" + OutTableNo + "," + ProductNo + "," + Num + ",GETDATE()," + ResponsiblePerson + ")";
		int num = se.executeSql(sql2);
		if(num > 0){
			return true;
		}else{
			return false;
		}

	}
	
	/**
	 * ȫ���������
	 */
	public static boolean outAll(int OrderFormNo,int ProductNo) throws Exception{
		String sql = "update t_OrderForm set p_Pay = 4 where p_No = " + OrderFormNo + "and p_Product_No = " + ProductNo;
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
}
