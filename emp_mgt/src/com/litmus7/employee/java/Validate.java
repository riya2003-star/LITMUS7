package com.litmus7.employee.java;

public class Validate {

	public static Response isValidRecord(String[] data) {
		Response res;
		if(data.length!=8) {
			res=new Response(105,"Warning incomplete data");
			return res;
		}
		if(data[0]==null) {
			res=new Response(106,"Warning employee ID is not mensioned");
			return res;
		}
		if(data[1]==null) {
			res= new Response(107,"Warning name of employee is not mensioned");
			return res;
		}
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		if(!data[3].matches(emailRegex)) {
			res= new Response(108,"Warning error in email");
			return res;
		}
		String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		if(!data[7].matches(dateRegex)) {
			res= new Response(109,"Warning error in date");
			return res;
		}
		else {
			res=new Response(200,"valid data");
			return res;
		}
	}
}
