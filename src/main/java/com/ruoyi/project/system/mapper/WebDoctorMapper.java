package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.domain.vo.QueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Repository
public interface WebDoctorMapper
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
     * @param aDoctor 员工信息
     * @return 结果
     */
    public int insertADoctor(@Param("doctor")ADoctor aDoctor) ;
    /**
     * 新增用户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    public int insertDoctor(@Param("doctor") ADoctor doctor);

    /**
     * 新增账户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    public void insertDAccount(@Param("doctor") ADoctor doctor);
    /**
     * 修改员工信息
     *
     * @param aDoctor 员工信息
     * @return 结果
     */
    public int updateADoctor(@Param("doctor")ADoctor aDoctor) ;

    /**
     * 通过员工编号删除员工
     *
     * @param userId 员工编号
     * @return 结果
     */
    public int deleteADoctorById(Long userId);

    /**
     * 批量删除员工信息
     *
     * @param userId 需要删除的员工编号
     * @return 结果
     */
    public int deleteDoctorById(Long userId);
    /**
     * 批量删除员工信息
     *
     * @param userId 需要删除的员工编号
     * @return 结果
     */
    public int deleteDAccountById(Long userId);


///////////////////////////////////////////////////////////////////////////
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
