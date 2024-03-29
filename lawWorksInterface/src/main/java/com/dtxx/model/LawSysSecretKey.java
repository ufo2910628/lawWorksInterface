package com.dtxx.model;

public class LawSysSecretKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.SECRET_KEY_ID
     *
     * @mbg.generated
     */
    private Long secretKeyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.SYS_CODE
     *
     * @mbg.generated
     */
    private String sysCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.IN_PUBLIC_KEY
     *
     * @mbg.generated
     */
    private String inPublicKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.IN_PRIVATE_KEY
     *
     * @mbg.generated
     */
    private String inPrivateKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.IN_AES_KEY1
     *
     * @mbg.generated
     */
    private String aesKey1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.IN_AES_KEY2
     *
     * @mbg.generated
     */
    private String aesKey2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.OUT_PUBLICKEY
     *
     * @mbg.generated
     */
    private String outPublickey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAW_SYS_SECRET_KEY.ACTIVE_FLAG
     *
     * @mbg.generated
     */
    private String activeFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_SYS_SECRET_KEY.SECRET_KEY_ID
     *
     * @return the value of LAW_SYS_SECRET_KEY.SECRET_KEY_ID
     *
     * @mbg.generated
     */
    public Long getSecretKeyId() {
        return secretKeyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_SYS_SECRET_KEY.SECRET_KEY_ID
     *
     * @param secretKeyId the value for LAW_SYS_SECRET_KEY.SECRET_KEY_ID
     *
     * @mbg.generated
     */
    public void setSecretKeyId(Long secretKeyId) {
        this.secretKeyId = secretKeyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_SYS_SECRET_KEY.SYS_CODE
     *
     * @return the value of LAW_SYS_SECRET_KEY.SYS_CODE
     *
     * @mbg.generated
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_SYS_SECRET_KEY.SYS_CODE
     *
     * @param sysCode the value for LAW_SYS_SECRET_KEY.SYS_CODE
     *
     * @mbg.generated
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_SYS_SECRET_KEY.IN_PUBLIC_KEY
     *
     * @return the value of LAW_SYS_SECRET_KEY.IN_PUBLIC_KEY
     *
     * @mbg.generated
     */
    public String getInPublicKey() {
        return inPublicKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_SYS_SECRET_KEY.IN_PUBLIC_KEY
     *
     * @param inPublicKey the value for LAW_SYS_SECRET_KEY.IN_PUBLIC_KEY
     *
     * @mbg.generated
     */
    public void setInPublicKey(String inPublicKey) {
        this.inPublicKey = inPublicKey == null ? null : inPublicKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_SYS_SECRET_KEY.IN_PRIVATE_KEY
     *
     * @return the value of LAW_SYS_SECRET_KEY.IN_PRIVATE_KEY
     *
     * @mbg.generated
     */
    public String getInPrivateKey() {
        return inPrivateKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_SYS_SECRET_KEY.IN_PRIVATE_KEY
     *
     * @param inPrivateKey the value for LAW_SYS_SECRET_KEY.IN_PRIVATE_KEY
     *
     * @mbg.generated
     */
    public void setInPrivateKey(String inPrivateKey) {
        this.inPrivateKey = inPrivateKey == null ? null : inPrivateKey.trim();
    }


    public String getAesKey1() {
		return aesKey1;
	}

	public void setAesKey1(String aesKey1) {
		this.aesKey1 = aesKey1;
	}

	public String getAesKey2() {
		return aesKey2;
	}

	public void setAesKey2(String aesKey2) {
		this.aesKey2 = aesKey2;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_SYS_SECRET_KEY.OUT_PUBLICKEY
     *
     * @return the value of LAW_SYS_SECRET_KEY.OUT_PUBLICKEY
     *
     * @mbg.generated
     */
    public String getOutPublickey() {
        return outPublickey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_SYS_SECRET_KEY.OUT_PUBLICKEY
     *
     * @param outPublickey the value for LAW_SYS_SECRET_KEY.OUT_PUBLICKEY
     *
     * @mbg.generated
     */
    public void setOutPublickey(String outPublickey) {
        this.outPublickey = outPublickey == null ? null : outPublickey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAW_SYS_SECRET_KEY.ACTIVE_FLAG
     *
     * @return the value of LAW_SYS_SECRET_KEY.ACTIVE_FLAG
     *
     * @mbg.generated
     */
    public String getActiveFlag() {
        return activeFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAW_SYS_SECRET_KEY.ACTIVE_FLAG
     *
     * @param activeFlag the value for LAW_SYS_SECRET_KEY.ACTIVE_FLAG
     *
     * @mbg.generated
     */
    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag == null ? null : activeFlag.trim();
    }
}