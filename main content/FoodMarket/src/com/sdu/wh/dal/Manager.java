package com.sdu.wh.dal;

public class Manager {

	/**
	 * 查询订单
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getOrderForm() throws Exception{
		String sql = SqlSentense.getselectvw("vw_BossOrderForm");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * 查询提货单
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getBillOfLading() throws Exception{
		String sql = SqlSentense.getselectvw("vw_BossBillOfLading");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * 查询生产计划
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getProductionSchedule() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_ProductionSchedule");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * 查询入库表
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getInTable() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_InTable");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * 查询出库表
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getOutTable() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_OutTable");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * 查询生产记录
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getProductRecord() throws Exception{
		String sql = SqlSentense.getselectvw("vw_BossProductRecord");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
	
	/**
	 * 查询库存表
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getInventory() throws Exception{
		String sql = SqlSentense.getSelect(0, 0, "t_Inventory");
		Object[][] result = SqlExecute.getObject(sql);
		return result;
	}
}
