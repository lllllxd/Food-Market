package com.sdu.wh.dao;

import java.sql.Date;

import com.sdu.wh.dal.SqlExecute;


public class InTable {

	private int mNo;
	private int mInNum;
	private Date mInDate;
	private int mProductNo;
	private int mResponsiblePerson;
	
	public InTable() {
		super();
	}

	public InTable(int no, int inNum, Date inDate, int productNo, int responsiblePerson) {
		super();
		mNo = no;
		mInNum = inNum;
		mInDate = inDate;
		mProductNo = productNo;
		mResponsiblePerson = responsiblePerson;
	}

	public InTable(String sql) throws Exception{
		super();
		String[] mClass=new String[7];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mNo = Integer.parseInt(mClass[0]);
		mInNum = Integer.parseInt(mClass[1]);
		mInDate = java.sql.Date.valueOf(mClass[2]);
		mProductNo = Integer.parseInt(mClass[3]);
		mResponsiblePerson = Integer.parseInt(mClass[4]);
	}
	
	public int getNo() {
		return mNo;
	}

	
	public void setNo(int no) {
		mNo = no;
	}

	
	public int getInNum() {
		return mInNum;
	}

	
	public void setInNum(int inNum) {
		mInNum = inNum;
	}

	
	public Date getInDate() {
		return mInDate;
	}

	
	public void setInDate(Date inDate) {
		mInDate = inDate;
	}

	
	public int getProductNo() {
		return mProductNo;
	}

	
	public void setProductNo(int productNo) {
		mProductNo = productNo;
	}

	
	public int getResponsiblePerson() {
		return mResponsiblePerson;
	}

	
	public void setResponsiblePerson(int responsiblePerson) {
		mResponsiblePerson = responsiblePerson;
	}
}
