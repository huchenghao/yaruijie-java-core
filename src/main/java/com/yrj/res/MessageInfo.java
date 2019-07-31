package com.yrj.res;

public class MessageInfo {

	/**
	 * 消息代码
	 */
	private String code ;
	
	/**
	 * 提示信息
	 */
	private String msg;
	/**
	 * 请求数据
	 */
	private Object result;

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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	

}
