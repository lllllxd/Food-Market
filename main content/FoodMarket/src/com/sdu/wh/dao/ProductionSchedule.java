package com.sdu.wh.dao;

import java.sql.Date;

import com.sdu.wh.dal.SqlExecute;

public class ProductionSchedule {

	private int mProductionScheduleNo;	
	private int mProductNo;
	private int mProductionNum;
	private Date mProductionScheduleDate;//生产计划表日期
	private Date mProductionBuildDate;//生产产品时间
	private int mResponsiblePerson;

	
	public ProductionSchedule() {
		super();
	}


	public ProductionSchedule(int mProductionScheduleNo, int mProductNo, int mProductionNum,
			Date mProductionScheduleDate, Date mProductionBuildDate, int mResponsiblePerson) {
		super();
		this.mProductionScheduleNo = mProductionScheduleNo;
		this.mProductNo = mProductNo;
		this.mProductionNum = mProductionNum;
		this.mProductionScheduleDate = mProductionScheduleDate;
		this.mProductionBuildDate = mProductionBuildDate;
		this.mResponsiblePerson = mResponsiblePerson;
	}

	public ProductionSchedule(String sql) throws Exception{
		super();
		String[] mClass=new String[6];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mProductionScheduleNo = Integer.parseInt(mClass[0]);
		mProductNo = Integer.parseInt(mClass[1]);
		mProductionNum = Integer.parseInt(mClass[2]);
		mProductionScheduleDate = java.sql.Date.valueOf(mClass[3]);
		mProductionBuildDate = java.sql.Date.valueOf(mClass[4]);
		mResponsiblePerson = Integer.parseInt(mClass[5]);
	}

	public int getmProductionScheduleNo() {
		return mProductionScheduleNo;
	}


	public void setmProductionScheduleNo(int mProductionScheduleNo) {
		this.mProductionScheduleNo = mProductionScheduleNo;
	}


	public int getmProductNo() {
		return mProductNo;
	}


	public void setmProductNo(int mProductNo) {
		this.mProductNo = mProductNo;
	}


	public int getmProductionNum() {
		return mProductionNum;
	}


	public void setmProductionNum(int mProductionNum) {
		this.mProductionNum = mProductionNum;
	}


	public Date getmProductionScheduleDate() {
		return mProductionScheduleDate;
	}


	public void setmProductionScheduleDate(Date mProductionScheduleDate) {
		this.mProductionScheduleDate = mProductionScheduleDate;
	}


	public Date getmProductionBuildDate() {
		return mProductionBuildDate;
	}


	public void setmProductionBuildDate(Date mProductionBuildDate) {
		this.mProductionBuildDate = mProductionBuildDate;
	}


	public int getmResponsiblePerson() {
		return mResponsiblePerson;
	}


	public void setmResponsiblePerson(int mResponsiblePerson) {
		this.mResponsiblePerson = mResponsiblePerson;
	}
	
	
	
}
