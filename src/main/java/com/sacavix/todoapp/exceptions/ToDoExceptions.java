package com.sacavix.todoapp.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ToDoExceptions extends RuntimeException {

	private String message;
	private HttpStatus httpStatus;
	
	
	
	public ToDoExceptions(String message, HttpStatus httpStatus) {
		super();	
		this.message=message;
		this.httpStatus=httpStatus;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public HttpStatus getHttpStatus() {
		return httpStatus;
	}



	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	
	
	
	
	
	
}
