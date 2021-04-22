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
public class ADoctor extends WebDoctor
{
    private static final long serialVersionUID = 1L;


    private String seeTime;
    private Integer maxNum;
    private Integer remanNum;



}