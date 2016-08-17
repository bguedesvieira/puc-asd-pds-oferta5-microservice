package com.aula02.api.response;

public class AddResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790442043246407261L;
	private String uri;
	
	public AddResponse(String uri) {
		this.uri = uri;
	}

	public AddResponse() {	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
