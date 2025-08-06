package com.litmus7.employeemanager.ui;


import java.sql.Date;
import java.util.List;

import com.litmus7.employeemanager.constant.StatusCode;
import com.litmus7.employeemanager.controller.EmployeeManagerController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;

public class EmployeeManagerApp {
	public static void main(String[] args) {
		EmployeeManagerController employeeManagerController = new EmployeeManagerController();
		
		//load datas from csv file to db
		
        String csvFilePath = "E:\\Users\\johns\\LITMUS\\LITMUS7\\java_emp_mgt\\employees.csv";
        Response<Integer> response=employeeManagerController.writeDataToDB(csvFilePath);
        if(response.getStatusCode()==StatusCode.SUCCESS) {
        	System.out.println(response.getMessage());
        	System.out.println(response.getData()+ " datas are inserted");
        }else {
        	System.out.println(response.getMessage());
        }
        
        //get all employees from db
        
        Response <List<Employee>> response2= employeeManagerController.getAllEmployees();
        if(response2.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response2.getMessage());
        }else {
        	System.out.println(response2.getMessage());
        	for(Employee employee:response2.getData()) {
            	System.out.println(employee.getEmployeeId()+" "+employee.getFirstName());
            }
        }
        
        //fetching employee by id
        
        int employeeId=102;
        Response<Employee> response3= employeeManagerController.getEmployeeById(employeeId);
        if(response3.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response3.getMessage());
        }else {
        	System.out.println(response3.getData().getFirstName()+" "+response3.getData().getLastName());
        }
        
        //deleting employee by id
        
        employeeId=102;
        Response<String> response4= employeeManagerController.deleteEmployeeById(employeeId);
        if(response4.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response4.getMessage());
        }else {
        	System.out.println(response4.getMessage());
        }
        
        //update an employee
        
        int empId = 101;
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "9876543210";
        String department = "IT";
        double salary = 75000.50;
        Date joinDate = Date.valueOf("2024-08-01"); 

        Employee employee = new Employee(empId, firstName, lastName, email, phone, department, salary, joinDate);
        Response<String> response5= employeeManagerController.updateEmployee(employee);
        if(response5.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response5.getMessage());
        }else {
        	System.out.println(response5.getMessage());
        }
        
        //add an employee
        empId = 107;
        firstName = "Kevin";
        lastName = "Thomas";
        email = "kevin.thomas@example.com";
        phone = "9877682821";
        department = "IT";
        salary = 70000.50;
        joinDate = Date.valueOf("2023-08-14"); 

        employee = new Employee(empId, firstName, lastName, email, phone, department, salary, joinDate);
        Response<String> response6= employeeManagerController.addEmployee(employee);
        if(response6.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response6.getMessage());
        }else {
        	System.out.println(response6.getMessage());
        }
        
	}

}
