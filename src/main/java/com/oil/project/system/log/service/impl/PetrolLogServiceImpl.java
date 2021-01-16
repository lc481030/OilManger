package com.oil.project.system.log.service.impl;

import com.oil.common.utils.text.Convert;
import com.oil.project.system.log.domain.PetrolLog;
import com.oil.project.system.log.mapper.PetrolLogMapper;
import com.oil.project.system.log.service.IPetrolLogService;
import com.oil.project.system.station.domain.PetrolStation;
import com.oil.project.system.station.mapper.PetrolStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 加油采购入库记录Service业务层处理
 * 
 * @author lc
 * @date 2020-11-04
 */
@Service
public class PetrolLogServiceImpl implements IPetrolLogService 
{
    @Autowired
    private PetrolLogMapper petrolLogMapper;
    @Autowired
    private PetrolStationMapper petrolStationMapper;

    /**
     * 查询加油采购入库记录
     * 
     * @param id 加油采购入库记录ID
     * @return 加油采购入库记录
     */
    @Override
    public PetrolLog selectPetrolLogById(Long id)
    {
        return petrolLogMapper.selectPetrolLogById(id);
    }

    /**
     * 查询加油采购入库记录列表
     * 
     * @param petrolLog 加油采购入库记录
     * @return 加油采购入库记录
     */
    @Override
    public List<PetrolLog> selectPetrolLogList(PetrolLog petrolLog)
    {
        return petrolLogMapper.selectPetrolLogList(petrolLog);
    }

    /**
     * 新增加油采购入库记录
     * 
     * @param petrolLog 加油采购入库记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPetrolLog(PetrolLog petrolLog)
    {
        PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(petrolLog.getStationId());

        PetrolStation petrolStation = new PetrolStation();
        petrolStation.setId(petrolLog.getStationId());
        petrolStation.setPrice(petrolLog.getPrice());
        if (null !=_petrolStation){
            petrolStation.setRemainl(_petrolStation.getRemainl().add(petrolLog.getLitre()));
        }else{
            petrolStation.setRemainl(petrolLog.getLitre());
        }

        petrolStationMapper.updatePetrolStation(petrolStation);

        return petrolLogMapper.insertPetrolLog(petrolLog);
    }

    /**
     * 修改加油采购入库记录
     * 
     * @param petrolLog 加油采购入库记录
     * @return 结果
     */
    @Override
    public int updatePetrolLog(PetrolLog petrolLog)
    {
        PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(petrolLog.getStationId());
        PetrolLog _petrolLog = petrolLogMapper.selectPetrolLogByLogId(petrolLog.getLogId());
        PetrolStation petrolStation = new PetrolStation();
        petrolStation.setId(petrolLog.getStationId());
        petrolStation.setPrice(petrolLog.getPrice());

        if (null !=_petrolLog){
            petrolStation.setRemainl(_petrolStation.getRemainl().subtract(_petrolLog.getLitre()).add(petrolLog.getLitre()));
            petrolLogMapper.updatePetrolLogByLogId(petrolLog);
        }else{
            petrolLogMapper.insertPetrolLog(petrolLog);
        }
        return petrolStationMapper.updatePetrolStation(petrolStation);
    }
//    @Override
//    public int updatePetrolLog(PetrolLog petrolLog)
//    {
//        PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(petrolLog.getStationId());
//        PetrolLog _petrolLog = petrolLogMapper.selectPetrolLogById(petrolLog.getId());
//        PetrolStation petrolStation = new PetrolStation();
//        petrolStation.setId(petrolLog.getStationId());
//        petrolStation.setPrice(petrolLog.getPrice());
//        petrolStation.setRemainl(_petrolStation.getRemainl().subtract(_petrolLog.getLitre()).add(petrolLog.getLitre()));
//        petrolStationMapper.updatePetrolStation(petrolStation);
//        return petrolLogMapper.updatePetrolLog(petrolLog);
//    }


    /**
     * 删除加油采购入库记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deletePetrolLogByIds(String ids)
    {
        String [] id = ids.split(",");
        for(int i=0;i<id.length;i++){
            PetrolLog _petrolLog = petrolLogMapper.selectPetrolLogById(Long.parseLong(id[i]));
            PetrolStation _petrolStation = petrolStationMapper.selectPetrolStationById(_petrolLog.getStationId());

            PetrolStation petrolStation = new PetrolStation();
            petrolStation.setId(_petrolLog.getStationId());
//            petrolStation.setPrice(_petrolLog.getPrice());
            petrolStation.setRemainl(_petrolStation.getRemainl().subtract(_petrolLog.getLitre()));
            petrolStationMapper.updatePetrolStation(petrolStation);

        }
        return petrolLogMapper.deletePetrolLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除加油采购入库记录信息
     * 
     * @param id 加油采购入库记录ID
     * @return 结果
     */
    @Override
    public int deletePetrolLogById(Long id)
    {
        return petrolLogMapper.deletePetrolLogById(id);
    }
}
