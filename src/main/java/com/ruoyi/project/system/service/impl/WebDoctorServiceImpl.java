package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.domain.vo.QueryVo;
import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebDoctorMapper;
import com.ruoyi.project.system.service.WebDoctorService;
import com.ruoyi.project.system.service.WebPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
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


//---------------------------------以下是医生预约信息操作-----------------------

    /**
     * 通过查询条件查询用户
     *
     * @param queryVo 查询条件
     * @return 员工信息集合信息
     */
    public List<ADoctor> selectADoctorByQuery(QueryVo queryVo) {
        return webDoctorMapper.selectADoctorByQuery(queryVo);
    }

    ;


    /**
     * 新增员工信息
     *
     * @param aDoctor 员工信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertADoctor(ADoctor aDoctor) throws Exception {
        webDoctorMapper.insertDAccount(aDoctor);
        if (aDoctor.getUserId() == null) {
            throw new Exception("插入失败");
        }
        int i = webDoctorMapper.insertDoctor(aDoctor);
        if (aDoctor.getDoctorId() == null) {
            throw new Exception("插入失败");
        }
        return webDoctorMapper.insertADoctor(aDoctor);
    }

    ;

    /**
     * 修改员工信息
     *
     * @param aDoctor 员工信息
     * @return 结果
     */
    public int updateADoctor(ADoctor aDoctor) {
        return webDoctorMapper.updateADoctor(aDoctor);
    }

    ;

    /**
     * 通过员工编号删除员工
     *
     * @param userId 员工编号
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteADoctorById(ADoctor aDoctor) throws Exception {
       if (webDoctorMapper.deleteADoctorById(aDoctor.getDoctorId())==0){
           throw new Exception("没有该医生");
       }
       if ( webDoctorMapper.deleteDoctorById(aDoctor.getUserId())==0){
           throw new Exception("没有该医生");
       }
       ;
     return    webDoctorMapper.deleteDAccountById(aDoctor.getUserId());
    }



//------------------------------------以下是医生信息操作-----------------------------------

    /**
     * 校验用户名称是否唯一
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebDoctor selectDoctorByUserId(Long userId) {
        return webDoctorMapper.selectDoctorByUserId(userId);
    }

    ;


    /**
     * 修改用户头像
     *
     * @param userId 用户ID
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateDoctorAvatar(Long userId, String avatar) {
        return webDoctorMapper.updateDoctorAvatar(userId, avatar) > 0;
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
        if (webDoctorMapper.updateDAccount(doctor) <= 0) {
            throw new Exception("更新失败");
        }
        return webDoctorMapper.updateDoctor(doctor);
//        return webDoctorMapper.updateAccount(patient);
    }


}
