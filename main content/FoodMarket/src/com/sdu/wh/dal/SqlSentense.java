package com.sdu.wh.dal;

import java.sql.Date;

public class SqlSentense {
	
	/**
	 * ��������ѯ,�漰�������sql���
	 * @return String
	 */
	public static String getSelect(Object ObS1,Object ObS2,String table) {
		return "select * from "+table+" where "+ObS1+" = "+ObS2;
	}
	 
	
	/**
	 * ������ɾ�����漰�������sql���
	 * @return String
	 */
	public static String getDelete(Object ObS1,Object ObS2,String table) {
		return "delete "+table+" where "+ObS1+" = "+ObS2;
	}
	
	/**
	 * ��ѯ��ͼ
	 */
	public static String getselectvw(String table) {
		return "select * from "+table;
	}
	
	/**
	 * ���±�񵥸�����
	 */
	public static String getupdate(Object ObS1,Object ObS2,String table,Object ObS3,Object ObS4) {
		return "update "+table+" set "+ObS1+" = "+ObS2+" where "+ObS3+" = "+ ObS4;
	}
	
	
	/**
	 * �ƶ�������洢����
	 * @return String
	 */
	public static String getBuildBillOflading(int ResponsiblePerson,int OrderFormNo) {
		return "exec pr_BuildBillOflading "+ResponsiblePerson+","+OrderFormNo;
	}
	
	
	/**
	 * �ƶ��������洢����
	 * @return String
	 */
	public static String getBuildOrderForm(int No,int CustomerNo,int ProductNo,int num,Date date,int ResponsiblePerson) {
		return "exec pr_BuildOrderForm "+No+","+CustomerNo+","+ProductNo+","+num+",'"+date+"',"+ResponsiblePerson;
	}
	

	/**
	 * �ƶ������ƻ��洢����
	 * @return String
	 */
	public static String getBuildProductionSchedule(int num,Date date,int ResponsiblePerson,int ProductNo) {
		return "exec pr_BuildProductionSchedule "+num+",'"+date+"',"+ResponsiblePerson+","+ProductNo;
	}
	
	/**
	 * �����ͻ��洢����
	 * @return String
	 */
	public static String getCreateCustomer(String name,int level,String detail) {
		return "exec pr_CreateCustomer '"+name+"',"+level+",'"+detail;
	}
	
	
	/**
	 * ��������Ա�洢����
	 * @return String
	 */
	public static String getCreateUser(String user,String key,String detail,String post,String department) {
		return "exec pr_CreateUser '"+user+"','"+key+"','"+detail+"','"+post+"','"+department+"'";
	}
	
	
	/**
	 * ���ɱ�洢����
	 * @return String
	 */
	public static String getInCashier(float money,String detail,int ResponsiblePerson,int OrderFormNo) {
		return "exec pr_InCashier "+money+",'"+detail+"',"+ResponsiblePerson+","+OrderFormNo;
	}
	
	
	/**
	 * ����洢����
	 * @return String
	 */
	public static String getInTableSave(int ProductNo,int num,int ResponsiblePerson) {
		return "exec pr_InTableSave "+ProductNo+","+num+","+ResponsiblePerson;
	}
	
	
	/**
	 * �˻��洢����
	 * @return String
	 */
	public static String getReturnGoods(Object data) {
		return "exec pr_ReturnGoods "+data;
	}
	
	
	/**
	 * �޸����۲��Դ洢����
	 * @return String
	 */
	public static String getUpdateSalesStrategy(int level,float discount,float prepayment,int ResponsiblePerson) {
		return "exec pr_UpdateSalesStrategy "+level+","+discount+","+prepayment+","+ResponsiblePerson;
	}

	/**
	 * �޸�������¼
	 */
	public static String getUpdateProductRecord(int No,int ResponsiblePerson) {
		return "exec pr_UpProductRecord "+No+","+ResponsiblePerson;
	}
}
