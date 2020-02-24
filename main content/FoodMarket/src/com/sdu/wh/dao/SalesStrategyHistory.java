package com.sdu.wh.dao;

import java.sql.Date;

import com.sdu.wh.dal.SqlExecute;

public class SalesStrategyHistory {

	private int mSalesStrategyHistoryNo;
	private Date mDate;
	private int mResponsiblePerson;
	private String mDetail;
	
	public SalesStrategyHistory() {
		super();
	}

	public SalesStrategyHistory(int mSalesStrategyHistoryNo, Date mDate, int mResponsiblePerson, String mDetail) {
		super();
		this.mSalesStrategyHistoryNo = mSalesStrategyHistoryNo;
		this.mDate = mDate;
		this.mResponsiblePerson = mResponsiblePerson;
		this.mDetail = mDetail;
	}
	
	public SalesStrategyHistory(String sql) throws Exception{
		super();
		String[] mClass=new String[4];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mSalesStrategyHistoryNo = Integer.parseInt(mClass[0]);
		mDate = java.sql.Date.valueOf(mClass[1]);
		mResponsiblePerson = Integer.parseInt(mClass[2]);
		mDetail = mClass[3];
	}

	public int getmSalesStrategyHistoryNo() {
		return mSalesStrategyHistoryNo;
	}

	public void setmSalesStrategyHistoryNo(int mSalesStrategyHistoryNo) {
		this.mSalesStrategyHistoryNo = mSalesStrategyHistoryNo;
	}

	public Date getmDate() {
		return mDate;
	}

	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	public int getmResponsiblePerson() {
		return mResponsiblePerson;
	}

	public void setmResponsiblePerson(int mResponsiblePerson) {
		this.mResponsiblePerson = mResponsiblePerson;
	}

	public String getmDetail() {
		return mDetail;
	}

	public void setmDetail(String mDetail) {
		this.mDetail = mDetail;
	}

	
	
}
