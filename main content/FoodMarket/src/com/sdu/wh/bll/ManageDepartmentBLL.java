package com.sdu.wh.bll;

import com.sdu.wh.dal.SqlExecute;
import com.sdu.wh.dal.SqlSentense;

public class ManageDepartmentBLL {
	
	/**
	 * 创建新员工账号
	 * @param id
	 * @param name
	 * @param password
	 * @param level 
	 * @return
	 */
	public boolean createManager(String id, String name, String password, int post) {
		if(id.length() == 0 || name.length() == 0 || password.length() == 0) {
			return false;
		}
		String sql=SqlSentense.getCreateUser(id, password, name, post + "", "");
		SqlExecute se =new SqlExecute();
		try {
			se.executeSql(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
