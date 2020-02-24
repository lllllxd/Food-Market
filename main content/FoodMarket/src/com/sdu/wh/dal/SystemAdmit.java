package com.sdu.wh.dal;

public class SystemAdmit {

	public static boolean createUser(String user,String key,String detail,String post,String department) throws Exception {
		String sql=SqlSentense.getCreateUser(user, key, detail, post, department);
		SqlExecute se=new SqlExecute();
		int r=se.executeSql(sql);
		if(r!=0) {
			return true;
		}
		else {
			return false;
		}
	}
}
