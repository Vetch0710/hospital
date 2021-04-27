package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysAppoint;
import com.ruoyi.project.system.domain.WebPatient;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 * 用户表 数据层
 *
 * @author ruoyi
 */
@Repository
public interface SysAppointMapper {
    /**
     * 校验用户名称是否唯一
     *
     * @param userId 身份证
     * @return 结果
     */
    public int selectPANumByUserId(@Param("userId") Long userId, @Param("banTime") String banTime, @Param("nowTime") String nowTime);


    public List<SysAppoint> selectADoctorByUserId(@Param("doctorId") Long doctorId);

    public List<SysAppoint> selectAPatientByUserId(@Param("patientId") Long patientId);

    public String selectAByMessageId(@Param("appointId") Long appointId);

    public int deleteADoctorById(@Param("appointId") Long appointId);


}
