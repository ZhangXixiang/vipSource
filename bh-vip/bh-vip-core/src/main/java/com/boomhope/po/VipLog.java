package com.boomhope.po;

public class VipLog {
    private String faceLogId;

    private String vipCusId;

    private String custNo;

    private String sameScore;

    private String errorCode;

    private String errorDesc;

    private String createDate;

    private String computeTime;

    private String status;
    
    private String sendImg;
    
    private String result;
    
    private String unitCode;
    
    

    public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getSendImg() {
		return sendImg;
	}

	public void setSendImg(String sendImg) {
		this.sendImg = sendImg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFaceLogId() {
        return faceLogId;
    }

    public void setFaceLogId(String faceLogId) {
        this.faceLogId = faceLogId == null ? null : faceLogId.trim();
    }

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

    public String getSameScore() {
        return sameScore;
    }

    public void setSameScore(String sameScore) {
        this.sameScore = sameScore == null ? null : sameScore.trim();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc == null ? null : errorDesc.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getComputeTime() {
        return computeTime;
    }

    public void setComputeTime(String computeTime) {
        this.computeTime = computeTime == null ? null : computeTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}