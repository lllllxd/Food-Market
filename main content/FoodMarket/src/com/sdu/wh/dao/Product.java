package com.sdu.wh.dao;

public class Product {

	private int mNo;				/*产品编号*/
	private String mName;			/*产品名称*/
	private int mShelfLife;			/*保质期*/
	private int mProductionCycle;	/*生产周期*/
	private float mPrice;			/*产品价格*/
	
	public Product() {
		super();
	}

	public Product(int mNo, String mName, int mShelfLife, int mProductionCycle, float mPrice) {
		super();
		this.mNo = mNo;
		this.mName = mName;
		this.mShelfLife = mShelfLife;
		this.mProductionCycle = mProductionCycle;
		this.mPrice = mPrice;
	}

	public int getmNo() {
		return mNo;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getmShelfLife() {
		return mShelfLife;
	}

	public void setmShelfLife(int mShelfLife) {
		this.mShelfLife = mShelfLife;
	}

	public int getmProductionCycle() {
		return mProductionCycle;
	}

	public void setmProductionCycle(int mProductionCycle) {
		this.mProductionCycle = mProductionCycle;
	}

	public float getmPrice() {
		return mPrice;
	}

	public void setmPrice(float mPrice) {
		this.mPrice = mPrice;
	}


	
}
