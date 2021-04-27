package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.WebPatient;

import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebPatientMapper;
import com.ruoyi.project.system.service.WebPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public WebPatient selectPatientByUserId(Long userId) {
        return webPatientMapper.selectPatientByUserId(userId);
    }

    ;

    /**
     * 校验用户名称是否唯一
     *
     * @param idCard 身份证
     * @return 结果
     */
    public boolean checkPatientUnique(String idCard, String name) {
        return (webPatientMapper.checkIdCardUnique(idCard) + webPatientMapper.checkNameUnique(name)) != 0;
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
        if (patient.getUserId() == null) {
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
        int i = webPatientMapper.checkNameUnique(patient.getName());
        String oldName = webPatientMapper.selectNameByUserId(patient.getUserId());
        if (i != 0 && !patient.getName().equals(oldName)) {
            throw new Exception("更新失败，该用户名已存在");
        }
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
    public List<String> updatePatientStatus(Long userId) throws Exception {
        List<String> result = new ArrayList<>();
        String status, banTime = "";
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
        Map<String, Object> map = webPatientMapper.selectPSBByUserId(userId);
        if ("1".equals(map.get("status").toString())) {
            if (nowTime.compareTo(map.get("banTime").toString()) >= 0) {
                int row = webPatientMapper.updatePatientStatus('0', map.get("banTime").toString(), userId);
                if (row == 0) {
                    throw new Exception("修改状态失败");
                }
                status = "0";
            } else {
                status = "1";
                banTime = map.get("banTime").toString();
            }

        } else {
            int i = sysAppointMapper.selectPANumByUserId(userId, map.get("banTime") == null ? null : map.get("banTime").toString(), nowTime);
            if (i > 3) {
                String banTimes = LocalDateTime.now().plus(1, ChronoUnit.MONTHS).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
                int i1 = webPatientMapper.updatePatientStatus('1', banTimes, userId);
                if (i1 == 0) {
                    throw new Exception("修改状态失败");
                }
                status = "1";
                banTime = banTimes;
            } else {
                status = "0";
            }
        }
        result.add(status);
        result.add(banTime);
        return result;
    }

    ;


}
