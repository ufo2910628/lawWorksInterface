package com.dtxx.model;

import com.fasterxml.jackson.annotation.JsonProperty; 

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SymContractBase {

	 private String operation;
	    private String sysCode;
	    private String contractId;
	    private String contractCode;
	    private String contractName;
	    private String contractAttr;
	    private String contractType;
	    private String contractClass;
	    private String contractKind;
	    private Date signDate;
	    private String contractNature;
	    private String items;
	    private BigDecimal contractWeight;
	    private BigDecimal unitHeat;
	    private BigDecimal unitPrice;
	    private String mainContract;
	    private String contractSubmitter;
	    private String bidCode;
	    private String insideSale;
	    private String purchaseType;
	    private String invoiceType;
	    private String purchaseClass;
	    private String paymentType;
	    private String settleType;
	    private String checkType;
	    private String signLocation;
	    private Date beginDate;
	    private Date endDate;
	    private String signPersonName;
	    private String takeDeptName;
	    private String takePersonName;
	    private String contactWay;
	    private String contractManagePersonName;
	    private String registerPersonId;
	    private String registerPersonName;
	    private Date registerDate;
	    private String contractOutline;
	    private String dueTimeInfo;
	    private String projProjectId;
	    private String tendersType;
	    private String bidPlanId;
	    private String isEquipType;
	    private String purOrderId;
	    private String contractDeadline;
	    private String remark;
	    private String bidProjectId;
	    private String bidProjectCode;
	    private String bidFirstPrice;
	    private String bidLastPrice;
	    private String bidStageCode;
	    private String bidStageName;
	    private String bidWinner;
	    private String deliveryLocation;
	    private Date finishDate;
	    private long contractPeroid;
	    private BigDecimal contractTotal;
	    private String currencyType;
	    private long effectiveNum;
	    private long eachNum;
	    private String solution;
	    private String paymentInfo;
	    private String contractStatus;
	    private String orgLayer;
	    private String manageDeptId;
	    private String amountFlag;
	    private String budgetDeptId;
	    private String budgetType;
	    private String budgetDtl;
	    private String finishFlag;
	    private String orgId;
	    private String deptId;
	    private String createUserId;
	    private Date createTime;
	    private Date updateTime;
	    private String updateUserId;
	//    @SerializedName(value = "relatedParty")
	    private List<SymRelatedParty> symRelatedParty;
	  //  @SerializedName(value = "projectPrice")
	    private List<SymProjectPrice> symProjectPrice;
	  //  @SerializedName(value = "quantity")
	    private List<SymQuantity> symQuantity;
	    private List<String> contractTerm;
	    private List<String> purchsingMaterial;
	 //   @SerializedName(value = "attachmentList")
	    private List<SymAttachment> symAttachments;
	    public String getOperation() {
	        return operation;
	    }

	    public void setOperation(String operation) {
	        this.operation = operation;
	    }

	    public String getSysCode() {
	        return sysCode;
	    }

	    public void setSysCode(String sysCode) {
	        this.sysCode = sysCode;
	    }

	    public String getContractId() {
	        return contractId;
	    }

	    public void setContractId(String contractId) {
	        this.contractId = contractId;
	    }

	    public String getContractCode() {
	        return contractCode;
	    }

	    public void setContractCode(String contractCode) {
	        this.contractCode = contractCode;
	    }

	    public String getContractName() {
	        return contractName;
	    }

	    public void setContractName(String contractName) {
	        this.contractName = contractName;
	    }

	    public String getContractAttr() {
	        return contractAttr;
	    }

	    public void setContractAttr(String contractAttr) {
	        this.contractAttr = contractAttr;
	    }

	    public String getContractType() {
	        return contractType;
	    }

	    public void setContractType(String contractType) {
	        this.contractType = contractType;
	    }

	    public String getContractClass() {
	        return contractClass;
	    }

	    public void setContractClass(String contractClass) {
	        this.contractClass = contractClass;
	    }

	    public List<SymAttachment> getSymAttachments() {
			return symAttachments;
		}

		public void setSymAttachments(List<SymAttachment> symAttachments) {
			this.symAttachments = symAttachments;
		}

		public String getContractKind() {
	        return contractKind;
	    }

	    public void setContractKind(String contractKind) {
	        this.contractKind = contractKind;
	    }

	    public Date getSignDate() {
	        return signDate;
	    }

	    public void setSignDate(Date signDate) {
	        this.signDate = signDate;
	    }

	    public String getContractNature() {
	        return contractNature;
	    }

	    public void setContractNature(String contractNature) {
	        this.contractNature = contractNature;
	    }

	    public String getItems() {
	        return items;
	    }

	    public void setItems(String items) {
	        this.items = items;
	    }

	    public BigDecimal getContractWeight() {
	        return contractWeight;
	    }

	    public void setContractWeight(BigDecimal contractWeight) {
	        this.contractWeight = contractWeight;
	    }

	    public BigDecimal getUnitHeat() {
	        return unitHeat;
	    }

	    public void setUnitHeat(BigDecimal unitHeat) {
	        this.unitHeat = unitHeat;
	    }

	    public BigDecimal getUnitPrice() {
	        return unitPrice;
	    }

	    public void setUnitPrice(BigDecimal unitPrice) {
	        this.unitPrice = unitPrice;
	    }

	    public String getMainContract() {
	        return mainContract;
	    }

	    public void setMainContract(String mainContract) {
	        this.mainContract = mainContract;
	    }

	    public String getContractSubmitter() {
	        return contractSubmitter;
	    }

	    public void setContractSubmitter(String contractSubmitter) {
	        this.contractSubmitter = contractSubmitter;
	    }

	    public String getBidCode() {
	        return bidCode;
	    }

	    public void setBidCode(String bidCode) {
	        this.bidCode = bidCode;
	    }

	    public String getInsideSale() {
	        return insideSale;
	    }

	    public void setInsideSale(String insideSale) {
	        this.insideSale = insideSale;
	    }

	    public String getPurchaseType() {
	        return purchaseType;
	    }

	    public void setPurchaseType(String purchaseType) {
	        this.purchaseType = purchaseType;
	    }

	    public String getInvoiceType() {
	        return invoiceType;
	    }

	    public void setInvoiceType(String invoiceType) {
	        this.invoiceType = invoiceType;
	    }

	    public String getPurchaseClass() {
	        return purchaseClass;
	    }

	    public void setPurchaseClass(String purchaseClass) {
	        this.purchaseClass = purchaseClass;
	    }

	    public String getPaymentType() {
	        return paymentType;
	    }

	    public void setPaymentType(String paymentType) {
	        this.paymentType = paymentType;
	    }

	    public String getSettleType() {
	        return settleType;
	    }

	    public void setSettleType(String settleType) {
	        this.settleType = settleType;
	    }

	    public String getCheckType() {
	        return checkType;
	    }

	    public void setCheckType(String checkType) {
	        this.checkType = checkType;
	    }

	    public String getSignLocation() {
	        return signLocation;
	    }

	    public void setSignLocation(String signLocation) {
	        this.signLocation = signLocation;
	    }

	    public Date getBeginDate() {
	        return beginDate;
	    }

	    public void setBeginDate(Date beginDate) {
	        this.beginDate = beginDate;
	    }

	    public Date getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(Date endDate) {
	        this.endDate = endDate;
	    }

	    public String getSignPersonName() {
	        return signPersonName;
	    }

	    public void setSignPersonName(String signPersonName) {
	        this.signPersonName = signPersonName;
	    }

	    public String getTakeDeptName() {
	        return takeDeptName;
	    }

	    public void setTakeDeptName(String takeDeptName) {
	        this.takeDeptName = takeDeptName;
	    }

	    public String getTakePersonName() {
	        return takePersonName;
	    }

	    public void setTakePersonName(String takePersonName) {
	        this.takePersonName = takePersonName;
	    }

	    public String getContactWay() {
	        return contactWay;
	    }

	    public void setContactWay(String contactWay) {
	        this.contactWay = contactWay;
	    }

	    public String getContractManagePersonName() {
	        return contractManagePersonName;
	    }

	    public void setContractManagePersonName(String contractManagePersonName) {
	        this.contractManagePersonName = contractManagePersonName;
	    }

	    public String getRegisterPersonId() {
	        return registerPersonId;
	    }

	    public void setRegisterPersonId(String registerPersonId) {
	        this.registerPersonId = registerPersonId;
	    }

	    public String getRegisterPersonName() {
	        return registerPersonName;
	    }

	    public void setRegisterPersonName(String registerPersonName) {
	        this.registerPersonName = registerPersonName;
	    }

	    public Date getRegisterDate() {
	        return registerDate;
	    }

	    public void setRegisterDate(Date registerDate) {
	        this.registerDate = registerDate;
	    }

	    public String getContractOutline() {
	        return contractOutline;
	    }

	    public void setContractOutline(String contractOutline) {
	        this.contractOutline = contractOutline;
	    }

	    public String getDueTimeInfo() {
	        return dueTimeInfo;
	    }

	    public void setDueTimeInfo(String dueTimeInfo) {
	        this.dueTimeInfo = dueTimeInfo;
	    }

	    public String getProjProjectId() {
	        return projProjectId;
	    }

	    public void setProjProjectId(String projProjectId) {
	        this.projProjectId = projProjectId;
	    }

	    public String getTendersType() {
	        return tendersType;
	    }

	    public void setTendersType(String tendersType) {
	        this.tendersType = tendersType;
	    }

	    public String getBidPlanId() {
	        return bidPlanId;
	    }

	    public void setBidPlanId(String bidPlanId) {
	        this.bidPlanId = bidPlanId;
	    }

	    public String getIsEquipType() {
	        return isEquipType;
	    }

	    public void setIsEquipType(String isEquipType) {
	        this.isEquipType = isEquipType;
	    }

	    public String getPurOrderId() {
	        return purOrderId;
	    }

	    public void setPurOrderId(String purOrderId) {
	        this.purOrderId = purOrderId;
	    }

	    public String getContractDeadline() {
	        return contractDeadline;
	    }

	    public void setContractDeadline(String contractDeadline) {
	        this.contractDeadline = contractDeadline;
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark;
	    }

	    public String getBidProjectId() {
	        return bidProjectId;
	    }

	    public void setBidProjectId(String bidProjectId) {
	        this.bidProjectId = bidProjectId;
	    }

	    public String getBidProjectCode() {
	        return bidProjectCode;
	    }

	    public void setBidProjectCode(String bidProjectCode) {
	        this.bidProjectCode = bidProjectCode;
	    }

	    public String getBidFirstPrice() {
	        return bidFirstPrice;
	    }

	    public void setBidFirstPrice(String bidFirstPrice) {
	        this.bidFirstPrice = bidFirstPrice;
	    }

	    public String getBidLastPrice() {
	        return bidLastPrice;
	    }

	    public void setBidLastPrice(String bidLastPrice) {
	        this.bidLastPrice = bidLastPrice;
	    }

	    public String getBidStageCode() {
	        return bidStageCode;
	    }

	    public void setBidStageCode(String bidStageCode) {
	        this.bidStageCode = bidStageCode;
	    }

	    public String getBidStageName() {
	        return bidStageName;
	    }

	    public void setBidStageName(String bidStageName) {
	        this.bidStageName = bidStageName;
	    }

	    public String getBidWinner() {
	        return bidWinner;
	    }

	    public void setBidWinner(String bidWinner) {
	        this.bidWinner = bidWinner;
	    }

	    public String getDeliveryLocation() {
	        return deliveryLocation;
	    }

	    public void setDeliveryLocation(String deliveryLocation) {
	        this.deliveryLocation = deliveryLocation;
	    }

	    public Date getFinishDate() {
	        return finishDate;
	    }

	    public void setFinishDate(Date finishDate) {
	        this.finishDate = finishDate;
	    }

	    public long getContractPeroid() {
	        return contractPeroid;
	    }

	    public void setContractPeroid(long contractPeroid) {
	        this.contractPeroid = contractPeroid;
	    }

	    public BigDecimal getContractTotal() {
	        return contractTotal;
	    }

	    public void setContractTotal(BigDecimal contractTotal) {
	        this.contractTotal = contractTotal;
	    }

	    public String getCurrencyType() {
	        return currencyType;
	    }

	    public void setCurrencyType(String currencyType) {
	        this.currencyType = currencyType;
	    }

	    public long getEffectiveNum() {
	        return effectiveNum;
	    }

	    public void setEffectiveNum(long effectiveNum) {
	        this.effectiveNum = effectiveNum;
	    }

	    public long getEachNum() {
	        return eachNum;
	    }

	    public void setEachNum(long eachNum) {
	        this.eachNum = eachNum;
	    }

	    public String getSolution() {
	        return solution;
	    }

	    public void setSolution(String solution) {
	        this.solution = solution;
	    }

	    public String getPaymentInfo() {
	        return paymentInfo;
	    }

	    public void setPaymentInfo(String paymentInfo) {
	        this.paymentInfo = paymentInfo;
	    }

	    public String getContractStatus() {
	        return contractStatus;
	    }

	    public void setContractStatus(String contractStatus) {
	        this.contractStatus = contractStatus;
	    }

	    public String getOrgLayer() {
	        return orgLayer;
	    }

	    public void setOrgLayer(String orgLayer) {
	        this.orgLayer = orgLayer;
	    }

	    public String getManageDeptId() {
	        return manageDeptId;
	    }

	    public void setManageDeptId(String manageDeptId) {
	        this.manageDeptId = manageDeptId;
	    }

	    public String getAmountFlag() {
	        return amountFlag;
	    }

	    public void setAmountFlag(String amountFlag) {
	        this.amountFlag = amountFlag;
	    }

	    public String getBudgetDeptId() {
	        return budgetDeptId;
	    }

	    public void setBudgetDeptId(String budgetDeptId) {
	        this.budgetDeptId = budgetDeptId;
	    }

	    public String getBudgetType() {
	        return budgetType;
	    }

	    public void setBudgetType(String budgetType) {
	        this.budgetType = budgetType;
	    }

	    public String getBudgetDtl() {
	        return budgetDtl;
	    }

	    public void setBudgetDtl(String budgetDtl) {
	        this.budgetDtl = budgetDtl;
	    }

	    public String getFinishFlag() {
	        return finishFlag;
	    }

	    public void setFinishFlag(String finishFlag) {
	        this.finishFlag = finishFlag;
	    }

	    public String getOrgId() {
	        return orgId;
	    }

	    public void setOrgId(String orgId) {
	        this.orgId = orgId;
	    }

	    public String getDeptId() {
	        return deptId;
	    }

	    public void setDeptId(String deptId) {
	        this.deptId = deptId;
	    }

	    public String getCreateUserId() {
	        return createUserId;
	    }

	    public void setCreateUserId(String createUserId) {
	        this.createUserId = createUserId;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

	    public String getUpdateUserId() {
	        return updateUserId;
	    }

	    public void setUpdateUserId(String updateUserId) {
	        this.updateUserId = updateUserId;
	    }

	    public List<SymRelatedParty> getSymRelatedParty() {
	        return symRelatedParty;
	    }

	    public void setSymRelatedParty(List<SymRelatedParty> symRelatedParty) {
	        this.symRelatedParty = symRelatedParty;
	    }

	    public List<SymProjectPrice> getSymProjectPrice() {
	        return symProjectPrice;
	    }

	    public void setSymProjectPrice(List<SymProjectPrice> symProjectPrice) {
	        this.symProjectPrice = symProjectPrice;
	    }

	    public List<SymQuantity> getSymQuantity() {
	        return symQuantity;
	    }

	    public void setSymQuantity(List<SymQuantity> symQuantity) {
	        this.symQuantity = symQuantity;
	    }

	    public List<String> getContractTerm() {
	        return contractTerm;
	    }

	    public void setContractTerm(List<String> contractTerm) {
	        this.contractTerm = contractTerm;
	    }

	    public List<String> getPurchsingMaterial() {
	        return purchsingMaterial;
	    }

	    public void setPurchsingMaterial(List<String> purchsingMaterial) {
	        this.purchsingMaterial = purchsingMaterial;
	    }
	
 
}
