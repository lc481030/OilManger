package com.oil.project.system.petrol.service.impl;

import com.oil.common.utils.text.Convert;
import com.oil.project.system.petrol.domain.CarPetrol;
import com.oil.project.system.petrol.mapper.CarPetrolMapper;
import com.oil.project.system.petrol.service.ICarPetrolService;
import com.oil.project.system.station.domain.PetrolStation;
import com.oil.project.system.station.mapper.PetrolStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆加油记录Service业务层处理
 * 
 * @author lc
 * @date 2020-11-04
 */
@Service
public class CarPetrolServiceImpl implements ICarPetrolService 
{
    @Autowired
    private CarPetrolMapper carPetrolMapper;

    @Autowired
    private PetrolStationMapper petrolStationMapper;

    /**
     * 查询车辆加油记录
     * 
     * @param id 车辆加油记录ID
     * @return 车辆加油记录
     */
    @Override
    public CarPetrol selectCarPetrolById(Long id)
    {
        return carPetrolMapper.selectCarPetrolById(id);
    }

    /**
     * 查询车辆加油记录列表
     * 
     * @param carPetrol 车辆加油记录
     * @return 车辆加油记录
     */
    @Override
    public List<CarPetrol> selectCarPetrolList(CarPetrol carPetrol)
    {
        return carPetrolMapper.selectCarPetrolList(carPetrol);
    }

    /**
     * 新增车辆加油记录
     * 
     * @param carPetrol 车辆加油记录
     * @return 结果
     */
    @Override
    public int insertCarPetrol(CarPetrol carPetrol)
    {

        //更新站点油量剩余库存
        PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(carPetrol.getStationId());

        PetrolStation petrolStation = new PetrolStation();
        petrolStation.setId(carPetrol.getStationId());
        petrolStation.setRemainl(_petrolStation.getRemainl().subtract(carPetrol.getLitre()));
        petrolStationMapper.updatePetrolStation(petrolStation);

        return carPetrolMapper.insertCarPetrol(carPetrol);
    }

    /**
     * 修改车辆加油记录
     * 
     * @param carPetrol 车辆加油记录
     * @return 结果
     */
    @Override
    public int updateCarPetrol(CarPetrol carPetrol)
    {

        PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(carPetrol.getStationId());
        CarPetrol _carPetrol = carPetrolMapper.selectCarPetrolById(carPetrol.getId());

        PetrolStation petrolStation = new PetrolStation();
        petrolStation.setId(carPetrol.getStationId());
        petrolStation.setRemainl(_petrolStation.getRemainl().add(_carPetrol.getLitre()).subtract(carPetrol.getLitre()));
        petrolStationMapper.updatePetrolStation(petrolStation);

        return carPetrolMapper.updateCarPetrol(carPetrol);
    }

    /**
     * 删除车辆加油记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarPetrolByIds(String ids)
    {

        String [] id = ids.split(",");
        for(int i=0;i<id.length;i++){

            CarPetrol _carPetrol = carPetrolMapper.selectCarPetrolById(Long.parseLong(id[i]));
            PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(_carPetrol.getStationId());

            PetrolStation petrolStation = new PetrolStation();
            petrolStation.setId(_carPetrol.getStationId());
            petrolStation.setRemainl(_petrolStation.getRemainl().add(_carPetrol.getLitre()));
            petrolStationMapper.updatePetrolStation(petrolStation);

        }

        return carPetrolMapper.deleteCarPetrolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除车辆加油记录信息
     * 
     * @param id 车辆加油记录ID
     * @return 结果
     */
    @Override
    public int deleteCarPetrolById(Long id)
    {
        return carPetrolMapper.deleteCarPetrolById(id);
    }
}
