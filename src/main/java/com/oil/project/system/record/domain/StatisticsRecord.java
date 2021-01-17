package com.oil.project.system.record.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oil.framework.aspectj.lang.annotation.Excel;
import com.oil.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 付款记录对象 t_amount_record
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@Data
public class StatisticsRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;


    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    @Excel(name = "司机姓名")
    private String driver;

    /** 金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    /** 金额 */
    @Excel(name = "实付")
    private BigDecimal paid;

    /** 金额 */
    @Excel(name = "欠款")
    private BigDecimal owe;
}
