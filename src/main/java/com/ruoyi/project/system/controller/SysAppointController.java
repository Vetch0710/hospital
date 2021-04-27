package com.ruoyi.project.system.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.SysAppoint;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.domain.vo.QueryVo;
import com.ruoyi.project.system.mapper.SysAppointMapper;
import com.ruoyi.project.system.service.SysAppointService;
import com.ruoyi.project.system.service.WebAppointService;
import com.ruoyi.project.system.service.WebPatientService;
import com.ruoyi.project.system.util.WordUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/sys/appoint")
public class SysAppointController extends BaseController {
    @Autowired
    private SysAppointService sysAppointService;
    @Autowired
    private WebPatientService webPatientService;


    @Autowired
    private TokenService tokenService;


    @ApiOperation("医生获取预约安排")
    @GetMapping("/doctor")
    public TableDataInfo doctor(QueryVo queryVo) {
        try {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            Account user = loginUser.getUser();
            Long userId = user.getUserId();
            startPage();
            List<SysAppoint> sysAppoint = sysAppointService.selectADoctorByUserId(userId);

            return getDataTable(sysAppoint);
        } catch (Exception e) {
            e.printStackTrace();
            return getDataTable(null);

        }
    }

    @ApiOperation("患者获取预约")
    @GetMapping("/patient")
    public TableDataInfo patient(QueryVo queryVo) {
        try {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            Account user = loginUser.getUser();
            Long userId = user.getUserId();
            startPage();
            List<SysAppoint> sysAppoint = sysAppointService.selectAPatientByUserId(userId);
            return getDataTable(sysAppoint);

        } catch (Exception e) {
            e.printStackTrace();
            return getDataTable(null);

        }
    }

    /**
     * 删除员工信息
     */
    @ApiOperation("患者撤销预约")
    @DeleteMapping
    public AjaxResult remove(@Validated @RequestBody SysAppoint sysAppoint) throws Exception {
        return toAjax(sysAppointService.deleteADoctorById(sysAppoint));
    }

    /**
     * 删除员工信息
     */
    @ApiOperation("保存预约")
    @PostMapping("/download")
    public AjaxResult download(@Validated @RequestBody HashMap<String, String> sysAppoint) throws Exception {


        InputStream is = new FileInputStream(new File("src/main/resources/模板.docx"));
        int frontText = Constants.RESOURCE_PREFIX.length();
        String filename = "";


        filename = sysAppoint.get("realName") + ".docx";

        //word文件保存路径
//                String downloadPath = RuoYiConfig.getDownloadStaffPath() + userName + "/" + filename;
        String downloadPath = RuoYiConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(downloadPath);
        if (WordUtil.changWord(is, os, sysAppoint, 200, 200)) {
            return   AjaxResult.success(filename);
        }
        return AjaxResult.error("error");

    }

}