package com.litmus7.employee.java;

import java.util.List;

public class EmployeeManagerApp {

	public static void main(String[] args) {
		EmployeeManagerController controller = new EmployeeManagerController();
        String csvFilePath = "C:\\Users\\Public\\Downloads\\LITMUS7\\emp_mgt\\employees.csv"; 
        List<Response> responseList=controller.writeDataToDB(csvFilePath);
        for (Response response : responseList) {
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Message: " + response.getErrorMessage());
            System.out.println("-----------------------------");
        }
        System.out.println("-----------------------------");
	}
}
