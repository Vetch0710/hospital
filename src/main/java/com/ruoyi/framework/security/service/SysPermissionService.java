package com.ruoyi.framework.security.service;

import java.util.HashSet;
import java.util.Set;

import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.mapper.SysLoginAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户权限处理
 * 
 * @author ruoyi
 */
@Component
public class SysPermissionService
{


    @Autowired
    private SysLoginAccountMapper sysLoginAccountMapper;

    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(Account user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(
                    sysLoginAccountMapper.selectMenuPermsByRoleId(user.getRoleId())
            );
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(Account user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(
                    sysLoginAccountMapper.selectMenuPermsByRoleId(user.getRoleId())
            );
        }
        return perms;
    }
}
