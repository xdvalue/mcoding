package com.mcoding.base.dst.bean.income;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="会员佣金表")
public class DstIncomeMember implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("会员id")
    private Integer memberId;

    @ApiModelProperty("会员名称")
    private String memberName;

    @ApiModelProperty("佣金总收入")
    private Integer incomeTotal;

    @ApiModelProperty("返利总积分")
    private Integer pointTotal;

    @ApiModelProperty("创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(Integer incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public Integer getPointTotal() {
        return pointTotal;
    }

    public void setPointTotal(Integer pointTotal) {
        this.pointTotal = pointTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}