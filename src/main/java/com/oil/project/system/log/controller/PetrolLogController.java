package com.oil.project.system.log.controller;

import com.oil.common.utils.DateUtils;
import com.oil.common.utils.poi.ExcelUtil;
import com.oil.framework.aspectj.lang.annotation.Log;
import com.oil.framework.aspectj.lang.enums.BusinessType;
import com.oil.framework.web.controller.BaseController;
import com.oil.framework.web.domain.AjaxResult;
import com.oil.framework.web.page.TableDataInfo;
import com.oil.project.system.log.domain.PetrolLog;
import com.oil.project.system.log.service.IPetrolLogService;
import com.oil.project.system.station.domain.PetrolStation;
import com.oil.project.system.station.service.IPetrolStationService;
import com.oil.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 加油采购入库记录Controller
 * 
 * @author lc
 * @date 2020-11-04
 */
@Controller
@RequestMapping("/system/log")
public class PetrolLogController extends BaseController
{
    private String prefix = "system/log";

    @Autowired
    private IPetrolLogService petrolLogService;

    @Autowired
    private IPetrolStationService petrolStationService;


    @RequiresPermissions("system:log:view")
    @GetMapping()
    public String log(ModelMap mmap)
    {
        List<PetrolStation> list = petrolStationService.selectPetrolStationList(new PetrolStation());
        mmap.put("stationList",list);

        return prefix + "/log";
    }

    /**
     * 查询加油采购入库记录列表
     */
    @RequiresPermissions("system:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PetrolLog petrolLog)
    {
        startPage();
        List<PetrolLog> list = petrolLogService.selectPetrolLogList(petrolLog);
        return getDataTable(list);
    }

    /**
     * 导出加油采购入库记录列表
     */
    @RequiresPermissions("system:log:export")
    @Log(title = "加油采购入库记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PetrolLog petrolLog)
    {
        List<PetrolLog> list = petrolLogService.selectPetrolLogList(petrolLog);
        ExcelUtil<PetrolLog> util = new ExcelUtil<PetrolLog>(PetrolLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增加油采购入库记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {

        List<PetrolStation> list = petrolStationService.selectPetrolStationList(new PetrolStation());
        mmap.put("stationList",list);

        return prefix + "/add";
    }

    /**
     * 新增保存加油采购入库记录
     */
    @RequiresPermissions("system:log:add")
    @Log(title = "加油采购入库记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PetrolLog petrolLog)
    {
        // 取身份信息
        User user = getSysUser();

        petrolLog.setUserId(user.getUserId());
        petrolLog.setUserName(user.getUserName());
        petrolLog.setAddtime(DateUtils.getNowDate());

        return toAjax(petrolLogService.insertPetrolLog(petrolLog));
    }

    /**
     * 修改加油采购入库记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        List<PetrolStation> list = petrolStationService.selectPetrolStationList(new PetrolStation());
        mmap.put("stationList",list);

        PetrolLog petrolLog = petrolLogService.selectPetrolLogById(id);
        mmap.put("petrolLog", petrolLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存加油采购入库记录
     */
    @RequiresPermissions("system:log:edit")
    @Log(title = "加油采购入库记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PetrolLog petrolLog)
    {
        // 取身份信息
        User user = getSysUser();

        petrolLog.setUpdateUserid(user.getUserId());
        petrolLog.setUpdateName(user.getUserName());
        petrolLog.setUpdateTime(DateUtils.getNowDate());


        return toAjax(petrolLogService.updatePetrolLog(petrolLog));
    }

    /**
     * 删除加油采购入库记录
     */
    @RequiresPermissions("system:log:remove")
    @Log(title = "加油采购入库记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(petrolLogService.deletePetrolLogByIds(ids));
    }
}
