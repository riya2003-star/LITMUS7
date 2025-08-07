package com.litmus7.employeemanager.ui;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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
        
        System.out.println("------------------------");
        
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
        System.out.println("------------------------");
        
        //fetching employee by id
        
        int employeeId=102;
        Response<Employee> response3= employeeManagerController.getEmployeeById(employeeId);
        if(response3.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response3.getMessage());
        }else {
        	System.out.println(response3.getData().getFirstName()+" "+response3.getData().getLastName());
        }
        System.out.println("------------------------");
        
        //deleting employee by id
        
        employeeId=102;
        Response<String> response4= employeeManagerController.deleteEmployeeById(employeeId);
        if(response4.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response4.getMessage());
        }else {
        	System.out.println(response4.getMessage());
        }
        System.out.println("------------------------");
        
        //update an employee
        
        int empId = 201;
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
        System.out.println("------------------------");
        
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
        System.out.println("------------------------");
        
        //adding employees in batch
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(108, "Ivan", "Petrov", "ivan.p@example.com", "9455667788", "R&D", 70000, Date.valueOf("2023-06-14")));
        employeeList.add(new Employee(109, "Jasmine", "Ahmed", "jasmine.ahmed@example.com", "8788990011", "IT", 47000, Date.valueOf("2023-06-14")));
        Response<Integer> response7= employeeManagerController.addEmployeesInBatch(employeeList);
        if(response7.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response7.getMessage());
        }else {
        	System.out.println(response7.getMessage());
        	System.out.println(response7.getData()+ " datas are inserted");
        }
        System.out.println("------------------------");
        
        //Transfer employees to department
        
        List<Integer> employeeIds = Arrays.asList(101, 102, 103); // Example employee IDs
        String newDepartment = "Marketing";
        Response<String> response8= employeeManagerController.transferEmployeesToDepartment(employeeIds, newDepartment);
        if(response8.getStatusCode()==StatusCode.FAILURE) {
        	System.out.println(response8.getMessage());
        }else {
        	System.out.println(response8.getMessage());
        }
	}

}
