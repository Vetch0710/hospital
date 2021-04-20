package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface WebDoctorService
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
    public int insertDoctor(WebDoctor doctor) throws Exception;

    /**
     * 修改用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    public int updateDoctor(WebDoctor doctor) throws Exception;


    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateDoctorAvatar(Long userId, String avatar);


}
