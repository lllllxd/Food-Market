package com.sdu.wh.dao;

import java.sql.Date;

import com.sdu.wh.dal.SqlExecute;

public class Inventory {
	
	private Date mInventoryDate;
	private int mProductNo;
	private int mInventoryNum;
	private Date mDestoryDate;
	
	
	public Inventory() {
		super();
	}


	public Inventory(Date mInventoryDate, int mProductNo, int mInventoryNum, Date mDestoryDate) {
		super();
		this.mInventoryDate = mInventoryDate;
		this.mProductNo = mProductNo;
		this.mInventoryNum = mInventoryNum;
		this.mDestoryDate = mDestoryDate;
	}

	public Inventory(String sql) throws Exception{
		super();
		String[] mClass=new String[7];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mInventoryDate = java.sql.Date.valueOf(mClass[0]);
		mProductNo = Integer.parseInt(mClass[1]);
		mInventoryNum = Integer.parseInt(mClass[2]);
		mDestoryDate = java.sql.Date.valueOf(mClass[3]);
	}

	public Date getmInventoryDate() {
		return mInventoryDate;
	}


	public void setmInventoryDate(Date mInventoryDate) {
		this.mInventoryDate = mInventoryDate;
	}


	public int getmProductNo() {
		return mProductNo;
	}


	public void setmProductNo(int mProductNo) {
		this.mProductNo = mProductNo;
	}


	public int getmInventoryNum() {
		return mInventoryNum;
	}


	public void setmInventoryNum(int mInventoryNum) {
		this.mInventoryNum = mInventoryNum;
	}


	public Date getmDestoryDate() {
		return mDestoryDate;
	}


	public void setmDestoryDate(Date mDestoryDate) {
		this.mDestoryDate = mDestoryDate;
	}

	
	
}
