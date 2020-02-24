package com.sdu.wh.bll;

import com.sdu.wh.dal.LoginDAL;
import com.sdu.wh.ui.MainForm;

public class LoginBLL {
	
	/**
	 * µÇÂ¼
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean UserLogin(String id, String password) {
		
		if (id.length() == 0 || password.length() == 0) {
			return false;
		}
		boolean res = LoginDAL.UserLogin(id, password); 
		if(res == true) {
		
			String[] strings = LoginDAL.getNoName(id);
			MainForm.setId(strings[0]);
			MainForm.setPName(strings[3]);
			System.out.println(strings[0] + " " + strings[3]);
			return true;
		} else {
			return false;
		}
	}
}
