package com.boomhope.po;

import com.boomhope.po.VipLog;

public class VipLogExtend extends VipLog {
	
	private String custName;
	
	private String  unitName;
	
	private String custSex;
	
	private String custLevel;
	
	
	
	public String getCustSex() {
		return custSex;
	}

	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	
	
}
