package com.litmus7.employee.java;


public class EmployeeManagerApp {

	public static void main(String[] args) {
		EmployeeManagerController controller = new EmployeeManagerController();
        String csvFilePath = "C:\\Users\\Public\\Downloads\\LITMUS7\\java_emp_mgt\\employees.csv"; 
        Response response=controller.writeDataToDB(csvFilePath);
        System.out.println(response.errorMessage);
	}

}
