package com.ruoyi.project.system.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 sys_menu
 * 
 * @author ruoyi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebPatient
{
    private static final long serialVersionUID = 1L;

    /** 账户id */
    private Long userId;

    /** 用户id */
    private Long patientId;

    /** 身份证 */
    private String idCard;

    /** 用户名 */
    private String name;

    /** 密码 */
    private String password;

    private String status;

    private String banTime;

    public WebPatient(String idCard, String name, String password) {
        this.idCard = idCard;
        this.name = name;
        this.password = password;
    }
}
