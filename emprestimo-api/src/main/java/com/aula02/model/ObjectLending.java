package com.aula02.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ObjectLending {
	
	private Long id;
	private String clientRegistrationId;
	private Long objectId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bookLendingDate;
	private String status;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date bookReturnDate;
	
	public ObjectLending() {	}
	
	public ObjectLending(Long id, String clientRegistrationId, Long objectId, Date bookLendingDate, String status,
			Date bookReturnDate) {
		super();
		this.id = id;
		this.clientRegistrationId = clientRegistrationId;
		this.objectId = objectId;
		this.bookLendingDate = bookLendingDate;
		this.status = status;
		this.bookReturnDate = bookReturnDate;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the clientRegistrationId
	 */
	public String getClientRegistrationId() {
		return clientRegistrationId;
	}
	/**
	 * @param clientRegistrationId the clientRegistrationId to set
	 */
	public void setClientRegistrationId(String clientRegistrationId) {
		this.clientRegistrationId = clientRegistrationId;
	}
	/**
	 * @return the objectId
	 */
	public Long getObjectId() {
		return objectId;
	}
	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	/**
	 * @return the bookLendingDate
	 */
	public Date getBookLendingDate() {
		return bookLendingDate;
	}
	/**
	 * @param bookLendingDate the bookLendingDate to set
	 */
	public void setBookLendingDate(Date bookLendingDate) {
		this.bookLendingDate = bookLendingDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the bookReturnDate
	 */
	public Date getBookReturnDate() {
		return bookReturnDate;
	}
	/**
	 * @param bookReturnDate the bookReturnDate to set
	 */
	public void setBookReturnDate(Date bookReturnDate) {
		this.bookReturnDate = bookReturnDate;
	}
}
