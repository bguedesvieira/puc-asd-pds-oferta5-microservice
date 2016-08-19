package br.com.aula02.exception;

public class SocialServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public SocialServiceException(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
