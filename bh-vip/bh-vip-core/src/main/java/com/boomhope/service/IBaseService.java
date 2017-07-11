package com.boomhope.service;


/**
 * 服务层公共类
 * @author zy
 *
 */
public interface IBaseService {
	
	/**
	 * 根据指定序列类型，获取序列
	 * @param seqName
	 * @return
	 */
	public String getSeq(String seqType);

}
