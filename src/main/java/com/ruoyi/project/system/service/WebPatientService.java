package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface WebPatientService
{

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebPatient selectPatientByUserId(Long userId);

    /**
     * 校验用户名称是否唯一
     *
     * @param idCard 身份证
     * @return 结果
     */
    public boolean checkPatientUnique(String idCard,String name);


    /**
     * 新增用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public int insertPatient(WebPatient patient) throws Exception;

    /**
     * 修改用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public int updatePatient(WebPatient patient) throws Exception;


    /**
     * 修改用户信息
     *
     * @param userId 账户id
     * @return 结果
     */
    public int updatePatientStatus(Long userId);



}
