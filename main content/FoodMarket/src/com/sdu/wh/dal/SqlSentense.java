package com.sdu.wh.dal;

import java.sql.Date;

public class SqlSentense {
	
	/**
	 * 单条件查询,涉及单个表的sql语句
	 * @return String
	 */
	public static String getSelect(Object ObS1,Object ObS2,String table) {
		return "select * from "+table+" where "+ObS1+" = "+ObS2;
	}
	 
	
	/**
	 * 单条件删除，涉及单个表的sql语句
	 * @return String
	 */
	public static String getDelete(Object ObS1,Object ObS2,String table) {
		return "delete "+table+" where "+ObS1+" = "+ObS2;
	}
	
	/**
	 * 查询视图
	 */
	public static String getselectvw(String table) {
		return "select * from "+table;
	}
	
	/**
	 * 更新表格单个属性
	 */
	public static String getupdate(Object ObS1,Object ObS2,String table,Object ObS3,Object ObS4) {
		return "update "+table+" set "+ObS1+" = "+ObS2+" where "+ObS3+" = "+ ObS4;
	}
	
	
	/**
	 * 制定提货单存储过程
	 * @return String
	 */
	public static String getBuildBillOflading(int ResponsiblePerson,int OrderFormNo) {
		return "exec pr_BuildBillOflading "+ResponsiblePerson+","+OrderFormNo;
	}
	
	
	/**
	 * 制定订货单存储过程
	 * @return String
	 */
	public static String getBuildOrderForm(int No,int CustomerNo,int ProductNo,int num,Date date,int ResponsiblePerson) {
		return "exec pr_BuildOrderForm "+No+","+CustomerNo+","+ProductNo+","+num+",'"+date+"',"+ResponsiblePerson;
	}
	

	/**
	 * 制定生产计划存储过程
	 * @return String
	 */
	public static String getBuildProductionSchedule(int num,Date date,int ResponsiblePerson,int ProductNo) {
		return "exec pr_BuildProductionSchedule "+num+",'"+date+"',"+ResponsiblePerson+","+ProductNo;
	}
	
	/**
	 * 创建客户存储过程
	 * @return String
	 */
	public static String getCreateCustomer(String name,int level,String detail) {
		return "exec pr_CreateCustomer '"+name+"',"+level+",'"+detail;
	}
	
	
	/**
	 * 创建管理员存储过程
	 * @return String
	 */
	public static String getCreateUser(String user,String key,String detail,String post,String department) {
		return "exec pr_CreateUser '"+user+"','"+key+"','"+detail+"','"+post+"','"+department+"'";
	}
	
	
	/**
	 * 出纳表存储过程
	 * @return String
	 */
	public static String getInCashier(float money,String detail,int ResponsiblePerson,int OrderFormNo) {
		return "exec pr_InCashier "+money+",'"+detail+"',"+ResponsiblePerson+","+OrderFormNo;
	}
	
	
	/**
	 * 入库表存储过程
	 * @return String
	 */
	public static String getInTableSave(int ProductNo,int num,int ResponsiblePerson) {
		return "exec pr_InTableSave "+ProductNo+","+num+","+ResponsiblePerson;
	}
	
	
	/**
	 * 退货存储过程
	 * @return String
	 */
	public static String getReturnGoods(Object data) {
		return "exec pr_ReturnGoods "+data;
	}
	
	
	/**
	 * 修改销售策略存储过程
	 * @return String
	 */
	public static String getUpdateSalesStrategy(int level,float discount,float prepayment,int ResponsiblePerson) {
		return "exec pr_UpdateSalesStrategy "+level+","+discount+","+prepayment+","+ResponsiblePerson;
	}

	/**
	 * 修改生产记录
	 */
	public static String getUpdateProductRecord(int No,int ResponsiblePerson) {
		return "exec pr_UpProductRecord "+No+","+ResponsiblePerson;
	}
}
