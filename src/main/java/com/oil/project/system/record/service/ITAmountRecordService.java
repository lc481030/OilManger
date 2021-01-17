package com.oil.project.system.record.service;

import java.util.List;
import java.util.Map;

import com.oil.project.system.record.domain.Statistics;
import com.oil.project.system.record.domain.StatisticsRecord;
import com.oil.project.system.record.domain.TAmountRecord;

/**
 * 付款记录Service接口
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
public interface ITAmountRecordService 
{
    /**
     * 查询付款记录
     * 
     * @param id 付款记录ID
     * @return 付款记录
     */
    public TAmountRecord selectTAmountRecordById(Long id);

    /**
     * 查询付款记录列表
     * 
     * @param tAmountRecord 付款记录
     * @return 付款记录集合
     */
    public List<TAmountRecord> selectTAmountRecordList(TAmountRecord tAmountRecord,String startDate,String endDate);

    /**
     * 新增付款记录
     * 
     * @param tAmountRecord 付款记录
     * @return 结果
     */
    public int insertTAmountRecord(TAmountRecord tAmountRecord);

    /**
     * 修改付款记录
     * 
     * @param tAmountRecord 付款记录
     * @return 结果
     */
    public int updateTAmountRecord(TAmountRecord tAmountRecord);

    /**
     * 批量删除付款记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTAmountRecordByIds(String ids);

    /**
     * 删除付款记录信息
     * 
     * @param id 付款记录ID
     * @return 结果
     */
    public int deleteTAmountRecordById(Long id);

    List<StatisticsRecord> selectAcountList(StatisticsRecord statisticsRecord);

    Map<String,String> selectStatistics();
}
