package com.litmus7.employee.java;

public class Response {

	private int statusCode;
	private String errorMessage;

	Response(int statusCode,String errorMessage){
		this.statusCode=statusCode;
		this.errorMessage=errorMessage;
	}
	
	public int getStatusCode() {
	    return statusCode;
	}

	public String getErrorMessage() {
	    return errorMessage;
	}

}
