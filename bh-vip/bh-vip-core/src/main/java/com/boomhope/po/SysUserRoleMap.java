package com.boomhope.po;

public class SysUserRoleMap {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role_map.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role_map.login_name
     *
     * @mbg.generated
     */
    private String loginName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_role_map.role_code
     *
     * @mbg.generated
     */
    private String roleCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role_map.id
     *
     * @return the value of sys_user_role_map.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role_map.id
     *
     * @param id the value for sys_user_role_map.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role_map.login_name
     *
     * @return the value of sys_user_role_map.login_name
     *
     * @mbg.generated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role_map.login_name
     *
     * @param loginName the value for sys_user_role_map.login_name
     *
     * @mbg.generated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_role_map.role_code
     *
     * @return the value of sys_user_role_map.role_code
     *
     * @mbg.generated
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_role_map.role_code
     *
     * @param roleCode the value for sys_user_role_map.role_code
     *
     * @mbg.generated
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}