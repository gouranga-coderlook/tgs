package com.coderlook.tgs.common;

import java.io.Serializable;

public class ServiceRequestHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object responseObj;
	private String status;
	private String message;

	public ServiceRequestHandler() {
		super();
	}

	public ServiceRequestHandler(Object responseObj, String status, String message) {
		super();
		this.responseObj = responseObj;
		this.status = status;
		this.message = message;
	}

	public Object getResponseObj() {
		return responseObj;
	}

	public void setResponseObj(Object responseObj) {
		this.responseObj = responseObj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
