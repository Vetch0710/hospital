package com.ruoyi.project.system.domain.vo;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @author Vetch
 * @Description ：员工信息查询条件类
 * @create 2021-04-02-13:13
 */


public class QueryVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 员工姓名 */
    private String userName;
    /** 职工级别 */
    private String level  ;
    /** 所属部门 */
    private String deptName   ;
    /** 所属项目组 */
    private String projectName   ;
    /** 项目经理 */
    private String projectManager  ;
    /** 信息填写状态 */
    private String isFinish  ;

    public QueryVo() {
    }

    public QueryVo(String userName, String level, String deptName, String projectName, String projectManager, String isFinish) {
        this.userName = userName;
        this.level = level;
        this.deptName = deptName;
        this.projectName = projectName;
        this.projectManager = projectManager;
        this.isFinish = isFinish;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    @Override
    public String toString() {
        return "QueryVo{" +
                "userName='" + userName + '\'' +
                ", level='" + level + '\'' +
                ", deptName='" + deptName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectManager='" + projectManager + '\'' +
                ", isFinish='" + isFinish + '\'' +
                '}';
    }
}
