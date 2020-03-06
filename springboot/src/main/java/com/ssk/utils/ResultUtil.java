package com.ssk.utils;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultUtil implements Serializable {
	private String code = null;
	private String msg= null;
	private Object data = null;
	private String token = null;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ResultUtil() {
		super();
	}

	public ResultUtil(String code) {
		super();
		this.code = code;
	}

	public ResultUtil(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultUtil ok(){
		return new ResultUtil("200");
	}
	
	public static ResultUtil ok(Object list,String token){
		ResultUtil result = new ResultUtil();
		result.setCode("200");
		result.setData(list);
		result.setToken(token);
		return result;
	}
	public static ResultUtil ok(String msg){
		ResultUtil result = new ResultUtil();
		result.setCode("200");
		result.setMsg(msg);
		return result;
	}
	
	public static ResultUtil error(){
		return new ResultUtil("500","失败，请联系超管！");
	}
	public static ResultUtil error(String str){
		return new ResultUtil("500",str);
	}
}
