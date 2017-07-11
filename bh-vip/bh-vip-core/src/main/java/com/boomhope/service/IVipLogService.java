package com.boomhope.service;

import java.util.List;
import java.util.Map;

import com.boomhope.model.VipLogExtendVo;
import com.boomhope.model.VipLogVo;

public interface IVipLogService {

	/**
	 * 查询设备部署信息列表 sql 查询
	 * 
	 * @param parMap
	 * @return
	 */
	public List<VipLogExtendVo> queryVipLogList(Map<String, String> parMap);
	/**
	 * 查询设备部署信息列表 sql 查询
	 * 
	 * @param parMap
	 * @return
	 */
	public List<VipLogExtendVo> queryVipLogForPadList(Map<String, String> parMap);

	/**
	 * 根据face_login_id查找信息
	 */
	public VipLogVo selectByFaceLogId(String faceLogId);

	/**
	 * 
	 * @Description: TODO(新增识别日志)
	 * @param:
	 * @return: void
	 * @throws
	 * @author:zhangxx
	 */
	public void insert(VipLogVo vipLogVo);
	
	/**
	 * 查找命中图片信息
	 */
	public List<VipLogVo> selectResultImgByDeployCode(String deployCode);
	
	/**
	 * 根据时间间隔删除vip_log表中的数据
	 * @param logSaveTime
	 */
	public void deleteByLogSaveTime(String logSaveTime);
	
	
	
}
