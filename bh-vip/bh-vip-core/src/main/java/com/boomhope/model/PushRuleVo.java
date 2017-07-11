package com.boomhope.model;

public class PushRuleVo {
    private String ruleId;

    private String threshold;

    private String timeInterval;

    private String logSaveTime;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold == null ? null : threshold.trim();
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval == null ? null : timeInterval.trim();
    }

    public String getLogSaveTime() {
        return logSaveTime;
    }

    public void setLogSaveTime(String logSaveTime) {
        this.logSaveTime = logSaveTime == null ? null : logSaveTime.trim();
    }
}