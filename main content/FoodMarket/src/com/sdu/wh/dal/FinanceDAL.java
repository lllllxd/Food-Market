package com.sdu.wh.dal;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;

import com.sdu.wh.dao.Customer;
import com.sdu.wh.dao.OrderForm;
import com.sdu.wh.dao.SalesStrategy;

public class FinanceDAL {
	
	private static Object[][] mData = null;
	
	
	/**
	 * 查找可退款订单，
	 * @return
	 * @throws Exception 
	 */
	public static Object[][] getRefundTableData() throws Exception{
		/**
		 * 根据订单生成表数据
		 */
		String sql = SqlSentense.getSelect("p_Pay", -1, "t_OrderForm");
		SqlExecute se = new SqlExecute();
		JTable jt = se.getTable(sql);
		
		ArrayList<Object[]> al = new ArrayList<Object[]>();
		
		int row = jt.getRowCount();
		int Column = jt.getColumnCount();
		
		if(row == 0){
			Object[][] oj = new Object[][]{};
			mData = oj;
			return mData;
		}
		/**
		 * 加入基础值
		 */
		Object a = jt.getValueAt(0, 0);
		Object b = jt.getValueAt(0, 1);
		Object c = jt.getValueAt(0, 6);
		float sum = (float)Double.parseDouble(jt.getValueAt(0, 8).toString());
		
		for(int i = 1; i < row; i++){
			if(a == jt.getValueAt(i, 0)){
				sum += Integer.parseInt(jt.getValueAt(i, 8).toString());
			}else{
				String sql2 = SqlSentense.getSelect("p_No", Integer.parseInt(b.toString()), "t_Customer");
				Customer customer = new Customer(sql2);
				String sql3 = SqlSentense.getSelect("p_Customer_Level", customer.getLevel(), "t_SalesStrategy");
				SalesStrategy salesStrategy = new SalesStrategy(sql3);
				DecimalFormat df = new DecimalFormat("##.##");
				String mDisMoney = df.format((sum * salesStrategy.getmDiscount() * salesStrategy.getmPrepaymentRatio()));
				Object[] oj = {a,b,c,"￥" + mDisMoney};
				al.add(oj);
				
				a = jt.getValueAt(i, 0);
				b = jt.getValueAt(i, 1);
				c = jt.getValueAt(i, 6);
				sum = Integer.parseInt(jt.getValueAt(i, 8).toString());
			}
		}
		/**
		 * 处理最后一项
		 */
		String sql2 = SqlSentense.getSelect("p_No", Integer.parseInt(b.toString()), "t_Customer");
		Customer customer = new Customer(sql2);
		String sql3 = SqlSentense.getSelect("p_Customer_Level", customer.getLevel(), "t_SalesStrategy");
		SalesStrategy salesStrategy = new SalesStrategy(sql3);
		DecimalFormat df = new DecimalFormat("##.##");
		String mDisMoney = df.format((sum * salesStrategy.getmDiscount() * salesStrategy.getmPrepaymentRatio()));
		Object[] oj = {a,b,c,"￥" + mDisMoney};
		al.add(oj);
		
		
		int size = al.size();
		Object[][] data = new Object[size][];
		for(int i = 0; i < size; i++){
			data[i] = al.get(i);
		}
		mData = data;
		
		return mData;
	}
	
