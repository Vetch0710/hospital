package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.vo.QueryVo;

import java.util.List;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface WebAppointService
{
    /**
     * 获取医生可预约时间
     *
     * @return 结果
     */
    public List<String> selectAppointTime(Long doctorId,String appointDay) throws Exception;
    public int insertAppoint(String appointTime,Long massageId,Long patientId) throws Exception;


}
