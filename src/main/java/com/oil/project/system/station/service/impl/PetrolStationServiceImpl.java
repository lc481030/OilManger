package com.oil.project.system.station.service.impl;

import com.oil.common.utils.text.Convert;
import com.oil.project.system.station.domain.PetrolStation;
import com.oil.project.system.station.mapper.PetrolStationMapper;
import com.oil.project.system.station.service.IPetrolStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 加油站点信息Service业务层处理
 * 
 * @author lc
 * @date 2020-11-04
 */
@Service
public class PetrolStationServiceImpl implements IPetrolStationService 
{
    @Autowired
    private PetrolStationMapper petrolStationMapper;

    /**
     * 查询加油站点信息
     * 
     * @param id 加油站点信息ID
     * @return 加油站点信息
     */
    @Override
    public PetrolStation selectPetrolStationById(Long id)
    {
        return petrolStationMapper.selectPetrolStationById(id);
    }

    /**
     * 查询加油站点信息列表
     * 
     * @param petrolStation 加油站点信息
     * @return 加油站点信息
     */
    @Override
    public List<PetrolStation> selectPetrolStationList(PetrolStation petrolStation)
    {
        return petrolStationMapper.selectPetrolStationList(petrolStation);
    }

    /**
     * 新增加油站点信息
     * 
     * @param petrolStation 加油站点信息
     * @return 结果
     */
    @Override
    public int insertPetrolStation(PetrolStation petrolStation)
    {
        return petrolStationMapper.insertPetrolStation(petrolStation);
    }

    /**
     * 修改加油站点信息
     * 
     * @param petrolStation 加油站点信息
     * @return 结果
     */
    @Override
    public int updatePetrolStation(PetrolStation petrolStation)
    {
        return petrolStationMapper.updatePetrolStation(petrolStation);
    }

    /**
     * 删除加油站点信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePetrolStationByIds(String ids)
    {
        return petrolStationMapper.deletePetrolStationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加油站点信息信息
     * 
     * @param id 加油站点信息ID
     * @return 结果
     */
    @Override
    public int deletePetrolStationById(Long id)
    {
        return petrolStationMapper.deletePetrolStationById(id);
    }
}
