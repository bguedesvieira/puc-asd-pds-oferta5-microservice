package br.com.aula02.exception;

public class ClientServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String message;
	private Throwable throwable;
	
	public ClientServiceException(String message){
		this.message = message;
	}
	
	public ClientServiceException(String message, Throwable t){
		this.message = message;
		this.throwable = t;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	
}
