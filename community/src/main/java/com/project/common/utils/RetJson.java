package com.project.common.utils;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

@Data
public class RetJson {
	
	private int code = 1;
	private String message;
	private Object data;
	
	public RetJson(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public RetJson() {}
	
	public RetJson isOk(String message,JSONObject json) {
		return new RetJson(1, message, json);
	}
	
	public RetJson isError(String message,JSONObject json) {
		return new RetJson(-1, message, json);
	}
	
	public RetJson isError(String message) {
		return new RetJson(-1, message, new JSONObject());
	}
	
	public RetJson isOk() {
		return new RetJson(1, "请求成功", new JSONObject());
	}
	
	public RetJson isOk(Object obj) {
		return new RetJson(1, "请求成功", obj);
	}

}
