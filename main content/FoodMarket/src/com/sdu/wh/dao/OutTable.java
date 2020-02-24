package com.sdu.wh.dao;

import java.sql.Date;

import com.sdu.wh.dal.SqlExecute;

public class OutTable {
	
	private int mOutNo;
	private int mProductNo;
	private int mOutNum;
	private Date mOutDate;
	private int mResponsiblePerson;
	
	
	public OutTable() {
		super();
	}


	public OutTable(int mOutNo, int mProductNo, int mOutNum, Date mOutDate, int mResponsiblePerson) {
		super();
		this.mOutNo = mOutNo;
		this.mProductNo = mProductNo;
		this.mOutNum = mOutNum;
		this.mOutDate = mOutDate;
		this.mResponsiblePerson = mResponsiblePerson;
	}

	public OutTable(String sql) throws Exception{
		super();
		String[] mClass=new String[5];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mOutNo = Integer.parseInt(mClass[0]);
		mProductNo = Integer.parseInt(mClass[1]);
		mOutNum = Integer.parseInt(mClass[2]);
		mOutDate = java.sql.Date.valueOf(mClass[3]);
		mResponsiblePerson = Integer.parseInt(mClass[4]);
	}

	public int getmOutNo() {
		return mOutNo;
	}


	public void setmOutNo(int mOutNo) {
		this.mOutNo = mOutNo;
	}


	public int getmProductNo() {
		return mProductNo;
	}


	public void setmProductNo(int mProductNo) {
		this.mProductNo = mProductNo;
	}


	public int getmOutNum() {
		return mOutNum;
	}


	public void setmOutNum(int mOutNum) {
		this.mOutNum = mOutNum;
	}


	public Date getmOutDate() {
		return mOutDate;
	}


	public void setmOutDate(Date mOutDate) {
		this.mOutDate = mOutDate;
	}


	public int getmResponsiblePerson() {
		return mResponsiblePerson;
	}


	public void setmResponsiblePerson(int mResponsiblePerson) {
		this.mResponsiblePerson = mResponsiblePerson;
	}

	
	
}
