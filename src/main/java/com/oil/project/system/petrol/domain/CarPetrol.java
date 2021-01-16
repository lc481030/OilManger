package com.oil.project.system.petrol.domain;

import com.oil.framework.aspectj.lang.annotation.Excel;
import com.oil.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆加油记录对象 t_car_petrol
 * 
 * @author lc
 * @date 2020-11-04
 */
@Getter
@Setter
public class CarPetrol extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车辆标识 */
    private Long carId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    /** 加油单价 */
    @Excel(name = "加油单价")
    private BigDecimal price;

    /** 加油总升数 */
    @Excel(name = "加油总升数")
    private BigDecimal litre;

    /** 合计金额 */
    @Excel(name = "合计金额")
    private BigDecimal amount;

    /** 加油日期 */
    @Excel(name = "加油日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date petrolTime;

    /** 录入操作者 */
    @Excel(name = "录入操作者")
    private Long userId;

    /** 录入时间 */
    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 是否删除 0 否，1是 */
    private Integer isdelete;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long upateUserid;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driver;

    /** 加油站 */
    private Long stationId;

    /** 加油站名称 */
    @Excel(name = "加油站名称")
    private String stationTitle;


    /** 录入者姓名 */
    @Excel(name = "录入者姓名")
    private String userName;

    /** 修改人姓名 */
    @Excel(name = "修改人姓名")
    private String updateName;

}
