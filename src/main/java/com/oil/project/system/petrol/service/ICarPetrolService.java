package com.oil.project.system.petrol.service;

import com.oil.project.system.petrol.domain.CarPetrol;

import java.util.List;

/**
 * 车辆加油记录Service接口
 * 
 * @author lc
 * @date 2020-11-04
 */
public interface ICarPetrolService 
{
    /**
     * 查询车辆加油记录
     * 
     * @param id 车辆加油记录ID
     * @return 车辆加油记录
     */
    public CarPetrol selectCarPetrolById(Long id);

    /**
     * 查询车辆加油记录列表
     * 
     * @param carPetrol 车辆加油记录
     * @return 车辆加油记录集合
     */
    public List<CarPetrol> selectCarPetrolList(CarPetrol carPetrol);

    /**
     * 新增车辆加油记录
     * 
     * @param carPetrol 车辆加油记录
     * @return 结果
     */
    public int insertCarPetrol(CarPetrol carPetrol);

    /**
     * 修改车辆加油记录
     * 
     * @param carPetrol 车辆加油记录
     * @return 结果
     */
    public int updateCarPetrol(CarPetrol carPetrol);

    /**
     * 批量删除车辆加油记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarPetrolByIds(String ids);

    /**
     * 删除车辆加油记录信息
     * 
     * @param id 车辆加油记录ID
     * @return 结果
     */
    public int deleteCarPetrolById(Long id);
}
