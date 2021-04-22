package com.ruoyi.project.system.domain.vo;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Vetch
 * @Description ：员工信息查询条件类
 * @create 2021-04-02-13:13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String realName;
    private Long doctorId  ;

}
