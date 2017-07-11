package com.boomhope.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseMapper {

	public void getSeq(Map map);
}
