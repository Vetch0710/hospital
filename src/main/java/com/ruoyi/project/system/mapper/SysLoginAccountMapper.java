package com.ruoyi.project.system.mapper;

import java.util.List;
import java.util.Set;

import com.ruoyi.project.system.domain.Account;
import com.ruoyi.project.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Repository
public interface SysLoginAccountMapper
{

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public Account selectAccountByUserName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public Account selectAccountById(Long userId);

    /**
     * 通过角色ID获取用户权限
     *
     * @param roleId 角色ID
     * @return 用户对象信息
     */
    public Set<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * 通过角色ID获取路由
     *
     * @param roleId 角色ID
     * @return 路由集合
     */
    public List<SysMenu> selectMenuTreeByRoleId(Long roleId);


}
