package com.boomhope.service.imple;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boomhope.dao.BaseMapper;
import com.boomhope.service.IBaseService;
@Transactional
@Service(value="baseService")
public class BaseServiceImple implements IBaseService{

	@Resource(name="baseMapper")
	private BaseMapper baseMapper;
	
	@Override
	public String getSeq(String seqType) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("seqType", seqType);
		baseMapper.getSeq(map);
		return map.get("v_retcode").toString();
	}

}
