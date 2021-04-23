package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.vo.QueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Repository
public interface WebAppointMapper
{
    /**
     * 校验用户名称是否唯一
     *
     * @return 结果
     */
    public String selectAppointTime(Long doctorId);

    /**
     * 校验用户名称是否唯一
     *
     * @return 结果
     */
    public List<String> selectAppointedTime(String time);

}
