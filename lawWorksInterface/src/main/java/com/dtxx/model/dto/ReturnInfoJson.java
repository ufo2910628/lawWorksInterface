package com.dtxx.model.dto;

import com.dtxx.util.PropsUtil;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 返回值信息
 * 
 * @author Hoperun
 *
 */
public class ReturnInfoJson {
	
	/**
	 * 返回码
	 */
	private int rtnCode;

	/**
	 * 说明
	 */
	private String msg;
	/**
	 * 数据
	 */
	private String date;
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ReturnInfoJson() {
	}

	public ReturnInfoJson(int code) {
		this.rtnCode = code;
		this.msg = PropsUtil.get(Integer.toString(code));
	}
	public ReturnInfoJson(int code,String date) {
		this.rtnCode = code;
		this.msg = PropsUtil.get(Integer.toString(code));
		this.date=date;
	}
	public int getRtnCode() {
		return rtnCode;
	}

	public ReturnInfoJson setRtnCode(int rtnCode) {
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
	public ReturnInfoJson setMsg(String msg) {
		this.msg = msg;
		return this;
	}
}
