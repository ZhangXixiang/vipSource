package com.boomhope.dao;

import com.boomhope.po.MacMachine;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MacMachineMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mac_machine
     *
     * @mbg.generated
     */
    void deleteByPrimaryKey(@Param("ids")List<String> ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mac_machine
     *
     * @mbg.generated
     */
    int insert(MacMachine record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mac_machine
     *
     * @mbg.generated
     */
    MacMachine selectByPrimaryKey(String macId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mac_machine
     *
     * @mbg.generated
     */
    List<MacMachine> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mac_machine
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MacMachine record);
    
    /**
     * 根据指定条件查询设备表数据
     * @param map
     * @return
     */
    List<MacMachine> findMacMachineList(Map<String,String> map);
    /**
     * 根据mac_address查询设备表数据
     * @param map
     * @return
     */
    List<MacMachine> selectByMacAddress(Map<String,String> map);
    
    /**
	 * 根据规格型号查询设备信息
	 * @param macModel
	 * @return
	 */
    List<MacMachine> selectByMacModelList(String macModel);
    
    /**
     * 查询所有mac_model：该方法已经废弃，原来传下拉框的值只传了一个List<String>,现改为上面一个方法，传整个对象过去，方便二级联动，查询mac_brand的实现
     * author：zhangxx
     * @return
     */
    List<String> selectMacModel();
    
}