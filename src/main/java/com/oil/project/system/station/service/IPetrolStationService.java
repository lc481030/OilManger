package com.oil.project.system.station.service;

import com.oil.project.system.station.domain.PetrolStation;

import java.util.List;

/**
 * 加油站点信息Service接口
 * 
 * @author lc
 * @date 2020-11-04
 */
public interface IPetrolStationService 
{
    /**
     * 查询加油站点信息
     * 
     * @param id 加油站点信息ID
     * @return 加油站点信息
     */
    public PetrolStation selectPetrolStationById(Long id);

    /**
     * 查询加油站点信息列表
     * 
     * @param petrolStation 加油站点信息
     * @return 加油站点信息集合
     */
    public List<PetrolStation> selectPetrolStationList(PetrolStation petrolStation);

    /**
     * 新增加油站点信息
     * 
     * @param petrolStation 加油站点信息
     * @return 结果
     */
    public int insertPetrolStation(PetrolStation petrolStation);

    /**
     * 修改加油站点信息
     * 
     * @param petrolStation 加油站点信息
     * @return 结果
     */
    public int updatePetrolStation(PetrolStation petrolStation);

    /**
     * 批量删除加油站点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePetrolStationByIds(String ids);

    /**
     * 删除加油站点信息信息
     * 
     * @param id 加油站点信息ID
     * @return 结果
     */
    public int deletePetrolStationById(Long id);
}
