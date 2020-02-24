package com.sdu.wh.dal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sdu.wh.dao.Customer;
import com.sdu.wh.dao.OrderForm;
import com.sdu.wh.dao.Product;

public class MarketingDAL {

	/**
	 * 生成一条用户订单，并添加到数据库
	 * @throws Exception 
	 */
	public static boolean saveOrderForm(int No,int customerNo,int productNo,int num,Date date,int responsiblePerson) throws Exception {
		String sql=SqlSentense.getBuildOrderForm(No,customerNo, productNo, num, date, responsiblePerson);
		SqlExecute se= new SqlExecute();
		int t=se.executeSql(sql);
		if(t!=0)
			return true;
		else
			return false;
	}
	
	/**
	 * 查询客户信息,返回一个字符串数组记录查询到的一条记录
	 * @throws Exception 
	 */
	public static String[] queryCustomerMessage(int customerNo) throws Exception {
		String sql=SqlSentense.getSelect("p_No",customerNo , "t_Customer");
		SqlExecute se=new SqlExecute();
		String[] customer=se.getClass(sql);
		return customer;
	}
	
	/**
	 * 生成选择订购表
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getOrderTable() throws Exception{
		/**
		 * 数据库查询
		 * "  产品编号  ","  产品名称  ","  产品价格  ","","  选购数量  "
		 */
		String sql=SqlSentense.getselectvw("vw_OrderTable");
		Object[][] data = SqlExecute.getObject(sql);
		return data;
	}

	/**
	 * 创建客户信息，并添加到数据库
	 * @throws Exception 
	 */
	public static String createCustomer(String name,int level,String detail) throws Exception {
		SqlExecute se=new SqlExecute();
		String No=se.getProcReturn(name, level, detail);
		return No;	
	}
	
	/**
	 * 修改销售策略
	 * @throws Exception 
	 */
	public static boolean upSaleStrategy(int level,float discount,float prepayment,int ResponsiblePerson) throws Exception {
		String sql=SqlSentense.getUpdateSalesStrategy(level, discount, prepayment, ResponsiblePerson);
		SqlExecute se= new SqlExecute();
		int t=se.executeSql(sql);
		if(t!=0)
			return true;
		else
			return false;
	}
	
	/**
	 * 查询可以退货表
	 * @throws Exception 
	 */
	public static Object[][] getReturnTable() throws Exception{
		String sql=SqlSentense.getselectvw("vw_ReturnGoodsTable");
		SqlExecute se= new SqlExecute();
		JTable t=new JTable();
		String[] s=new String[2];
		s[0]="未付款";s[1]="已付款";
		
		t = se.getTable(sql);
		Object[][] data = new Object[t.getRowCount()][t.getColumnCount()];
		for(int i=0;i<t.getRowCount();i++) {
			for(int j=0;j<t.getColumnCount();j++) {
			DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
			String cellData=(String) tableModel.getValueAt(i,j);
			//cellData是第一行第一列的数据值
			//getValueAT(a,b)返回的是Object类型的对象
			if(j==2) {
				data[i][j]=s[Integer.parseInt(cellData)];
				}
			else {
				data[i][j]=cellData;
				}
			}
		}
		return data;
	}

	/**
	 * 退货操作
	 * @throws Exception 
	 */
	public static boolean returnGoods(Object data) throws Exception {
		String sql=SqlSentense.getReturnGoods(data);
		SqlExecute se= new SqlExecute();
		int t=se.executeSql(sql);
		if(t!=0)
			return true;
		else
			return false;
	}

	/**
	 * 查询所有订单
	 * @throws Exception 
	 */
	public static Object[][] getOrderForm() throws Exception{
		String sql=SqlSentense.getSelect(0, 0, "t_OrderForm");
		Object[][] data = SqlExecute.getObject(sql);
		return data;
	}
	
	/**
	 * 返回最大订单编号
	 * @throws Exception 
	 */
	public static String getMaxNo() throws Exception {
		String sql=" select max(p_No )from t_OrderForm";	
		SqlExecute se=new SqlExecute();
		String[] mreturn =se.getClass(sql);
		return mreturn[0];
	}

}
