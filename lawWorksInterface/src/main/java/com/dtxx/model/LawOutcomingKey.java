package com.dtxx.model;

public class LawOutcomingKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_OUTCOMING.OUT_ID
     *
     * @mbg.generated
     */
    private Short outId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_OUTCOMING.TARGET_SYS_CODE
     *
     * @mbg.generated
     */
    private String targetSysCode;
    
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column LAW_OUTCOMING.STATUS
    *
    * @mbg.generated
    */
   private String status;

   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column LAW_OUTCOMING.STATUS
    *
    * @return the value of LAW_OUTCOMING.STATUS
    *
    * @mbg.generated
    */
   public String getStatus() {
       return status;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column LAW_OUTCOMING.STATUS
    *
    * @param status the value for LAW_OUTCOMING.STATUS
    *
    * @mbg.generated
    */
   public void setStatus(String status) {
       this.status = status == null ? null : status.trim();
   }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_OUTCOMING.OUT_ID
     *
     * @return the value of LAW_OUTCOMING.OUT_ID
     *
     * @mbg.generated
     */
    public Short getOutId() {
        return outId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_OUTCOMING.OUT_ID
     *
     * @param outId the value for LAW_OUTCOMING.OUT_ID
     *
     * @mbg.generated
     */
    public void setOutId(Short outId) {
        this.outId = outId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_OUTCOMING.TARGET_SYS_CODE
     *
     * @return the value of LAW_OUTCOMING.TARGET_SYS_CODE
     *
     * @mbg.generated
     */
    public String getTargetSysCode() {
        return targetSysCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_OUTCOMING.TARGET_SYS_CODE
     *
     * @param targetSysCode the value for LAW_OUTCOMING.TARGET_SYS_CODE
     *
     * @mbg.generated
     */
    public void setTargetSysCode(String targetSysCode) {
        this.targetSysCode = targetSysCode == null ? null : targetSysCode.trim();
    }
    public LawOutcomingKey(Short outId, String targetSysCode) {
        this.outId = outId;
        this.targetSysCode = targetSysCode;
    }

    public LawOutcomingKey( String targetSysCode) { 
        this.targetSysCode = targetSysCode;
    }
    public LawOutcomingKey() {
    }
    
}