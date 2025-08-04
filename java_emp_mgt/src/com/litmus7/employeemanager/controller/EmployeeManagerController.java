package com.litmus7.employeemanager.controller;

import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.service.EmployeeManagerService;


public class EmployeeManagerController {
	private EmployeeManagerService service=new EmployeeManagerService();
	
    public Response<Integer> writeDataToDB(String csvPath) {
    	if(csvPath == null)
    		return new Response<Integer>(100,"the file is null");
    	if(!csvPath.toLowerCase().endsWith(".csv"))
    		return new Response<Integer>(100,"It is not a CSV file");
    	int countInsertedEmployees=service.writeDataToDB(csvPath);
    	if(countInsertedEmployees>0)
    		return (new Response<Integer>(200,"suceess",countInsertedEmployees));
    	else
    		return (new Response<>(100,"failed"));
    	   	
    }
    public Response <List<Employee>> getAllEmployees(){
    	List<Employee> employees=service.getAllEmployees();
    	if(employees==null) 
    		return new Response<List<Employee>>(100,"Could'nt get the list of employees");
    	else
    		return new Response<List<Employee>>(200,"Success",employees);
    }
}
