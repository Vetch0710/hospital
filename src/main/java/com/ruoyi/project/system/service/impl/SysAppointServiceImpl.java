package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.Appoint;
import com.ruoyi.project.system.domain.SysAppoint;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebAppointMapper;
import com.ruoyi.project.system.mapper.WebDoctorMapper;
import com.ruoyi.project.system.mapper.WebPatientMapper;
import com.ruoyi.project.system.service.SysAppointService;
import com.ruoyi.project.system.service.WebAppointService;
import com.ruoyi.project.system.util.CutTimeUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysAppointServiceImpl implements SysAppointService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysAppointMapper sysAppointMapper;
    @Autowired
    private WebDoctorMapper webDoctorMapper;
    @Autowired
    private WebPatientMapper webPatientMapper;


    public List<SysAppoint> selectADoctorByUserId(Long userId) {
        WebDoctor webDoctor = webDoctorMapper.selectDoctorByUserId(userId);
        return sysAppointMapper.selectADoctorByUserId(webDoctor.getDoctorId());
    }


    public List<SysAppoint> selectAPatientByUserId(Long userId) {
        WebPatient webPatient = webPatientMapper.selectPatientByUserId(userId);
        return sysAppointMapper.selectAPatientByUserId(webPatient.getPatientId());
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteADoctorById(SysAppoint sysAppoint) throws Exception {
        String seeTime = sysAppointMapper.selectAByMessageId(sysAppoint.getAppointId());
        if (getMin(seeTime) < 180) {
            throw new Exception("预约时间小于三小时,不可撤销");
        }
        int i = sysAppointMapper.deleteADoctorById(sysAppoint.getAppointId());
        if (i == 0) {
            throw new Exception("撤销预约失败");
        }
        int remanNum = webDoctorMapper.selectADoctorRemanNum(sysAppoint.getMessageId());
        return  webDoctorMapper.updateADoctorRemanNum(remanNum +1, sysAppoint.getMessageId());

    }


    public long getMin(String appointTime) throws Exception {
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date date1 = null, date2 = null;
        date1 = format.parse(appointTime);
        date2 = format.parse(nowTime);
        Calendar ca1 = Calendar.getInstance();
        Calendar ca2 = Calendar.getInstance();
        ca1.setTime(date1);
        ca2.setTime(date2);
        return  (ca1.getTimeInMillis() - ca2.getTimeInMillis()) / (1000 * 60);
    }

}
