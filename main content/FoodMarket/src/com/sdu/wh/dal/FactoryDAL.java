package com.sdu.wh.dal;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FactoryDAL {

	/**
	 * 工厂查看所有生产计划，并查看这些计划的生产状况
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getProductionState(Object col, Object value) throws Exception {
		String sql = SqlSentense.getSelect(col, value, "t_ProductRecord");
		String[] s = { "未生产", "正在生产", "生产完毕" };
		SqlExecute se = new SqlExecute();
		JTable t = new JTable();
		t = se.getTable(sql);
		Object[][] data = new Object[t.getRowCount()][t.getColumnCount()];
		for (int i = 0; i < t.getRowCount(); i++) {
			for (int j = 0; j < t.getColumnCount(); j++) {
				DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
				String cellData = (String) tableModel.getValueAt(i, j);
				// cellData是第一行第一列的数据值
				// getValueAT(a,b)返回的是Object类型的对象
				if (j == 3) {
					data[i][j] = s[Integer.parseInt(cellData)];
				} else {
					data[i][j] = cellData;
				}
			}
		}
		return data;
	}

	/**
	 * 工厂更新生成计划状态
	 * 
	 * @throws Exception
	 */
	public static boolean upSchedule(int No, int Person) throws Exception {
		String sql = SqlSentense.getUpdateProductRecord(No, Person);
		SqlExecute se = new SqlExecute();
		int r = se.executeSql(sql);
		if (r != 0)
			return true;
		else
			return false;
	}

}
