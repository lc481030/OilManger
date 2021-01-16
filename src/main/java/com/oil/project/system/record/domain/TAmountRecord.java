package com.oil.project.system.record.domain;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oil.framework.aspectj.lang.annotation.Excel;
import com.oil.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 付款记录对象 t_amount_record
 * 
 * @author ruoyi
 * @date 2021-01-16
 */
public class TAmountRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 车辆标识 */
    @Excel(name = "车辆标识")
    private Long carId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNumber;

    @Excel(name = "司机姓名")
    private String driver;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 结算日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 录入操作者 */
//    @Excel(name = "录入操作者")
    private Long userId;

    /** 录入者姓名 */
//    @Excel(name = "录入者姓名")
    private String userName;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "录入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** 是否删除 0 否，1是 */
//    @Excel(name = "是否删除 0 否，1是")
    private Integer isdelete;

    /** 修改人 */
//    @Excel(name = "修改人")
    private Long upateUserid;

    /** 修改人姓名 */
//    @Excel(name = "修改人姓名")
    private String updateName;

//    @Excel(name = "更新时间")
    private Date updatetime;

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCarId(Long carId)
    {
        this.carId = carId;
    }

    public Long getCarId()
    {
        return carId;
    }
    public void setCarNumber(String carNumber)
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber()
    {
        return carNumber;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setAddtime(Date addtime)
    {
        this.addtime = addtime;
    }

    public Date getAddtime()
    {
        return addtime;
    }
    public void setIsdelete(Integer isdelete)
    {
        this.isdelete = isdelete;
    }

    public Integer getIsdelete()
    {
        return isdelete;
    }
    public void setUpateUserid(Long upateUserid)
    {
        this.upateUserid = upateUserid;
    }

    public Long getUpateUserid()
    {
        return upateUserid;
    }
    public void setUpdateName(String updateName)
    {
        this.updateName = updateName;
    }

    public String getUpdateName()
    {
        return updateName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carId", getCarId())
            .append("carNumber", getCarNumber())
            .append("amount", getAmount())
            .append("date", getDate())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("addtime", getAddtime())
            .append("isdelete", getIsdelete())
            .append("updatetime", getUpdatetime())
            .append("upateUserid", getUpateUserid())
            .append("updateName", getUpdateName())
            .toString();
    }
}
