package com.oil.project.system.record.domain;

import com.oil.framework.aspectj.lang.annotation.Excel;
import com.oil.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 付款记录对象 t_amount_record
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
@Data
public class Statistics extends BaseEntity
{
    private String totalTitre;

    private String totalAmount;

    private String petrolLog;
}
