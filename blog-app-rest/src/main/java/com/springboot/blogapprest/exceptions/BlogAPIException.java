package com.springboot.blogapprest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BlogAPIException extends RuntimeException {
	
	private HttpStatus httpStatus;
	private String message;
	
	
	public BlogAPIException(HttpStatus httpStatus ,String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}
	
	
	public BlogAPIException(HttpStatus httpStatus, String message,String message1) {
		super(message);
		this.httpStatus = httpStatus;
		this.message = message1;
	}
	
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
	
	
	public String getMessage() {
		return message;
	}
	
	
	
	
	
}
