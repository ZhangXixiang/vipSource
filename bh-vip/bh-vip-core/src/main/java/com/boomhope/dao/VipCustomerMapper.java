package com.boomhope.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.boomhope.po.DeployUnit;
import com.boomhope.po.VipCustomer;
@Repository
public interface VipCustomerMapper {
    int deleteByPrimaryKey(String vipCusId);

    int insert(VipCustomer record);

    int insertSelective(VipCustomer record);

    VipCustomer selectByPrimaryKey(String vipCusId);

    int updateByPrimaryKeySelective(VipCustomer record);

    int updateByPrimaryKey(VipCustomer record);
    
    List<VipCustomer> findVipCustomerList(Map<String,String>parMap);
    
    //根据客户证件号码来查询VIP客户维护信息，添加业务无里面需要用到的逻辑
    List<VipCustomer> selectByCustNoList(Map<String, String> parMap);
    //删除客户信息
    void deleteCustlist(List<String> userIds);
    /**
     * 导入excel的插入
     */
    void importInsert(List<VipCustomer> list);

}