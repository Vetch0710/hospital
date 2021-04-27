package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.WebPatient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Repository
public interface WebPatientMapper
{

    /**
     * 校验用户名称是否唯一
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebPatient selectPatientByUserId(Long userId);


    /**
     * 查询用户状态
     *
     * @param userId 身份证
     * @return 结果
     */
    public Map<String,Object> selectPSBByUserId(Long userId);

    /**
     * 校验用户名称是否唯一
     *
     * @param idCard 身份证
     * @return 结果
     */
    public int checkIdCardUnique(@Param("idCard")String idCard);
    public int checkNameUnique(@Param("name")String name);
    public String selectNameByUserId(@Param("userId")Long userId);


    /**
     * 新增用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public int insertPatient(@Param("patient")WebPatient patient);

    /**
     * 新增账户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public void insertAccount(@Param("patient")WebPatient patient);

    /**
     * 修改用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public int updatePatient(@Param("patient")WebPatient patient);
    /**
     * 修改用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public int updateAccount(@Param("patient")WebPatient patient);

    /**
     * 修改用户状态
     *
     * @param status 用户信息
     * @param banTime 禁止时间
     * @return 结果
     */
    public int updatePatientStatus(@Param("status") char status,@Param("banTime") String banTime,@Param("userId") Long userId);



}