	public static Object[][] getFinanceTableData() throws Exception{
		/**
		 * 根据订单生成表数据
		 * 订单编号，客户编号，收款情况，预付款，全款
		 */
		String sql = SqlSentense.getSelect(0, 0, "t_OrderForm");
		SqlExecute se = new SqlExecute();
		JTable jt = se.getTable(sql);
		
		ArrayList<Object[]> al = new ArrayList<Object[]>();
		
		int row = jt.getRowCount();
		int Column = jt.getColumnCount();
		if(row == 0){
			Object[][] oj = new Object[][]{};
			mData = oj;
			return mData;
		}
		/**
		 * 加入基础值
		 */
		Object a = jt.getValueAt(0, 0);
		Object b = jt.getValueAt(0, 1);
		Object c = jt.getValueAt(0, 7);
		float sum = (float)Double.parseDouble(jt.getValueAt(0, 8).toString());
		
		for(int i = 1; i < row; i++){
			if(a == jt.getValueAt(i, 0)){
				sum += Integer.parseInt(jt.getValueAt(i, 8).toString());
			}else{
				if(Integer.parseInt(c.toString()) == 0 || Integer.parseInt(c.toString()) == 1){
					String sql2 = SqlSentense.getSelect("p_No", Integer.parseInt(b.toString()), "t_Customer");
					Customer customer = new Customer(sql2);
					String sql3 = SqlSentense.getSelect("p_Customer_Level", customer.getLevel(), "t_SalesStrategy");
					SalesStrategy salesStrategy = new SalesStrategy(sql3);
					DecimalFormat df = new DecimalFormat("##.##");
					String mDisMoney = df.format((sum * salesStrategy.getmDiscount() * salesStrategy.getmPrepaymentRatio()));
					String mMoney = df.format(sum * salesStrategy.getmDiscount());
					Object[] oj = {a,b,c,"￥" + mDisMoney,"￥" + mMoney};
					al.add(oj);
				}
				
				a = jt.getValueAt(i, 0);
				b = jt.getValueAt(i, 1);
				c = jt.getValueAt(i, 7);
				sum = (float)Double.parseDouble(jt.getValueAt(0, 8).toString());
			}
		}
		/**
		 * 处理最后一项
		 */
		if (Integer.parseInt(c.toString()) == 0 || Integer.parseInt(c.toString()) == 1) {
			String sql2 = SqlSentense.getSelect("p_No", Integer.parseInt(b.toString()), "t_Customer");
			Customer customer = new Customer(sql2);
			String sql3 = SqlSentense.getSelect("p_Customer_Level", customer.getLevel(), "t_SalesStrategy");
			SalesStrategy salesStrategy = new SalesStrategy(sql3);
			DecimalFormat df = new DecimalFormat("##.##");
			String mDisMoney = df.format((sum * salesStrategy.getmDiscount() * salesStrategy.getmPrepaymentRatio()));
			String mMoney = df.format(sum * salesStrategy.getmDiscount());
			Object[] oj = { a, b, c, "￥" + mDisMoney, "￥" + mMoney };
			al.add(oj);
		}
		
		int size = al.size();
		Object[][] data = new Object[size][];
		for(int i = 0; i < size; i++){
			data[i] = al.get(i);
		}
		mData = data;
		
		return mData;
	}
	
	/**
	 * 生成提货单
	 * @throws Exception 
	 */
	public static boolean buildBillOfLading(int ResponsiblePerson,int OrderFormNo) throws Exception{
		String sql = SqlSentense.getBuildBillOflading(ResponsiblePerson, OrderFormNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 退货
	 * @throws Exception 
	 */
	public static boolean returnGoods(int OrderFormNo) throws Exception{
		String sql = SqlSentense.getReturnGoods(OrderFormNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 编写出纳表
	 * @throws Exception 
	 */
	public static boolean buildCashier(float Money,String Detail,int ResponsiblePerson ,int OrderFormNo) throws Exception{
		String sql = SqlSentense.getInCashier(Money, Detail,ResponsiblePerson, OrderFormNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 已交定金
	 * @throws Exception 
	 */
	public static boolean payLittle(int OrderFormNo) throws Exception{
		String sql = SqlSentense.getupdate("p_Pay", "1", "t_OrderForm", "p_No", OrderFormNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 已交全款
	 * @throws Exception 
	 */
	public static boolean payAll(int OrderFormNo) throws Exception{
		String sql = SqlSentense.getupdate("p_Pay", "3","t_OrderForm", "p_No", OrderFormNo);
		SqlExecute se = new SqlExecute();
		int num = se.executeSql(sql);
		if(num > 0){
			return true;
		}else{
			return false;
		}
	}
}
