package com.oil.project.system.petrol.controller;

import com.oil.common.utils.DateUtils;
import com.oil.common.utils.poi.ExcelUtil;
import com.oil.framework.aspectj.lang.annotation.Log;
import com.oil.framework.aspectj.lang.enums.BusinessType;
import com.oil.framework.web.controller.BaseController;
import com.oil.framework.web.domain.AjaxResult;
import com.oil.framework.web.page.TableDataInfo;
import com.oil.project.system.car.domain.Car;
import com.oil.project.system.car.service.ICarService;
import com.oil.project.system.petrol.domain.CarPetrol;
import com.oil.project.system.petrol.service.ICarPetrolService;
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
 * 车辆加油记录Controller
 * 
 * @author lc
 * @date 2020-11-04
 */
@Controller
@RequestMapping("/system/petrol")
public class CarPetrolController extends BaseController
{
    private String prefix = "system/petrol";

    @Autowired
    private ICarPetrolService carPetrolService;

    @Autowired
    private ICarService carService;

    @Autowired
    private IPetrolStationService petrolStationService;



    @RequiresPermissions("system:petrol:view")
    @GetMapping()
    public String petrol(ModelMap mmap)
    {
        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);

        List<PetrolStation> list2 = petrolStationService.selectPetrolStationList(new PetrolStation());
        mmap.put("stationList",list2);

        return prefix + "/petrol";
    }


    /**
     * 查询车辆加油记录列表
     */
    @RequiresPermissions("system:petrol:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CarPetrol carPetrol)
    {
        startPage();
        List<CarPetrol> list = carPetrolService.selectCarPetrolList(carPetrol);
        return getDataTable(list);
    }

    /**
     * 导出车辆加油记录列表
     */
    @RequiresPermissions("system:petrol:export")
    @Log(title = "车辆加油记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CarPetrol carPetrol)
    {
        List<CarPetrol> list = carPetrolService.selectCarPetrolList(carPetrol);
        ExcelUtil<CarPetrol> util = new ExcelUtil<CarPetrol>(CarPetrol.class);
        return util.exportExcel(list, "petrol");
    }

    /**
     * 新增车辆加油记录
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {

        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);

        List<PetrolStation> list2 = petrolStationService.selectPetrolStationList(new PetrolStation());
        mmap.put("stationList",list2);

        return prefix + "/add";
    }

    /**
     * 新增保存车辆加油记录
     */
    @RequiresPermissions("system:petrol:add")
    @Log(title = "车辆加油记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CarPetrol carPetrol)
    {

        // 取身份信息
        User user = getSysUser();

        carPetrol.setUserId(user.getUserId());
        carPetrol.setUserName(user.getUserName());
        carPetrol.setAddtime(DateUtils.getNowDate());
        carPetrol.setIsdelete(0);
        return toAjax(carPetrolService.insertCarPetrol(carPetrol));
    }

    /**
     * 修改车辆加油记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        List<Car> list =  carService.selectCarList(new Car());
        mmap.put("carList",list);

        List<PetrolStation> list2 = petrolStationService.selectPetrolStationList(new PetrolStation());
        mmap.put("stationList",list2);

        CarPetrol carPetrol = carPetrolService.selectCarPetrolById(id);
        mmap.put("carPetrol", carPetrol);
        return prefix + "/edit";
    }

    /**
     * 修改保存车辆加油记录
     */
    @RequiresPermissions("system:petrol:edit")
    @Log(title = "车辆加油记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CarPetrol carPetrol)
    {

        // 取身份信息
        User user = getSysUser();

        carPetrol.setUpateUserid(user.getUserId());
        carPetrol.setUpdateName(user.getUserName());
        carPetrol.setUpdateTime(DateUtils.getNowDate());
        carPetrol.setIsdelete(0);
        return toAjax(carPetrolService.updateCarPetrol(carPetrol));
    }

    /**
     * 删除车辆加油记录
     */
    @RequiresPermissions("system:petrol:remove")
    @Log(title = "车辆加油记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(carPetrolService.deleteCarPetrolByIds(ids));
    }
}
