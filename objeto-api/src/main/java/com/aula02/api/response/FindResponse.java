package com.aula02.api.response;

import java.util.List;

import com.aula02.model.Objeto;

public class FindResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
