package com.sdu.wh.dao;

import com.sdu.wh.dal.SqlExecute;

public class UserTable {

	private int mUserNo;
	private String mUser;
	private String mKey;
	private String mDetail;
	
	public UserTable() {
		super();
	}

	
	


	public UserTable(int mUserNo, String mUser, String mKey, String mDetail) {
		super();
		this.mUserNo = mUserNo;
		this.mUser = mUser;
		this.mKey = mKey;
		this.mDetail = mDetail;
	}


	public UserTable(String sql) throws Exception{
		super();
		String[] mClass=new String[9];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mUserNo = Integer.parseInt(mClass[0]);
		mUser = mClass[1];
		mKey = mClass[2];
		mDetail = mClass[3];
	}


	public int getmUserNo() {
		return mUserNo;
	}





	public void setmUserNo(int mUserNo) {
		this.mUserNo = mUserNo;
	}





	public String getUser() {
		return mUser;
	}


	public void setUser(String user) {
		mUser = user;
	}


	public String getKey() {
		return mKey;
	}


	public void setKey(String key) {
		mKey = key;
	}


	public String getDetail() {
		return mDetail;
	}


	public void setDetail(String detail) {
		mDetail = detail;
	}
}
