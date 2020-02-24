package com.sdu.wh.dao;

import com.sdu.wh.dal.SqlExecute;

public class PostTable {

	private int mUserNo;
	private String mPost;
	private String mDepartment;
	
	public PostTable() {
		super();
	}

	
	


	public PostTable(int mUserNo, String mPost, String mDepartment) {
		super();
		this.mUserNo = mUserNo;
		this.mPost = mPost;
		this.mDepartment = mDepartment;
	}


	public PostTable(String sql) throws Exception{
		super();
		String[] mClass=new String[3];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mUserNo = Integer.parseInt(mClass[0]);
		mPost = mClass[1];
		mDepartment = mClass[2];
	}



	public int getmUserNo() {
		return mUserNo;
	}





	public void setmUserNo(int mUserNo) {
		this.mUserNo = mUserNo;
	}





	public String getPost() {
		return mPost;
	}


	public void setPost(String post) {
		mPost = post;
	}


	public String getDepartment() {
		return mDepartment;
	}


	public void setDepartment(String department) {
		mDepartment = department;
	}
}
