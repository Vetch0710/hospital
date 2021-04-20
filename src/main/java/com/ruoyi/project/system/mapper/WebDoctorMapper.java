package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Map;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Repository
public interface WebDoctorMapper
{

    /**
     * 校验用户名称是否唯一
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebDoctor selectDoctorByUserId(Long userId);

    /**
     * 新增用户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    public int insertDoctor(@Param("doctor") WebDoctor doctor);

    /**
     * 新增账户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    public void insertDAccount(@Param("doctor") WebDoctor doctor);

    /**
     * 修改用户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    public int updateDoctor(@Param("doctor") WebDoctor doctor);
    /**
     * 修改用户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    public int updateDAccount(@Param("doctor") WebDoctor doctor);


    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateDoctorAvatar(@Param("userId")Long userId, @Param("avatar")String avatar);

}
