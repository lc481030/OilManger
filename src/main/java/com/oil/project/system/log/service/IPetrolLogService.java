package com.oil.project.system.log.service;

import com.oil.project.system.log.domain.PetrolLog;

import java.util.List;

/**
 * 加油采购入库记录Service接口
 * 
 * @author lc
 * @date 2020-11-04
 */
public interface IPetrolLogService 
{
    /**
     * 查询加油采购入库记录
     * 
     * @param id 加油采购入库记录ID
     * @return 加油采购入库记录
     */
    public PetrolLog selectPetrolLogById(Long id);

    /**
     * 查询加油采购入库记录列表
     * 
     * @param petrolLog 加油采购入库记录
     * @return 加油采购入库记录集合
     */
    public List<PetrolLog> selectPetrolLogList(PetrolLog petrolLog);

    /**
     * 新增加油采购入库记录
     * 
     * @param petrolLog 加油采购入库记录
     * @return 结果
     */
    public int insertPetrolLog(PetrolLog petrolLog);

    /**
     * 修改加油采购入库记录
     * 
     * @param petrolLog 加油采购入库记录
     * @return 结果
     */
    public int updatePetrolLog(PetrolLog petrolLog);

    /**
     * 批量删除加油采购入库记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePetrolLogByIds(String ids);

    /**
     * 删除加油采购入库记录信息
     * 
     * @param id 加油采购入库记录ID
     * @return 结果
     */
    public int deletePetrolLogById(Long id);
}
