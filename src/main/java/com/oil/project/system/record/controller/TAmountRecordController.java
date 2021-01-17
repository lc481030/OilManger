package com.oil.project.system.record.controller;

import java.util.Date;
import java.util.List;

import com.oil.common.utils.DateUtils;
import com.oil.project.system.car.domain.Car;
import com.oil.project.system.car.service.ICarService;
import com.oil.project.system.record.domain.StatisticsRecord;
import com.oil.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.oil.framework.aspectj.lang.annotation.Log;
import com.oil.framework.aspectj.lang.enums.BusinessType;
import com.oil.project.system.record.domain.TAmountRecord;
import com.oil.project.system.record.service.ITAmountRecordService;
import com.oil.framework.web.controller.BaseController;
import com.oil.framework.web.domain.AjaxResult;
import com.oil.common.utils.poi.ExcelUtil;
import com.oil.framework.web.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 付款记录Controller
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@Controller
@RequestMapping("/system/record")
public class TAmountRecordController extends BaseController
{
    private String prefix = "system/record";

    @Autowired
    private ITAmountRecordService tAmountRecordService;

    @Autowired
    private ICarService carService;

    @RequiresPermissions("system:record:view")
    @GetMapping()
    public String record(ModelMap mmap)
    {
        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);
        return prefix + "/index";
    }


    @RequiresPermissions("system:record:view")
    @GetMapping("/acount")
    public String acount(ModelMap mmap)
    {
        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);
        return prefix + "/acount";
    }

    /**
     * 查询付款记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/acountlist")
    @ResponseBody
    public TableDataInfo acountList(StatisticsRecord statisticsRecord , HttpServletRequest request)
    {
        startPage();
        List<StatisticsRecord> list = tAmountRecordService.selectAcountList(statisticsRecord);
        return getDataTable(list);
    }


    /**
     * 导出付款记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "结算统计", businessType = BusinessType.EXPORT)
    @PostMapping("/statisticsExport")
    @ResponseBody
    public AjaxResult statisticsExport(StatisticsRecord statisticsRecord, HttpServletRequest request)
    {
        List<StatisticsRecord> list = tAmountRecordService.selectAcountList(statisticsRecord);
        ExcelUtil<StatisticsRecord> util = new ExcelUtil<StatisticsRecord>(StatisticsRecord.class);
        return util.exportExcel(list, "结算统计");
    }

    /**
     * 查询付款记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TAmountRecord tAmountRecord ,HttpServletRequest request)
    {
        String startDate = request.getParameter("beginRecordTime");
        String endDate = request.getParameter("endRecordTime");
        startPage();
        List<TAmountRecord> list = tAmountRecordService.selectTAmountRecordList(tAmountRecord,startDate,endDate);
        return getDataTable(list);
    }


    /**
     * 导出付款记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "付款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TAmountRecord tAmountRecord, HttpServletRequest request)
    {
        String startDate = request.getParameter("beginRecordTime");
        String endDate = request.getParameter("endRecordTime");
        List<TAmountRecord> list = tAmountRecordService.selectTAmountRecordList(tAmountRecord,startDate,endDate);
        ExcelUtil<TAmountRecord> util = new ExcelUtil<TAmountRecord>(TAmountRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增付款记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);
        return prefix + "/add";
    }

    /**
     * 新增保存付款记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "付款记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TAmountRecord tAmountRecord,HttpServletRequest request)
    {
        tAmountRecord.setIsdelete(0);
        tAmountRecord.setAddtime(new Date());
        tAmountRecord.setDate(new Date());
        User user = getSysUser();
        tAmountRecord.setUserId(user.getUserId());
        tAmountRecord.setUserName(user.getUserName());
        String driver = request.getParameter("driver");
        tAmountRecord.setDriver(driver);
        return toAjax(tAmountRecordService.insertTAmountRecord(tAmountRecord));
    }

    /**
     * 修改付款记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);
        TAmountRecord tAmountRecord = tAmountRecordService.selectTAmountRecordById(id);
        mmap.put("tAmountRecord", tAmountRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存付款记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "付款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TAmountRecord tAmountRecord,HttpServletRequest request)
    {
        // 取身份信息
        User user = getSysUser();

        tAmountRecord.setUpateUserid(user.getUserId());
        tAmountRecord.setUpdateName(user.getUserName());
        tAmountRecord.setUpdateTime(DateUtils.getNowDate());
        tAmountRecord.setIsdelete(0);
        String driver = request.getParameter("driver");
        tAmountRecord.setDriver(driver);
        return toAjax(tAmountRecordService.updateTAmountRecord(tAmountRecord));
    }

    /**
     * 删除付款记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "付款记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tAmountRecordService.deleteTAmountRecordByIds(ids));
    }
}
