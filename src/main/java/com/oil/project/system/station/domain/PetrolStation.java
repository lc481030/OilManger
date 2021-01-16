package com.oil.project.system.station.domain;

import com.oil.framework.aspectj.lang.annotation.Excel;
import com.oil.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 加油站点信息对象 t_petrol_station
 * 
 * @author lc
 * @date 2020-11-04
 */
public class PetrolStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 加油站名称 */
    @Excel(name = "加油站名称")
    private String title;

    /** 最新单价 */
    @Excel(name = "最新单价")
    private BigDecimal price;

    /** 剩余油量 */
    @Excel(name = "剩余油量")
    private BigDecimal remainl;


    private Integer isdelete;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setRemainl(BigDecimal remainl)
    {
        this.remainl = remainl;
    }

    public BigDecimal getRemainl()
    {
        return remainl;
    }
    public void setIsdelete(Integer isdelete)
    {
        this.isdelete = isdelete;
    }

    public Integer getIsdelete()
    {
        return isdelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("price", getPrice())
            .append("remainl", getRemainl())
            .append("isdelete", getIsdelete())
            .toString();
    }
}
