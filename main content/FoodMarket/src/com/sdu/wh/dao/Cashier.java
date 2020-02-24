package com.sdu.wh.dao;

import java.sql.Date;

import com.sdu.wh.dal.SqlExecute;

public class Cashier {

	private int mNo;
	private float mAmoney;
	private Date mDate;
	private String mDetail;
	private String mOrderFormNo;
	
	public Cashier() {
		super();
	}

	
	public Cashier(float amoney, Date date, String detail, String aNo) {
		super();
		mAmoney = amoney;
		mDate = date;
		mDetail = detail;
		mOrderFormNo = aNo;
	}
	
	public Cashier(String sql) throws Exception{
		super();
		String[] mClass=new String[7];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mNo = Integer.parseInt(mClass[0]);
		mAmoney = (float) Double.parseDouble(mClass[1]);
		mDate = java.sql.Date.valueOf(mClass[2]);
		mDetail = mClass[3];
		mOrderFormNo = mClass[4];
	}

	public float getAmoney() {
		return mAmoney;
	}


	public void setAmoney(float amoney) {
		mAmoney = amoney;
	}


	public Date getDate() {
		return mDate;
	}


	public void setDate(Date date) {
		mDate = date;
	}


	public String getDetail() {
		return mDetail;
	}


	public void setDetail(String detail) {
		mDetail = detail;
	}


	public String getANo() {
		return mOrderFormNo;
	}


	public void setANo(String aNo) {
		mOrderFormNo = aNo;
	}
}
