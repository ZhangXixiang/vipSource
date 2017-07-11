package com.boomhope.dao;

import com.boomhope.po.PushRule;

public interface PushRuleMapper {
    int deleteByPrimaryKey(String ruleId);

//    增加
    int insert(PushRule record);

    int insertSelective(PushRule record);

    PushRule selectByPrimaryKey(String ruleId);

    int updateByPrimaryKeySelective(PushRule record);

    int updateByPrimaryKey(PushRule record);
}