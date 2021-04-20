package com.ruoyi.project.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 菜单权限表 sys_menu
 * 
 * @author ruoyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebDoctor
{
    private static final long serialVersionUID = 1L;

    /** 账户id */
    private Long userId;

    /** 用户id */
    private Long doctorId;

    /** 用户名 */
    private String name;

    /** 密码 */
    private String password;

    private String avatar;

    private String realName;
    private String position;
    private String department;
    private String specialty;
    private String status;


}
