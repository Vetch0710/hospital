package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.service.WebAppointService;
import com.ruoyi.project.system.service.WebDoctorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/web/appoint")
public class WebAppointController extends BaseController {
    @Autowired
    private WebAppointService webAppointService;
    @Autowired
    private SysLoginService sysLoginService;


    @Autowired
    private TokenService tokenService;


    @ApiOperation("获取可预约时间")
    @GetMapping("/appointTime/{id}")
    public AjaxResult list(@PathVariable Long id) {

        List<String> result = webAppointService.selectAppointTime(id);
        if (result != null) {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("time", result);
            return ajax;
        }
        return AjaxResult.error("获取时间失败");
    }

//    @ApiOperation("获取员工列表")
//    @GetMapping("/info")
//    public AjaxResult list() {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        Account user = loginUser.getUser();
//        Long userId = user.getUserId();
//        WebDoctor webDoctor = webDoctorService.selectDoctorByUserId(userId);
//        if (webDoctor != null) {
//            AjaxResult ajax = AjaxResult.success();
//            ajax.put("doctor", webDoctor);
//            return ajax;
//        }
//        return AjaxResult.error("获取医生信息失败");
//    }
//
//
//    /**
//     * 修改用户
//     */
//    @ApiOperation("修改用户")
//    @PutMapping
//    public AjaxResult edit(@Validated @RequestBody WebDoctor webDoctor) throws Exception {
////        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
////        Account account = loginUser.getUser();
////        webPatient.setUserId(account.getUserId());
//        return toAjax(webDoctorService.updateDoctor(webDoctor));
//    }

}