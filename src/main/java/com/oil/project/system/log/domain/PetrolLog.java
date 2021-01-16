package com.oil.project.system.log.domain;

import com.oil.framework.aspectj.lang.annotation.Excel;
import com.oil.framework.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 加油采购入库记录对象 t_petrol_log
 * 
 * @author lc
 * @date 2020-11-04
 */
@Getter
@Setter
public class PetrolLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    private Long logId;

    /** 加油站ID */
    private Long stationId;

    @Excel(name = "加油站名称")
    private String stationTitle;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 总升数 */
    @Excel(name = "总升数")
    private BigDecimal litre;

    /** 合计总金额 */
    @Excel(name = "合计总金额")
    private BigDecimal amount;

    /** 操作者 */
    private Long userId;

    @Excel(name = "操作者")
    private String userName;

    /** 购买时间 */
    @Excel(name = "采购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyingtime;

    /** 记录时间 */
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 删除标识，0否，1是 */
    private Integer isdelete;

    /** 更新操作者 */
    private Long updateUserid;

    @Excel(name = "更新操作者")
    private String updateName;


}
