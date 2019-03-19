package com.dtxx.model;

import java.util.Date;

public class LawIncomingRetList {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.IN_ID
     *
     * @mbg.generated
     */
    private Short inId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.TARGET_SYS_CODE
     *
     * @mbg.generated
     */
    private String targetSysCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.INTF_CODE
     *
     * @mbg.generated
     */
    private String intfCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.STATUS
     *
     * @mbg.generated
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.ERROR_MSG
     *
     * @mbg.generated
     */
    private String errorMsg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.CREATION_DATE
     *
     * @mbg.generated
     */
    private Date creationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING_RET_LIST.LAST_UPDATE_DATE
     *
     * @mbg.generated
     */
    private Date lastUpdateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.IN_ID
     *
     * @return the value of LAW_INCOMING_RET_LIST.IN_ID
     *
     * @mbg.generated
     */
    public Short getInId() {
        return inId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.IN_ID
     *
     * @param inId the value for LAW_INCOMING_RET_LIST.IN_ID
     *
     * @mbg.generated
     */
    public void setInId(Short inId) {
        this.inId = inId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.TARGET_SYS_CODE
     *
     * @return the value of LAW_INCOMING_RET_LIST.TARGET_SYS_CODE
     *
     * @mbg.generated
     */
    public String getTargetSysCode() {
        return targetSysCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.TARGET_SYS_CODE
     *
     * @param targetSysCode the value for LAW_INCOMING_RET_LIST.TARGET_SYS_CODE
     *
     * @mbg.generated
     */
    public void setTargetSysCode(String targetSysCode) {
        this.targetSysCode = targetSysCode == null ? null : targetSysCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.INTF_CODE
     *
     * @return the value of LAW_INCOMING_RET_LIST.INTF_CODE
     *
     * @mbg.generated
     */
    public String getIntfCode() {
        return intfCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.INTF_CODE
     *
     * @param intfCode the value for LAW_INCOMING_RET_LIST.INTF_CODE
     *
     * @mbg.generated
     */
    public void setIntfCode(String intfCode) {
        this.intfCode = intfCode == null ? null : intfCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.STATUS
     *
     * @return the value of LAW_INCOMING_RET_LIST.STATUS
     *
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.STATUS
     *
     * @param status the value for LAW_INCOMING_RET_LIST.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.ERROR_MSG
     *
     * @return the value of LAW_INCOMING_RET_LIST.ERROR_MSG
     *
     * @mbg.generated
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.ERROR_MSG
     *
     * @param errorMsg the value for LAW_INCOMING_RET_LIST.ERROR_MSG
     *
     * @mbg.generated
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.CREATION_DATE
     *
     * @return the value of LAW_INCOMING_RET_LIST.CREATION_DATE
     *
     * @mbg.generated
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.CREATION_DATE
     *
     * @param creationDate the value for LAW_INCOMING_RET_LIST.CREATION_DATE
     *
     * @mbg.generated
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING_RET_LIST.LAST_UPDATE_DATE
     *
     * @return the value of LAW_INCOMING_RET_LIST.LAST_UPDATE_DATE
     *
     * @mbg.generated
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING_RET_LIST.LAST_UPDATE_DATE
     *
     * @param lastUpdateDate the value for LAW_INCOMING_RET_LIST.LAST_UPDATE_DATE
     *
     * @mbg.generated
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}