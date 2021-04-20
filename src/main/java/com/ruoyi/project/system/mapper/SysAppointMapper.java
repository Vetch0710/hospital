package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.WebPatient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Repository
public interface SysAppointMapper
{
    /**
     * 校验用户名称是否唯一
     *
     * @param userId 身份证
     * @return 结果
     */
    public int selectPANumByUserId(@Param("userId")Long userId, @Param("banTime")LocalDate  banTime);





}
