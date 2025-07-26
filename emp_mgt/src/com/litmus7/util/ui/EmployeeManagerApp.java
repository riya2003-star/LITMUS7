package com.litmus7.ui;
import java.util.List;

import com.litmus7.controller.EmployeeManagerController;
import com.litmus7.dto.Response;

public class EmployeeManagerApp {

	public static <T> void main(String[] args) {
        EmployeeManagerController controller = new EmployeeManagerController();
        String csvFilePath = "E:\\Users\\johns\\LITMUS\\LITMUS7\\emp_mgt\\employees.csv";
        List<Response<String>> responseList=controller.writeDataToDB(csvFilePath);
        for (Response<String> response : responseList) {
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Message: " + response.getMessage());
            System.out.println("-----------------------------");
        }
        System.out.println("-----------------------------");
	}
	

}
