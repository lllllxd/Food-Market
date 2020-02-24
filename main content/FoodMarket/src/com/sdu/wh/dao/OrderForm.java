package com.sdu.wh.dao;

import java.sql.Date;
import java.util.Calendar;

/**
 * 订单类，用于在程序内临时保存订单数据
 * 
 * @author 刘杰
 *
 */
public class OrderForm {

	private String mCustomerNo;		/*客户编号*/
	private String mCustomerName;	/*客户名称*/
	private Object[][] mData;		/*订单内容*/
	private Calendar mTakingDate;	/*提货日期*/
	private Calendar mDate;			/*订单日期*/
	private String mPersonId;		/*负责人编号*/
	private String mPersonName;		/*负责人名称*/
	
	public String getCustomerNo() {
		return mCustomerNo;
	}
	public void setCustomerNo(String customerNo) {
		mCustomerNo = customerNo;
	}
	public String getCustomerName() {
		return mCustomerName;
	}
	public void setCustomerName(String customerName) {
		mCustomerName = customerName;
	}
	public Object[][] getData() {
		return mData;
	}
	public void setData(Object[][] data) {
		mData = data;
	}
	public Calendar getTakingDate() {
		return mTakingDate;
	}
	public void setTakingDate(Calendar takingDate) {
		mTakingDate = takingDate;
	}
	public Calendar getDate() {
		return mDate;
	}
	public void setDate() {
		mDate = Calendar.getInstance();
	}
	public String getPersonId() {
		return mPersonId;
	}
	public void setPersonId(String personId) {
		mPersonId = personId;
	}
	public String getPersonName() {
		return mPersonName;
	}
	public void setPersonName(String personName) {
		mPersonName = personName;
	}
	
	
}
