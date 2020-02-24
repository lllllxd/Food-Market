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
	 * ����һ���û�����������ӵ����ݿ�
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
	 * ��ѯ�ͻ���Ϣ,����һ���ַ��������¼��ѯ����һ����¼
	 * @throws Exception 
	 */
	public static String[] queryCustomerMessage(int customerNo) throws Exception {
		String sql=SqlSentense.getSelect("p_No",customerNo , "t_Customer");
		SqlExecute se=new SqlExecute();
		String[] customer=se.getClass(sql);
		return customer;
	}
	
	/**
	 * ����ѡ�񶩹���
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getOrderTable() throws Exception{
		/**
		 * ���ݿ��ѯ
		 * "  ��Ʒ���  ","  ��Ʒ����  ","  ��Ʒ�۸�  ","","  ѡ������  "
		 */
		String sql=SqlSentense.getselectvw("vw_OrderTable");
		Object[][] data = SqlExecute.getObject(sql);
		return data;
	}

	/**
	 * �����ͻ���Ϣ������ӵ����ݿ�
	 * @throws Exception 
	 */
	public static String createCustomer(String name,int level,String detail) throws Exception {
		SqlExecute se=new SqlExecute();
		String No=se.getProcReturn(name, level, detail);
		return No;	
	}
	
	/**
	 * �޸����۲���
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
	 * ��ѯ�����˻���
	 * @throws Exception 
	 */
	public static Object[][] getReturnTable() throws Exception{
		String sql=SqlSentense.getselectvw("vw_ReturnGoodsTable");
		SqlExecute se= new SqlExecute();
		JTable t=new JTable();
		String[] s=new String[2];
		s[0]="δ����";s[1]="�Ѹ���";
		
		t = se.getTable(sql);
		Object[][] data = new Object[t.getRowCount()][t.getColumnCount()];
		for(int i=0;i<t.getRowCount();i++) {
			for(int j=0;j<t.getColumnCount();j++) {
			DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
			String cellData=(String) tableModel.getValueAt(i,j);
			//cellData�ǵ�һ�е�һ�е�����ֵ
			//getValueAT(a,b)���ص���Object���͵Ķ���
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
	 * �˻�����
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
	 * ��ѯ���ж���
	 * @throws Exception 
	 */
	public static Object[][] getOrderForm() throws Exception{
		String sql=SqlSentense.getSelect(0, 0, "t_OrderForm");
		Object[][] data = SqlExecute.getObject(sql);
		return data;
	}
	
	/**
	 * ������󶩵����
	 * @throws Exception 
	 */
	public static String getMaxNo() throws Exception {
		String sql=" select max(p_No )from t_OrderForm";	
		SqlExecute se=new SqlExecute();
		String[] mreturn =se.getClass(sql);
		return mreturn[0];
	}

}
