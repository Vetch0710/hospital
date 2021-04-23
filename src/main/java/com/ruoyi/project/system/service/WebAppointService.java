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
     * 校验用户名称是否唯一
     *
     * @return 结果
     */
    public List<String> selectAppointTime(Long doctorId);


}
