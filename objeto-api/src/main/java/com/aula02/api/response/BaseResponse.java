package com.aula02.api.response;

import java.io.Serializable;

public class BaseResponse implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1927328338558262160L;
	
	private Integer code;
	private String message;
	
	public BaseResponse(){
	}

	public BaseResponse(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
