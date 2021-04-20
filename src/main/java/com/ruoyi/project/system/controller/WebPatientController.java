package com.ruoyi.project.system.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.WebPatient;
import com.ruoyi.project.system.domain.vo.QueryVo;
import com.ruoyi.project.system.service.ISysLoginAccountService;
import com.ruoyi.project.system.service.WebPatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/web/patient")
public class WebPatientController extends BaseController {
    @Autowired
    private WebPatientService webPatientService;
    @Autowired
    private SysLoginService sysLoginService;


    @Autowired
    private TokenService tokenService;



    @ApiOperation("获取员工列表")
    @PreAuthorize("@ss.hasPermi('web:patientinfo:list')")
    @GetMapping("/list")
    public AjaxResult list() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Account user = loginUser.getUser();
        Long userId = user.getUserId();
        WebPatient webPatient = webPatientService.selectPatientByUserId(userId);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("patient", webPatient);
        return ajax;
    }



    @ApiOperation("添加用户")
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @RequestMapping("/register")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Map<String, String> params) throws Exception{
        if (sysLoginService.checkCode(params.get("code"), params.get("uuid"))) {
            if (webPatientService.checkPatientUnique(params.get("idCard"),params.get("username"))) {
                return AjaxResult.error("新增用户'" + params.get("username") + "'失败，用户已存在");
            }

//            WebPatient webPatient = new WebPatient(params.get("idCard"),params.get("username"),  SecurityUtils.encryptPassword(params.get("password")));
            WebPatient webPatient = new WebPatient(params.get("idCard"),params.get("username"),  params.get("password"));
            return toAjax(webPatientService.insertPatient(webPatient));
        }

        return AjaxResult.error("验证码为空");
    }


    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @PreAuthorize("@ss.hasPermi('web:patientinfo:edit')")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody WebPatient webPatient)throws Exception {
//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//        Account account = loginUser.getUser();
//        webPatient.setUserId(account.getUserId());
        return toAjax(webPatientService.updatePatient(webPatient));
    }


}
