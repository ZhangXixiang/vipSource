package com.boomhope.service.imple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.VipLogMapper;
import com.boomhope.model.VipLogExtendVo;
import com.boomhope.model.VipLogVo;
import com.boomhope.po.VipLog;
import com.boomhope.po.VipLogExtend;
import com.boomhope.service.IVipLogService;
@Transactional
@Service(value = "vipLogService")
public class VipLogServiceImple extends BaseServiceImple implements IVipLogService {
	
	/**
	 * 日志类
	 */
	private final static Logger logger = LoggerFactory.getLogger(VipLogServiceImple.class);
	
	@Resource(name = "vipLogMapper")
	private VipLogMapper vipLogMapper;
	
	@Override
	public List<VipLogExtendVo> queryVipLogList(Map<String, String> parMap) {
		List<VipLogExtend> list = vipLogMapper.queryVipLogList(parMap);
		if (list == null || list.size() == 0) {
			return null;
		}
		//将po对象转成vo对象
		List<VipLogExtendVo> voList = new ArrayList<VipLogExtendVo>();
		for(int i=0;i<list.size();i++){
			VipLogExtend vipLog = list.get(i);
			VipLogExtendVo vo = new VipLogExtendVo();
			vo.setFaceLogId(vipLog.getFaceLogId());
			vo.setComputeTime(vipLog.getComputeTime());
			vo.setCreateDate(vipLog.getCreateDate());
			vo.setFaceLogId(vipLog.getFaceLogId());
			vo.setResult(vipLog.getResult());
			
			vo.setSameScore(vipLog.getSameScore());
			vo.setSendImg(vipLog.getSendImg());
			vo.setCustNo(vipLog.getCustNo());
			vo.setErrorCode(vipLog.getErrorCode());
			vo.setErrorDesc(vipLog.getErrorDesc());
			
			vo.setStatus(vipLog.getStatus());
			vo.setUnitCode(vipLog.getUnitCode());
			vo.setVipCusId(vipLog.getVipCusId());
			
			vo.setCustName(vipLog.getCustName());
			vo.setCustNo(vipLog.getCustNo());
			
			voList.add(vo);
		}
		logger.info("查询vipLog表成功");
		return voList;
	}

	@Override
	public VipLogVo selectByFaceLogId(String faceLogId) {
		/**
		 * 根据face_log_id查询
		 */
		VipLog vipLog = vipLogMapper.selectByPrimaryKey(faceLogId);
		VipLogVo vo = new VipLogVo();
		vo.setFaceLogId(vipLog.getFaceLogId());
		vo.setComputeTime(vipLog.getComputeTime());
		vo.setCreateDate(vipLog.getCreateDate());
		vo.setFaceLogId(vipLog.getFaceLogId());
		vo.setResult(vipLog.getResult());
		
		vo.setSameScore(vipLog.getSameScore());
		vo.setSendImg(vipLog.getSendImg());
		vo.setCustNo(vipLog.getCustNo());
		vo.setErrorCode(vipLog.getErrorCode());
		vo.setErrorDesc(vipLog.getErrorDesc());
		
		vo.setStatus(vipLog.getStatus());
		vo.setUnitCode(vipLog.getUnitCode());
		vo.setVipCusId(vipLog.getVipCusId());
		return vo;

	}
	@Override
	public void insert(VipLogVo vipLogVo) {

		VipLog vipLog = new VipLog();
		vipLog.setFaceLogId(vipLogVo.getFaceLogId());
		vipLog.setComputeTime(vipLogVo.getComputeTime());
		vipLog.setCreateDate(vipLogVo.getCreateDate());
		vipLog.setFaceLogId(vipLogVo.getFaceLogId());
		vipLog.setResult(vipLogVo.getResult());
		
		vipLog.setSameScore(vipLogVo.getSameScore());
		vipLog.setSendImg(vipLogVo.getSendImg());
		vipLog.setCustNo(vipLogVo.getCustNo());
		vipLog.setErrorCode(vipLogVo.getErrorCode());
		vipLog.setErrorDesc(vipLogVo.getErrorDesc());
		
		vipLog.setStatus(vipLogVo.getStatus());
		vipLog.setUnitCode(vipLogVo.getUnitCode());
		vipLog.setVipCusId(vipLogVo.getVipCusId());
		vipLogMapper.insert(vipLog);

	}


	@Override
	public List<VipLogExtendVo> queryVipLogForPadList(Map<String, String> parMap) {
		List<VipLogExtend> list = vipLogMapper.queryVipLogForPadList(parMap);
		if (list == null || list.size() == 0) {
			return null;
		}
		//将po对象转成vo对象
		List<VipLogExtendVo> voList = new ArrayList<VipLogExtendVo>();
		for(int i=0;i<list.size();i++){
			VipLogExtend vipLog = list.get(i);
			VipLogExtendVo vo = new VipLogExtendVo();
			vo.setFaceLogId(vipLog.getFaceLogId());
			vo.setComputeTime(vipLog.getComputeTime());
			vo.setCreateDate(vipLog.getCreateDate());
			vo.setFaceLogId(vipLog.getFaceLogId());
			vo.setResult(vipLog.getResult());
			
			vo.setSameScore(vipLog.getSameScore());
			vo.setSendImg(vipLog.getSendImg());
			vo.setCustNo(vipLog.getCustNo());
			vo.setErrorCode(vipLog.getErrorCode());
			vo.setErrorDesc(vipLog.getErrorDesc());
			
			vo.setStatus(vipLog.getStatus());
			vo.setUnitCode(vipLog.getUnitCode());
			vo.setVipCusId(vipLog.getVipCusId());
			
			vo.setCustName(vipLog.getCustName());
			vo.setCustNo(vipLog.getCustNo());
			vo.setCustLevel(vipLog.getCustLevel());
			vo.setCustSex(vipLog.getCustSex());
			
			voList.add(vo);
		}
		logger.info("查询vipLog表成功");
		return voList;
	}
	
	
	@Override
	public List<VipLogVo> selectResultImgByDeployCode(String deployCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteByLogSaveTime(String logSaveTime) {
		
		vipLogMapper.deleteByLogSaveTime(logSaveTime);
		
	}
}
