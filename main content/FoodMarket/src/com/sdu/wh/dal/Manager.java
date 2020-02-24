package com.sdu.wh.dal;

public class Manager {

	/**
	 * ��ѯ����
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getOrderForm() throws Exception{
		String sql = SqlSentense.getselectvw("vw_BossOrderForm");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * ��ѯ�����
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getBillOfLading() throws Exception{
		String sql = SqlSentense.getselectvw("vw_BossBillOfLading");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * ��ѯ�����ƻ�
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getProductionSchedule() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_ProductionSchedule");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * ��ѯ����
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getInTable() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_InTable");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * ��ѯ�����
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getOutTable() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_OutTable");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * ��ѯ������¼
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getProductRecord() throws Exception{
		String sql = SqlSentense.getselectvw("vw_BossProductRecord");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * ��ѯ����
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getInventory() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_Inventory");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
}
