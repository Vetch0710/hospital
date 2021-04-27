package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysAppoint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单 业务层
 * 
 * @author ruoyi
 */
public interface SysAppointService
{

    public List<SysAppoint> selectADoctorByUserId(Long userId);
    public List<SysAppoint> selectAPatientByUserId(Long userId);
    public int deleteADoctorById(SysAppoint sysAppoint) throws Exception;


}
