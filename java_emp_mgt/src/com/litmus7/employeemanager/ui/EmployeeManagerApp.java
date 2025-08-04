package com.litmus7.employeemanager.ui;

import java.util.List;

import com.litmus7.employeemanager.controller.EmployeeManagerController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;

public class EmployeeManagerApp {

	public static void main(String[] args) {
        EmployeeManagerController controller = new EmployeeManagerController();
        String csvFilePath = "E:\\Users\\johns\\LITMUS\\LITMUS7\\java_emp_mgt\\employees.csv";
        Response<Integer> response=controller.writeDataToDB(csvFilePath);
        if(response.getStatusCode()==200) {
        	System.out.println(response.getMessage());
        	System.out.println(response.getData()+ " datas are inserted");
        }
        else {
        	System.out.println(response.getMessage());
        }
        
        Response <List<Employee>> response2= controller.getAllEmployees();
        if(response2.getStatusCode()==100) {
        	System.out.println(response2.getMessage());
        }else {
        	for(Employee employee:response2.getData()) {
            	System.out.println(employee.getEmployeeId()+" "+employee.getFirstName());
            }
        }
	}

}
