package com.oil.project.system.record.service.impl;

import java.util.List;

import com.oil.project.system.record.domain.StatisticsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oil.project.system.record.mapper.TAmountRecordMapper;
import com.oil.project.system.record.domain.TAmountRecord;
import com.oil.project.system.record.service.ITAmountRecordService;
import com.oil.common.utils.text.Convert;

/**
 * 付款记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@Service
public class TAmountRecordServiceImpl implements ITAmountRecordService 
{
    @Autowired
    private TAmountRecordMapper tAmountRecordMapper;

    /**
     * 查询付款记录
     * 
     * @param id 付款记录ID
     * @return 付款记录
     */
    @Override
    public TAmountRecord selectTAmountRecordById(Long id)
    {
        return tAmountRecordMapper.selectTAmountRecordById(id);
    }

    /**
     * 查询付款记录列表
     * 
     * @param tAmountRecord 付款记录
     * @return 付款记录
     */
    @Override
    public List<TAmountRecord> selectTAmountRecordList(TAmountRecord tAmountRecord,String startDate,String endDate)
    {
        tAmountRecord.setStartDate(startDate);
        tAmountRecord.setEndDate(endDate);
        return tAmountRecordMapper.selectTAmountRecordList(tAmountRecord);
    }

    /**
     * 新增付款记录
     * 
     * @param tAmountRecord 付款记录
     * @return 结果
     */
    @Override
    public int insertTAmountRecord(TAmountRecord tAmountRecord)
    {
        return tAmountRecordMapper.insertTAmountRecord(tAmountRecord);
    }

    /**
     * 修改付款记录
     * 
     * @param tAmountRecord 付款记录
     * @return 结果
     */
    @Override
    public int updateTAmountRecord(TAmountRecord tAmountRecord)
    {
        return tAmountRecordMapper.updateTAmountRecord(tAmountRecord);
    }

    /**
     * 删除付款记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTAmountRecordByIds(String ids)
    {
        return tAmountRecordMapper.deleteTAmountRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除付款记录信息
     * 
     * @param id 付款记录ID
     * @return 结果
     */
    @Override
    public int deleteTAmountRecordById(Long id)
    {
        return tAmountRecordMapper.deleteTAmountRecordById(id);
    }

    @Override
    public List<StatisticsRecord> selectAcountList(StatisticsRecord statisticsRecord) {
        return tAmountRecordMapper.selectAcountList(statisticsRecord);
    }
}
