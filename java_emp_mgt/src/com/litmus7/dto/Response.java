package com.litmus7.dto;

public class Response <T>{

	private int statusCode;
	private String message;
	private T data;

	public Response(int statusCode,String message){
		this.statusCode=statusCode;
		this.message=message;
		this.data=null;
		
	}
	
	public Response(int statusCode, String message, T data) {
		this.statusCode=statusCode;
		this.message=message;
		this.data=data;
	}

	public int getStatusCode() {
	    return statusCode;
	}

	public String getMessage() {
	    return message;
	}
	
	public T getData() {
		return data;
	}

}
