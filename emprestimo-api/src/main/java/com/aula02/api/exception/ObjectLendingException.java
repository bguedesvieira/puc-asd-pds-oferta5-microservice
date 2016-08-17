package com.aula02.api.exception;

public class ObjectLendingException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2086908116964077832L;
	private String message;
	private Throwable throwable;
	
	public ObjectLendingException(String message) {
		this.message = message;
	}
	
	public ObjectLendingException(String message, Throwable throwable) {
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
