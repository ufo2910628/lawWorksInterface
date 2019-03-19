package com.dtxx.model.dto;

import com.dtxx.util.PropsUtil;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 返回值信息
 * 
 * @author Hoperun
 *
 */
public class ReturnInfo {
	
	/**
	 * 返回码
	 */
	@JsonProperty("rtn_code")
	private int rtnCode;

	/**
	 * 说明
	 */
	@JsonProperty("msg")
	private String msg;

	public ReturnInfo() {
	}

	public ReturnInfo(int code) {
		this.rtnCode = code;
		this.msg = PropsUtil.get(Integer.toString(code));
	}

	public int getRtnCode() {
		return rtnCode;
	}

	public ReturnInfo setRtnCode(int rtnCode) {
		this.rtnCode = rtnCode;
		return this;
	}

	/**
	 *
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 *
	 * @param msg
	 */
	public ReturnInfo setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
