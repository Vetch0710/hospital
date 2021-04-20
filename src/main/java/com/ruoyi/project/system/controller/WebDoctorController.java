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
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.service.WebDoctorService;
import com.ruoyi.project.system.service.WebPatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/web/doctor")
public class WebDoctorController extends BaseController {
    @Autowired
    private WebDoctorService webDoctorService;
    @Autowired
    private SysLoginService sysLoginService;


    @Autowired
    private TokenService tokenService;



    @ApiOperation("获取员工列表")
    @PreAuthorize("@ss.hasPermi('web:doctorinfo:list')")
    @GetMapping("/list")
    public AjaxResult list() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Account user = loginUser.getUser();
        Long userId = user.getUserId();
        WebDoctor webDoctor = webDoctorService.selectDoctorByUserId(userId);
        if (webDoctor!=null){
            AjaxResult ajax = AjaxResult.success();
            ajax.put("doctor", webDoctor);
            return ajax;
        }
       return AjaxResult.error("获取医生信息失败");
    }



//    @ApiOperation("添加用户")
////    @PreAuthorize("@ss.hasPermi('system:user:add')")
//    @RequestMapping("/register")
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody Map<String, String> params) throws Exception{
//        if (sysLoginService.checkCode(params.get("code"), params.get("uuid"))) {
//
//
////            WebPatient webPatient = new WebPatient(params.get("idCard"),params.get("username"),  SecurityUtils.encryptPassword(params.get("password")));
//            WebPatient webPatient = new WebPatient(params.get("idCard"),params.get("username"),  params.get("password"));
//            return toAjax(webPatientService.insertPatient(webPatient));
//        }
//
//        return AjaxResult.error("验证码为空");
//    }


    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @PreAuthorize("@ss.hasPermi('web:doctorinfo:edit')")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody WebDoctor webDoctor)throws Exception {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        Account account = loginUser.getUser();
//        webPatient.setUserId(account.getUserId());
        return toAjax(webDoctorService.updateDoctor(webDoctor));
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            if (webDoctorService.updateDoctorAvatar(loginUser.getUser().getUserId(), avatar))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
//                // 更新缓存用户头像
//                loginUser.getUser().setAvatar(avatar);
//                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
}
