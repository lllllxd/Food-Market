package com.sdu.wh.dao;

import com.sdu.wh.dal.SqlExecute;

public class Customer {
	
	private int mNo;
	private String mName;
	private int mLevel;
	private String mDetail;
	
	public Customer() {
		super();
	}
	
	
	public Customer(int No,String Name,int Level,String Detail) {
		super();
		this.mNo=No;
		this.mName=Name;
		this.mLevel=Level;
		this.mDetail=Detail;
	}
	
	public Customer(String sql) throws Exception{
		super();
		String[] mClass=new String[7];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mNo = Integer.parseInt(mClass[0]);
		mName = mClass[1];
		mLevel = Integer.parseInt(mClass[2]);
		mDetail = mClass[3];
	}
	
	public int getNo() {
		return mNo;
	}
	
	
	public void setNo(int No) {
		mNo=No;
	}
	
	
	public String getName() {
		return mName;
	}
	
	
	public void setName(String Name) {
		mName=Name;
	}
	
	
	public int getLevel() {
		return mLevel;
	}
	
	
	public void setLevel(int Level) {
		mLevel=Level;
	}


	public String getDetail() {
		return mDetail;
	}


	public void setDetail(String detail) {
		mDetail = detail;
	}
}
