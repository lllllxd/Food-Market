package com.sdu.wh.dal;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FactoryDAL {

	/**
	 * �����鿴���������ƻ������鿴��Щ�ƻ�������״��
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getProductionState(Object col, Object value) throws Exception {
		String sql = SqlSentense.getSelect(col, value, "t_ProductRecord");
		String[] s = { "δ����", "��������", "�������" };
		SqlExecute se = new SqlExecute();
		JTable t = new JTable();
		t = se.getTable(sql);
		Object[][] data = new Object[t.getRowCount()][t.getColumnCount()];
		for (int i = 0; i < t.getRowCount(); i++) {
			for (int j = 0; j < t.getColumnCount(); j++) {
				DefaultTableModel tableModel = (DefaultTableModel) t.getModel();
				String cellData = (String) tableModel.getValueAt(i, j);
				// cellData�ǵ�һ�е�һ�е�����ֵ
				// getValueAT(a,b)���ص���Object���͵Ķ���
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
	 * �����������ɼƻ�״̬
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
