package com.litmus7.employee.java;

public class Validate {

	public static boolean isValidRecord(String[] data) {
		if(data.length!=8) return false;
		if(data[0]==null) return false;
		if(data[1]==null) return false;
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		if(!data[4].matches(emailRegex)) return false;
		String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
		if(!data[8].matches(dateRegex)) return false;
		else return true;
	}
}
