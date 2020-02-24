package com.sdu.wh.dao;

import java.sql.Date;
import com.sdu.wh.dal.SqlExecute;

public class BillOfLading {

	private int mBillOfLadingNo;
	private int mCustomerNo;
	private int mProductNo;
	private int mOrderNum;
	private Date mBillOfLadingDate;//提货日期
	private Date mMakeBillOfLadingDate;//提货单产生日期
	private String mResponsiblePerson;
	
	public BillOfLading() {
		super();
	}
	

	public BillOfLading(int billOfLadingNo, int customerNo, int productNo, int orderNum, Date billOfLadingDate,
			Date makeBillOfLadingDate, String responsiblePerson) {
		super();
		mBillOfLadingNo = billOfLadingNo;
		mCustomerNo = customerNo;
		mProductNo = productNo;
		mOrderNum = orderNum;
		mBillOfLadingDate = billOfLadingDate;
		mMakeBillOfLadingDate = makeBillOfLadingDate;
		mResponsiblePerson = responsiblePerson;
	}


	public BillOfLading(String sql) throws Exception {
		super();
		String[] mClass=new String[7];
		SqlExecute se = new SqlExecute();
		mClass=se.getClass( sql);
		mBillOfLadingNo = Integer.parseInt(mClass[0]);
		mCustomerNo = Integer.parseInt(mClass[1]);
		mProductNo = Integer.parseInt(mClass[2]);
		mOrderNum = Integer.parseInt(mClass[3]);
		mBillOfLadingDate = java.sql.Date.valueOf(mClass[4]);
		mMakeBillOfLadingDate = java.sql.Date.valueOf(mClass[5]);
		mResponsiblePerson = mClass[6];
	}

	
	public int getBillOfLadingNo() {
		return mBillOfLadingNo;
	}


	public void setBillOfLadingNo(int billOfLadingNo) {
		mBillOfLadingNo = billOfLadingNo;
	}


	public int getCustomerNo() {
		return mCustomerNo;
	}


	public void setCustomerNo(int customerNo) {
		mCustomerNo = customerNo;
	}


	public int getProductNo() {
		return mProductNo;
	}


	public void setProductNo(int productNo) {
		mProductNo = productNo;
	}


	public int getOrderNum() {
		return mOrderNum;
	}


	public void setOrderNum(int orderNum) {
		mOrderNum = orderNum;
	}


	public Date getBillOfLadingDate() {
		return mBillOfLadingDate;
	}


	public void setBillOfLadingDate(Date billOfLadingDate) {
		mBillOfLadingDate = billOfLadingDate;
	}


	public Date getMakeBillOfLadingDate() {
		return mMakeBillOfLadingDate;
	}


	public void setMakeBillOfLadingDate(Date makeBillOfLadingDate) {
		mMakeBillOfLadingDate = makeBillOfLadingDate;
	}


	public String getResponsiblePerson() {
		return mResponsiblePerson;
	}


	public void setResponsiblePerson(String responsiblePerson) {
		mResponsiblePerson = responsiblePerson;
	}
}
