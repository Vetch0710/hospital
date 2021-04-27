package com.ruoyi.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.Query;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.server.SysFile;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.ADoctor;
import com.ruoyi.project.system.domain.vo.QueryVo;
import com.ruoyi.project.system.mapper.WebPatientMapper;
import com.ruoyi.project.system.service.*;
import com.ruoyi.project.system.util.RemoteFile;
import com.ruoyi.project.system.util.WordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api("简历信息管理")
@RestController
@RequestMapping("/sys/ADoctor")
public class SysADoctorController extends BaseController {
    @Autowired
    private WebDoctorService webDoctorService;

    @Autowired
    private WebPatientMapper webPatientMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取员工信息列表
     */
    @ApiOperation("获取医生列表")
    @PreAuthorize("@ss.hasPermi('sys:dpinfo:query')")
    @GetMapping("/list")
    public TableDataInfo list(QueryVo queryVo) {
        startPage();
        List<ADoctor> list = webDoctorService.selectADoctorByQuery(queryVo);
        return getDataTable(list);
    }


    /**
     * 新增用户
     */
    @ApiOperation("添加医生")
    @PreAuthorize("@ss.hasPermi('sys:dpinfo:add')")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ADoctor aDoctor) throws Exception {
        if (webPatientMapper.checkNameUnique(aDoctor.getName())!=0) {
            return AjaxResult.error("新增医生'" + aDoctor.getName() + "'失败，医生已存在");
        }

        return toAjax(webDoctorService.insertADoctor(aDoctor));
    }

    /**
     * 修改用户
     */
    @ApiOperation("修改医生")
    @PreAuthorize("@ss.hasPermi('sys:dpinfo:edit')")
    @Log(title = "简历信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ADoctor aDoctor) throws Exception {

        return toAjax(webDoctorService.updateADoctor(aDoctor));
    }


    /**
     * 删除员工信息
     */
    @ApiOperation("删除医生")
    @PreAuthorize("@ss.hasPermi('sys:dpinfo:remove')")
    @DeleteMapping
    public AjaxResult remove(@Validated @RequestBody  ADoctor aDoctor) throws Exception {
            return toAjax(webDoctorService.deleteADoctorById(aDoctor));
    }

    /**
     * 照片上传
     */
    @ApiOperation("上传证件照")
    @Log(title = "上传证件照", businessType = BusinessType.UPDATE)
    @PostMapping("/upload/{type}")
//    @RequestHeader("accessToken") String accessToken
    public AjaxResult avatar(@RequestParam("file") MultipartFile file, HttpServletRequest request, @PathVariable String type) throws IOException {

//        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        if (!file.isEmpty()) {
//            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            String path = FileUploadUtils.upload(RuoYiConfig.getProfilePath(), file);

            if (StringUtils.isEmpty(path)) {
                return AjaxResult.error("文件服务异常，请联系管理员");
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", path);
            ajax.put("type", type);
            return ajax;
        }

        return AjaxResult.error("上传图片异常，请联系管理员");
//        return AjaxResult.success();
    }


}