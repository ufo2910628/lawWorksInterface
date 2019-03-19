package com.dtxx.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 返回信息（一个参数data）
 * 
 * @author Hoperun
 *
 */
public class Return1Info extends ReturnInfo {

	@JsonProperty("data")
	private Object data;

	public Return1Info() {
		super();
	}

	public Return1Info(int code) {
		super(code);
	}

	public Object getData() {
		return data;
	}

	public Return1Info setData(Object result) {
		this.data = result;
		return this;
	}
	public String toString() {
		return "{\"msg\":\""+this.getMsg()+"\",\"data\":\"\",\"rtnCode\":"+this.getRtnCode()+"}";
	}
}
