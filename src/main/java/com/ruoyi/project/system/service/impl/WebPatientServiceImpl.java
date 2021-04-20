package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.WebPatient;

import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebPatientMapper;
import com.ruoyi.project.system.service.WebPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class WebPatientServiceImpl implements WebPatientService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private WebPatientMapper webPatientMapper;

    @Autowired
    private SysAppointMapper sysAppointMapper;


    /**
     * 校验用户名称是否唯一
     *
     * @param userId 用户id
     * @return 结果
     */
    public WebPatient selectPatientByUserId(Long userId){
      return   webPatientMapper.selectPatientByUserId(userId);
    };

    /**
     * 校验用户名称是否唯一
     *
     * @param idCard 身份证
     * @return 结果
     */
    public boolean checkPatientUnique(String idCard,String name) {
        return (webPatientMapper.checkIdCardUnique(idCard)+webPatientMapper.checkNameUnique(name))!=0;
    }

    ;


    /**
     * 新增用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertPatient(WebPatient patient) throws Exception {
        webPatientMapper.insertAccount(patient);
        if (patient.getUserId()==null){
            throw new Exception("插入失败");
        }
        return webPatientMapper.insertPatient(patient);

    }

    ;

    /**
     * 修改用户信息
     *
     * @param patient 用户信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int updatePatient(WebPatient patient) throws Exception {
//        if (webPatientMapper.updateAccount(patient)<=0){
//            throw new Exception("更新失败");
//        }
//        return   webPatientMapper.updatePatient(patient);
        return webPatientMapper.updateAccount(patient);
    }

    /**
     * 修改用户状态
     *
     * @param userId 账户id
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePatientStatus(Long userId) {
        Map<String, Object> map = webPatientMapper.selectPSBByUserId(userId);
        if ((char) map.get("status")=='1'){
            if (  LocalDate.now().compareTo((LocalDate)map.get("banTime"))>=0){
                return webPatientMapper.updatePatientStatus('0',(LocalDate)map.get("banTime") ,userId);
            }
        }else {
            int i = sysAppointMapper.selectPANumByUserId(userId,(LocalDate)map.get("banTime"));
            if (i > 3) {
                char status = '1';
                LocalDate banTime = LocalDate.now().plus(1, ChronoUnit.MONTHS);
                return webPatientMapper.updatePatientStatus(status,banTime,userId);
            }
        }

        return 1;
    }

    ;


}
