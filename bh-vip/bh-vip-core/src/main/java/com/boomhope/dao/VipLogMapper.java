package com.boomhope.dao;

import java.util.List;
import java.util.Map;

import com.boomhope.po.VipLog;
import com.boomhope.po.VipLogExtend;

public interface VipLogMapper {
    int deleteByPrimaryKey(String faceLogId);

    int insert(VipLog record);

    int insertSelective(VipLog record);

    VipLog selectByPrimaryKey(String faceLogId);

    int updateByPrimaryKeySelective(VipLog record);

    int updateByPrimaryKey(VipLog record);

    /**
     * 根据条件查询vipLog表，条件为null查询所有
     * @param parMap
     * @author liu.zh
     * @return
     */
	List<VipLogExtend> queryVipLogList(Map<String, String> parMap);
	
	/**
	 * 根据条件查询vipLog表，（pad推送专用）
	 */
	List<VipLogExtend> queryVipLogForPadList(Map<String, String> parMap);
	
	/**
	 * 根据日志保留时间删除vip_log中的数据；
	 * @param logSaveTime
	 */
	void deleteByLogSaveTime(String logSaveTime);
}