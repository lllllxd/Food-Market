package com.sdu.wh.dao;

import com.sdu.wh.dal.SqlExecute;

public class SalesStrategy {

	private int mLevel;
	private float mDiscount;//ÕÛ¿Û
	private float mPrepaymentRatio;//Ô¤¸¶¿î±ÈÀý
	private int mResponsiblePerson;
	
	public SalesStrategy() {
		super();
	}

	public SalesStrategy(int mLevel, float mDiscount, float mPrepaymentRatio, int mResponsiblePerson) {
		super();
		this.mLevel = mLevel;
		this.mDiscount = mDiscount;
		this.mPrepaymentRatio = mPrepaymentRatio;
		this.mResponsiblePerson = mResponsiblePerson;
	}

	public SalesStrategy(String sql) throws Exception{
		super();
		String[] mClass=new String[4];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass(sql);
		mLevel = Integer.parseInt(mClass[0]);
		mDiscount = (float)Double.parseDouble(mClass[1]);
		mPrepaymentRatio = (float)Double.parseDouble(mClass[2]);
		mResponsiblePerson = Integer.parseInt(mClass[3]);
	}
	
	public int getmLevel() {
		return mLevel;
	}

	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}

	public float getmDiscount() {
		return mDiscount;
	}

	public void setmDiscount(float mDiscount) {
		this.mDiscount = mDiscount;
	}

	public float getmPrepaymentRatio() {
		return mPrepaymentRatio;
	}

	public void setmPrepaymentRatio(float mPrepaymentRatio) {
		this.mPrepaymentRatio = mPrepaymentRatio;
	}

	public int getmResponsiblePerson() {
		return mResponsiblePerson;
	}

	public void setmResponsiblePerson(int mResponsiblePerson) {
		this.mResponsiblePerson = mResponsiblePerson;
	}

	
	
}
