package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.WebDoctor;
import com.ruoyi.project.system.mapper.SysLoginAccountMapper;
import com.ruoyi.project.system.service.ISysLoginAccountService;
import com.ruoyi.project.system.service.WebDoctorService;
import com.ruoyi.project.system.service.impl.SysLoginAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.web.domain.AjaxResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private WebDoctorService webDoctorService;

    @Autowired
    private ISysLoginAccountService iSysLoginAccountService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        Account user = loginUser.getUser();
        // 角色集合
        Set<String> roles = new HashSet<>(Arrays.asList(user.getRole()));

        // 权限集合
        Set<String> permissions = iSysLoginAccountService.selectMenuPermsByRoleId(user.getRoleId());
        AjaxResult ajax = AjaxResult.success();
        if (roles.iterator().next().equals("医生") ){
            WebDoctor webDoctor = webDoctorService.selectDoctorByUserId(user.getUserId());
            ajax.put("avatar", webDoctor.getAvatar());
        }
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        Account user = loginUser.getUser();
        //根据uid获取路由信息
        List<SysMenu> menus = iSysLoginAccountService.selectMenuTreeByRoleId(user.getRoleId());
        //根据路由信息 转换成前端可识别的路由组件信息结构
        return AjaxResult.success(iSysLoginAccountService.buildMenus(menus));
    }
}
