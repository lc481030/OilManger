package com.oil.project.system.record.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oil.project.system.record.domain.Statistics;
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

    @Override
    public Map<String,String> selectStatistics() {
        List<Statistics> list = tAmountRecordMapper.selectStatistics();
        Map<String,String> map = new HashMap<>();
        for (Statistics statistics:list){
            if (statistics.getPetrolLog().equals("petrol_log")){
                map.put("buyOil",statistics.getTotalTitre());/*累计买油*/
                map.put("buyMoney",statistics.getTotalAmount());/*累计买油的钱*/
            }else if (statistics.getPetrolLog().equals("car_petrol")){
                map.put("carOil",statistics.getTotalTitre());/*累计加了多少油*/
                map.put("carMoney",statistics.getTotalAmount());/*累计加油该收多少钱*/
            }else if (statistics.getPetrolLog().equals("amount_record")){
                map.put("getMoney",statistics.getTotalAmount());/*累计收到多少钱*/
            }
        }
        //买油减去加油 = 剩余油
        Double buyOil = Double.parseDouble(map.get("buyOil"));
        Double carOil = Double.parseDouble(map.get("carOil"));
        Double haveOil = buyOil - carOil;
        map.put("haveOil",haveOil.toString());
        //该收 减去 已收 = 司机欠款(未收)
        Double carMoney = Double.parseDouble(map.get("carMoney"));
        Double getMoney = Double.parseDouble(map.get("getMoney"));
        Double weiMoney = carMoney - getMoney;
        map.put("weiMoney",weiMoney.toString());
        //欠款
        //买油减去卖出去的 = 已经赚的
        Double buyMoney = Double.parseDouble(map.get("buyMoney"));
        Double myMoney = buyMoney - carMoney;
        map.put("myMoney",myMoney.toString());
        return map;
    }
}
