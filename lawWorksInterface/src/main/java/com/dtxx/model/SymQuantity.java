package com.dtxx.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SymQuantity {
	 private String operation;
	    private String contractId;
	    private String quantityId;
	    private String quantityCode;
	    private String quantityName;
	    private String kind;
	    private String unit;
	    private BigDecimal price;
	    @JsonProperty(value = "quantity")
	    private BigDecimal number;
	    private BigDecimal amount;
	    private String remark;
	    private String parentOid;
	    private long levels;
	    private String isLeaf;
	    private long orderNo;
	    private Date planDate;

	    public String getOperation() {
	        return operation;
	    }

	    public void setOperation(String operation) {
	        this.operation = operation;
	    }

	    public String getContractId() {
	        return contractId;
	    }

	    public void setContractId(String contractId) {
	        this.contractId = contractId;
	    }

	    public String getQuantityId() {
	        return quantityId;
	    }

	    public void setQuantityId(String quantityId) {
	        this.quantityId = quantityId;
	    }

	    public String getQuantityCode() {
	        return quantityCode;
	    }

	    public void setQuantityCode(String quantityCode) {
	        this.quantityCode = quantityCode;
	    }

	    public String getQuantityName() {
	        return quantityName;
	    }

	    public void setQuantityName(String quantityName) {
	        this.quantityName = quantityName;
	    }

	    public String getKind() {
	        return kind;
	    }

	    public void setKind(String kind) {
	        this.kind = kind;
	    }

	    public String getUnit() {
	        return unit;
	    }

	    public void setUnit(String unit) {
	        this.unit = unit;
	    }

	    public BigDecimal getPrice() {
	        return price;
	    }

	    public void setPrice(BigDecimal price) {
	        this.price = price;
	    }

	    public BigDecimal getNumber() {
	        return number;
	    }

	    public void setNumber(BigDecimal number) {
	        this.number = number;
	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark;
	    }

	    public String getParentOid() {
	        return parentOid;
	    }

	    public void setParentOid(String parentOid) {
	        this.parentOid = parentOid;
	    }

	    public long getLevels() {
	        return levels;
	    }

	    public void setLevels(long levels) {
	        this.levels = levels;
	    }

	    public String getIsLeaf() {
	        return isLeaf;
	    }

	    public void setIsLeaf(String isLeaf) {
	        this.isLeaf = isLeaf;
	    }

	    public long getOrderNo() {
	        return orderNo;
	    }

	    public void setOrderNo(long orderNo) {
	        this.orderNo = orderNo;
	    }

	    public Date getPlanDate() {
	        return planDate;
	    }

	    public void setPlanDate(Date planDate) {
	        this.planDate = planDate;
	    }
}
