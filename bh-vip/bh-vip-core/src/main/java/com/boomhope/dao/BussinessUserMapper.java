package com.boomhope.dao;

import java.util.List;
import java.util.Map;

import com.boomhope.po.BussinessUser;
import com.boomhope.po.BussinessUserExtend;

public interface BussinessUserMapper {
	List<BussinessUserExtend> slectAll(Map<String, String> map);
	
    int deleteByPrimaryKey(List<String> string);

    int insert(BussinessUser bussinessUser);

    int insertSelective(BussinessUser bussinessUser);

    BussinessUser selectByPrimaryKey(String userCode);

    int updateByPrimaryKeySelective(BussinessUser bussinessUser);

    int updateByPrimaryKey(BussinessUser record);
    
    int enableBan(BussinessUser bussinessUser);
    
    List<BussinessUser> selectCombination(Map<String,String> map);
}