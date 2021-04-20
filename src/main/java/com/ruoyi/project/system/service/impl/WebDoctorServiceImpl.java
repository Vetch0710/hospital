package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebDoctorMapper;
import com.ruoyi.project.system.service.WebDoctorService;
import com.ruoyi.project.system.service.WebPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class WebDoctorServiceImpl implements WebDoctorService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private WebDoctorMapper webDoctorMapper;

    @Autowired
    private SysAppointMapper sysAppointMapper;


    /**
     * 校验用户名称是否唯一
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebDoctor selectDoctorByUserId(Long userId){
      return   webDoctorMapper.selectDoctorByUserId(userId);
    };


    /**
     * 修改用户头像
     *
     * @param userId 用户ID
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateDoctorAvatar(Long userId, String avatar)
    {
        return webDoctorMapper.updateDoctorAvatar(userId, avatar) > 0;
    }


    /**
     * 新增用户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertDoctor(WebDoctor doctor) throws Exception {
        webDoctorMapper.insertDAccount(doctor);
        if (doctor.getUserId()==null){
            throw new Exception("插入失败");
        }
        return webDoctorMapper.insertDoctor(doctor);

    }

    ;

    /**
     * 修改用户信息
     *
     * @param doctor 用户信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateDoctor(WebDoctor doctor) throws Exception {
        if (webDoctorMapper.updateDAccount(doctor)<=0){
            throw new Exception("更新失败");
        }
        return   webDoctorMapper.updateDoctor(doctor);
//        return webDoctorMapper.updateAccount(patient);
    }



}
