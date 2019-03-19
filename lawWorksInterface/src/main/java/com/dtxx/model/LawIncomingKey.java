package com.dtxx.model;

public class LawIncomingKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING.IN_ID
     *
     * @mbg.generated
     */
    private Integer inId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_INCOMING.SOURCE_SYS_CODE
     *
     * @mbg.generated
     */
    private String sourceSysCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING.IN_ID
     *
     * @return the value of LAW_INCOMING.IN_ID
     *
     * @mbg.generated
     */
    public Integer getInId() {
        return inId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING.IN_ID
     *
     * @param inId the value for LAW_INCOMING.IN_ID
     *
     * @mbg.generated
     */
    public void setInId(Integer inId) {
        this.inId = inId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_INCOMING.SOURCE_SYS_CODE
     *
     * @return the value of LAW_INCOMING.SOURCE_SYS_CODE
     *
     * @mbg.generated
     */
    public String getSourceSysCode() {
        return sourceSysCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_INCOMING.SOURCE_SYS_CODE
     *
     * @param sourceSysCode the value for LAW_INCOMING.SOURCE_SYS_CODE
     *
     * @mbg.generated
     */
    public void setSourceSysCode(String sourceSysCode) {
        this.sourceSysCode = sourceSysCode == null ? null : sourceSysCode.trim();
    }
    
    /**
    *
    * This field was generated by MyBatis Generator.
    * This field corresponds to the database column LAW_INCOMING.STATUS
    *
    * @mbg.generated
    */
   private String status;
   
   /**
    * This method was generated by MyBatis Generator.
    * This method returns the value of the database column LAW_INCOMING.STATUS
    *
    * @return the value of LAW_INCOMING.STATUS
    *
    * @mbg.generated
    */
   public String getStatus() {
       return status;
   }

   /**
    * This method was generated by MyBatis Generator.
    * This method sets the value of the database column LAW_INCOMING.STATUS
    *
    * @param status the value for LAW_INCOMING.STATUS
    *
    * @mbg.generated
    */
   public void setStatus(String status) {
       this.status = status == null ? null : status.trim();
   }

    public LawIncomingKey(Integer inId, String sourceSysCode) {
        this.inId = inId;
        this.sourceSysCode = sourceSysCode;
    }

    public LawIncomingKey(String sourceSysCode) {
        this.sourceSysCode = sourceSysCode;
    }
    
   
    public LawIncomingKey() {
    }
}