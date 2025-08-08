package com.litmus7.employeemanager.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.employeemanager.constant.Constant;

public class Validate {
	private static final Logger logger = LogManager.getLogger(Validate.class);
	public static boolean validLength(String[] data) {
		if (data.length != 8) {
			logger.warn("Invalid data length: Expected 8 fields, found {}", data.length);
			return false;
		}
		return true;
	}
	public static boolean validEmpID(String data) {
		if (data == null || Integer.parseInt(data) < 0) {
			logger.warn("Invalid employee ID: {}", data);
			return false;
		}
		return true;
	}
	public static boolean validName(String data) {
		if (data == null || data.isEmpty()) {
			logger.warn("Invalid name: {}", data);
			return false;
		}
		return true;
	}
	public static boolean validSalary(String data) {
		if (!data.matches(Constant.SALARY_REGEX)) {
			logger.warn("Invalid salary format: {}", data);
			return false;
		}
		return true;
	}
	public static boolean validEmail(String data) {
		if (!data.matches(Constant.EMAIL_REGEX)) {
			logger.warn("Invalid email format: {}", data);
			return false;
		}
		return true;
	}
	public static boolean validPhone(String data) {
		if (!data.matches(Constant.PHONE_REGEX)) {
			logger.warn("Invalid phone number format: {}", data);
			return false;
		}
		return true;
	}
	public static boolean validDate(String data) {
		if (!data.matches(Constant.DATE_REGEX)) {
			logger.warn("Invalid date format: {}", data);
			return false;
		}
		return true;
	}
	public static boolean validEmployeeDetails(String[] data){
		return validLength(data) & validEmpID(data[0]) & validName(data[1]) & validEmail(data[3]) 
        		& validPhone(data[4]) & validSalary(data[6]) & validDate(data[7]);
	}
}
