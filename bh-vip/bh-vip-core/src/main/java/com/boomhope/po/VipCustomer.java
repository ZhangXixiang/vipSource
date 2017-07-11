package com.boomhope.po;

public class VipCustomer {
    private String vipCusId;

    private String custNo;

    private String custName;

    private String custSex;

    private String creditType;

    private String creditNo;

    private String createDate;

    private String custLevel;

    private String custBir;
    

    public String getVipCusId() {
        return vipCusId;
    }

    public void setVipCusId(String vipCusId) {
        this.vipCusId = vipCusId == null ? null : vipCusId.trim();
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustSex() {
        return custSex;
    }

    public void setCustSex(String custSex) {
        this.custSex = custSex == null ? null : custSex.trim();
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType == null ? null : creditType.trim();
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo == null ? null : creditNo.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel == null ? null : custLevel.trim();
    }

	public String getCustBir() {
		return custBir;
	}

	public void setCustBir(String custBir) {
		this.custBir = custBir;
	}
}