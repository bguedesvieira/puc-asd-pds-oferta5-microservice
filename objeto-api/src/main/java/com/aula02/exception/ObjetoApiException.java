package com.aula02.exception;

public class ObjetoApiException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2008598757493758236L;
	
	private String message;
	private Throwable throwable;
	
	public ObjetoApiException(String message) {
		super(message);
		this.message = message;
	}
	
	public ObjetoApiException(String message, Throwable throwable) {
		super(message,throwable);
		this.message = message;
		this.throwable = throwable;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the throwable
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * @param throwable the throwable to set
	 */
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
}
