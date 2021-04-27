package com.ruoyi.project.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Vetch
 * @Description
 * @create 2021-04-22-10:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysAppoint {
    private static final long serialVersionUID = 1L;


    private Long appointId;
    private Long messageId;
    private String time;
    private String seeTime;
    private String status;

    private String realName;
    private String position;
    private String department;
    private String specialty;

    private String name;


}