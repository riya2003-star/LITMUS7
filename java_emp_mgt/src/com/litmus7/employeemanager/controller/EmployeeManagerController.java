package com.litmus7.employeemanager.controller;

import java.util.List;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.service.EmployeeManagerService;


public class EmployeeManagerController {
	private EmployeeManagerService service=new EmployeeManagerService();
	
    public Response<Integer> writeDataToDB(String csvPath) {
    	if(csvPath == null)
    		return new Response<Integer>(100,"the file is null");
    	if(!csvPath.toLowerCase().endsWith(".csv"))
    		return new Response<Integer>(100,"It is not a CSV file");
    	int countInsertedEmployees = 0;
		try {
			countInsertedEmployees = service.writeDataToDB(csvPath);
		}
		catch (EmployeeServiceException e) {
			return new Response<Integer>(100,"Service Error "+e.getMessage());
		}
		catch(Exception e){
			return new Response<Integer>(100,"System error "+e.getMessage());
		}
    	if(countInsertedEmployees>0)
    		return (new Response<Integer>(200,"suceess",countInsertedEmployees));
    	else
    		return (new Response<>(100,"employees cannot be inserted"));
    	   	
    }
    public Response <List<Employee>> getAllEmployees(){
    	List<Employee> employees;
		try {
			employees = service.getAllEmployees();
		} catch (EmployeeServiceException e) {
			return new Response<List<Employee>>(100,"Service Error "+e.getMessage());
		}
    	if(employees.size()==0) 
    		return new Response<List<Employee>>(100,"Could'nt get the list of employees");
    	else
    		return new Response<List<Employee>>(200,"Success",employees);
    }
}
