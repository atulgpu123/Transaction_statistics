package com.n26.exception;

import org.springframework.http.HttpStatus;

import com.n26.enums.ErrorEnum;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 3291752718319877464L;
	private String errorCode;
	private String errorMessage;
	private int statusCode = HttpStatus.BAD_REQUEST.value();
	
	public ApplicationException(String errorCode, String errorMessage, int statusCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}
	
	public ApplicationException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	

	public ApplicationException(ErrorEnum exception) {
		super();
		this.errorCode = exception.getCode();
		this.errorMessage = exception.getDesc();
		
	}
	public ApplicationException(ErrorEnum exception,int statusCode) {
		super();
		this.errorCode = exception.getCode();
		this.errorMessage = exception.getDesc();
		this.statusCode=statusCode;
		
	}
	
}
