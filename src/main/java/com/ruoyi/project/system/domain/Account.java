package com.ruoyi.project.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Vetch
 * @Description
 * @create 2021-04-16-13:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    private Long userId;
    private String userName;
    private String password;
    private String code;
    private String role;
    private Long roleId;
    public boolean isAdmin(){
        return "admin".equals(this.role);
    }
}
