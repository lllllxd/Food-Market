package com.sdu.wh.dal;

public class LoginDAL {
	
	public static boolean UserLogin(String id, String password) {
		/**
		 * 数据库操作 
		 */
		String sql="select * from t_UserTable where p_User = '" +  id+ "' and p_Key= '" + password + "'";
		SqlExecute se = new SqlExecute();
		String[] data=null;
		try {
			data=se.getClass(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(data!=null) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	/**
	 * 返回会员编号和姓名
	 * data[0],data[3]分别是管理员编号和管理员姓名
	 */
	public static String[] getNoName(String id) {
		String sql=SqlSentense.getSelect("p_User", "'"+id+"'", "t_UserTable");
		SqlExecute se = new SqlExecute();
		String[] data=null;
		try {
			data=se.getClass(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
}
