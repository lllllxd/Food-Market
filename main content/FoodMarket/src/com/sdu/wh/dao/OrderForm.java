package com.sdu.wh.dao;

import java.sql.Date;
import java.util.Calendar;

/**
 * �����࣬�����ڳ�������ʱ���涩������
 * 
 * @author ����
 *
 */
public class OrderForm {

	private String mCustomerNo;		/*�ͻ����*/
	private String mCustomerName;	/*�ͻ�����*/
	private Object[][] mData;		/*��������*/
	private Calendar mTakingDate;	/*�������*/
	private Calendar mDate;			/*��������*/
	private String mPersonId;		/*�����˱��*/
	private String mPersonName;		/*����������*/
	
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
