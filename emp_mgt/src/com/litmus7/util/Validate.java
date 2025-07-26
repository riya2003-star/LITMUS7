package com.litmus7.util;

import com.litmus7.constant.Constant;

public class Validate {
	public static boolean validLength(String[] data) {
		if (data.length != 8) {
			return false;
		}
		return false;
	}
	public static boolean validEmpID(String data) {
		if (data == null) {
			return false;
		}
		return true;
	}
	public static boolean validName(String data) {
		if (data == null) {
			return false;
		}
		return true;
	}
	public static boolean validSalary(String data) {
		if (!data.matches(Constant.SALARY_REGEX)) {
			return false;
		}
		return true;
	}
	public static boolean validEmail(String data) {
		if (!data.matches(Constant.EMAIL_REGEX)) {
			return false;
		}
		return true;
	}
	public static boolean validPhone(String data) {
		if (!data.matches(Constant.PHONE_REGEX)) {
			return false;
		}
		return true;
	}
	public static boolean validDate(String data) {
		if (!data.matches(Constant.DATE_REGEX)) {
			return false;
		}
		return true;
	}

}
