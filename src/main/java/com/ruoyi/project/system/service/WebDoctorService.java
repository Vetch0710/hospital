package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.domain.vo.QueryVo;

import java.util.List;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface WebDoctorService
{
//---------------------------------以下是医生预约信息操作-----------------------
    /**
     * 通过查询条件查询用户
     *
     * @param queryVo 查询条件
     * @return 员工信息集合信息
     */
    public List<ADoctor> selectADoctorByQuery(QueryVo queryVo);


    /**
     * 新增员工信息
     *
     * @param staff 员工信息
     * @return 结果
     */
    public int insertADoctor(ADoctor aDoctor) throws Exception;

    /**
     * 修改员工信息
     *
     * @param staff 员工信息
     * @return 结果
     */
    public int updateADoctor(ADoctor aDoctor) ;

    /**
     * 通过员工编号删除员工
     *
     * @param aDoctor 员工编号
     * @return 结果
     */
    public int deleteADoctorById(ADoctor aDoctor) throws Exception;




//--------------------------------以下是医生信息操作--------------------------
    /**
     * 校验用户名称是否唯一
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebDoctor selectDoctorByUserId(Long userId);




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
