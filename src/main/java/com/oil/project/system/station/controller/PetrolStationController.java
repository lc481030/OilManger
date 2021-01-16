package com.oil.project.system.station.controller;

import com.oil.common.utils.poi.ExcelUtil;
import com.oil.framework.aspectj.lang.annotation.Log;
import com.oil.framework.aspectj.lang.enums.BusinessType;
import com.oil.framework.web.controller.BaseController;
import com.oil.framework.web.domain.AjaxResult;
import com.oil.framework.web.page.TableDataInfo;
import com.oil.project.system.station.domain.PetrolStation;
import com.oil.project.system.station.service.IPetrolStationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 加油站点信息Controller
 * 
 * @author lc
 * @date 2020-11-04
 */
@Controller
@RequestMapping("/system/station")
public class PetrolStationController extends BaseController
{
    private String prefix = "system/station";

    @Autowired
    private IPetrolStationService petrolStationService;

    @RequiresPermissions("system:station:view")
    @GetMapping()
    public String station()
    {
        return prefix + "/station";
    }

    /**
     * 查询加油站点信息列表
     */
    @RequiresPermissions("system:station:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PetrolStation petrolStation)
    {
        startPage();
        List<PetrolStation> list = petrolStationService.selectPetrolStationList(petrolStation);
        return getDataTable(list);
    }

    /**
     * 导出加油站点信息列表
     */
    @RequiresPermissions("system:station:export")
    @Log(title = "加油站点信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PetrolStation petrolStation)
    {
        List<PetrolStation> list = petrolStationService.selectPetrolStationList(petrolStation);
        ExcelUtil<PetrolStation> util = new ExcelUtil<PetrolStation>(PetrolStation.class);
        return util.exportExcel(list, "加油站点");
    }

    /**
     * 新增加油站点信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存加油站点信息
     */
    @RequiresPermissions("system:station:add")
    @Log(title = "加油站点信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PetrolStation petrolStation)
    {
        return toAjax(petrolStationService.insertPetrolStation(petrolStation));
    }

    /**
     * 修改加油站点信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PetrolStation petrolStation = petrolStationService.selectPetrolStationById(id);
        mmap.put("petrolStation", petrolStation);
        return prefix + "/edit";
    }

    /**
     * 修改保存加油站点信息
     */
    @RequiresPermissions("system:station:edit")
    @Log(title = "加油站点信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PetrolStation petrolStation)
    {
        return toAjax(petrolStationService.updatePetrolStation(petrolStation));
    }

    /**
     * 删除加油站点信息
     */
    @RequiresPermissions("system:station:remove")
    @Log(title = "加油站点信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(petrolStationService.deletePetrolStationByIds(ids));
    }
}
