package com.ruoyi.project.system.service.impl;


import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.domain.vo.QueryVo;
import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.mapper.WebAppointMapper;
import com.ruoyi.project.system.mapper.WebDoctorMapper;
import com.ruoyi.project.system.service.WebAppointService;
import com.ruoyi.project.system.service.WebDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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





    /**
     * 校验用户名称是否唯一
     *
     * @return 结果
     */
    public List<String> selectAppointTime(Long doctorId) {
        String nextWeek = LocalDate.now().plus(1, ChronoUnit.DAYS).toString();    //使用变量赋值
        List<String> times = webAppointMapper.selectAppointedTime(nextWeek);
        List<String> result = new ArrayList<>();
        String[] appointTime = webAppointMapper.selectAppointTime(doctorId).split("-");
        String startTime = appointTime[0];
        String endTime = appointTime[1];

        return result;
    }





}
