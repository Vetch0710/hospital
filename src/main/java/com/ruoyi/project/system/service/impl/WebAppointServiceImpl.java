package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.Appoint;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.vo.QueryVo;
import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebAppointMapper;
import com.ruoyi.project.system.mapper.WebDoctorMapper;
import com.ruoyi.project.system.service.WebAppointService;
import com.ruoyi.project.system.service.WebDoctorService;
import com.ruoyi.project.system.util.CutTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class WebAppointServiceImpl implements WebAppointService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private WebAppointMapper webAppointMapper;
    @Autowired
    private WebDoctorMapper webDoctorMapper;


    /**
     * 获取医生可预约时间
     *
     * @return 结果
     */
    public List<String> selectAppointTime(Long doctorId, String appointDay) throws Exception {
//        String nextWeek = LocalDate.now().plus(1, ChronoUnit.DAYS).toString();    //使用变量赋值
        List<String> disableTimes = webAppointMapper.selectAppointedTime(appointDay);
        String appointTime = webAppointMapper.selectAppointTime(doctorId);
        if (appointTime == null || "".equals(appointTime)) {
            throw new Exception("当前医生已没有预约名额");
        }
        return CutTimeUtil.cutTime(appointTime, 30, disableTimes);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertAppoint(String appointTime, Long massageId, Long patientId) throws Exception {
        int row=0;
        String nowTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
        Appoint appoint = new Appoint(nowTime, appointTime, massageId, patientId);
        String day = appointTime.substring(0,appointTime.lastIndexOf("-"));
        String time = appointTime.substring(appointTime.lastIndexOf("-")+1);
        synchronized (this) {
            List<String> times = webAppointMapper.selectAppointedTime(day);
            int remanNum = webDoctorMapper.selectADoctorRemanNum(massageId);
            if (!times.contains(time) && remanNum > 0) {
                int i = webAppointMapper.insertAppoint(appoint);
                if (i == 0) {
                    throw new Exception("预约失败");
                }
               row = webDoctorMapper.updateADoctorRemanNum(remanNum - 1, massageId);
            }
        }
        return row;
    }


}
