package com.litmus7.employeemanager.controller;

import java.util.List;

import com.litmus7.employeemanager.constant.MessageConstant;
import com.litmus7.employeemanager.constant.StatusCode;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.dto.Response;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.service.EmployeeManagerService;
import com.litmus7.employeemanager.util.Validate;


public class EmployeeManagerController {
	private EmployeeManagerService employeeManagerService=new EmployeeManagerService();
	
    public Response<Integer> writeDataToDB(String csvPath) {
    	if(csvPath == null)
    		return new Response<Integer>(StatusCode.FAILURE,MessageConstant.MISSING_FILE_PATH);
    	if(!csvPath.toLowerCase().endsWith(".csv"))
    		return new Response<Integer>(StatusCode.FAILURE,MessageConstant.NOT_A_CSV);
    	int countInsertedEmployees = 0;
		try {
			countInsertedEmployees = employeeManagerService.writeDataToDB(csvPath);
		}
		catch (EmployeeServiceException e) {
			return new Response<Integer>(StatusCode.FAILURE,MessageConstant.SERVICE_ERROR+e.getMessage());
		}
		catch(Exception e){
			return new Response<Integer>(StatusCode.FAILURE,MessageConstant.SYSTEM_ERROR+e.getMessage());
		}
    	if(countInsertedEmployees>0)
    		return (new Response<Integer>(StatusCode.SUCCESS,MessageConstant.SUCCESS,countInsertedEmployees));
    	else
    		return (new Response<>(StatusCode.FAILURE,MessageConstant.FAILED));
    	   	
    }
    
    
    public Response <List<Employee>> getAllEmployees(){
    	List<Employee> employees;
		try {
			employees = employeeManagerService.getAllEmployees();
		} catch (EmployeeServiceException e) {
			return new Response<List<Employee>>(StatusCode.FAILURE,MessageConstant.SERVICE_ERROR+e.getMessage());
		}catch(Exception e){
			return new Response<List<Employee>>(StatusCode.FAILURE,MessageConstant.SYSTEM_ERROR+e.getMessage());
		}
    	if(employees.size()==0) 
    		return new Response<List<Employee>>(StatusCode.FAILURE,MessageConstant.FAILED);
    	else
    		return new Response<List<Employee>>(StatusCode.SUCCESS,MessageConstant.SUCCESS,employees);
    }
    
    public Response <Employee> getEmployeeById(int employeeId){
    	Employee employee = null;
		try {
			employee = employeeManagerService.getEmployeeById(employeeId);
		} catch (EmployeeServiceException e) {
			return new Response<Employee>(StatusCode.FAILURE,MessageConstant.SERVICE_ERROR+e.getMessage());
		}catch(Exception e){
			return new Response<Employee>(StatusCode.FAILURE,MessageConstant.SYSTEM_ERROR+e.getMessage());
		}
    	if(employee==null) 
    		return new Response<Employee>(StatusCode.FAILURE,MessageConstant.FAILED);
    	else
    		return new Response<Employee>(StatusCode.SUCCESS,MessageConstant.SUCCESS,employee);
    }
    
    public Response <String> deleteEmployeeById(int employeeId){
		try {
			employeeManagerService.deleteEmployeeById(employeeId);
		} catch (EmployeeServiceException e) {
			return new Response<String>(StatusCode.FAILURE,MessageConstant.SERVICE_ERROR+e.getMessage());
		}catch(Exception e){
			return new Response<String>(StatusCode.FAILURE,MessageConstant.SYSTEM_ERROR+e.getMessage());
		}
    	return new Response<String>(StatusCode.SUCCESS,MessageConstant.SUCCESS);
    }
    
    public Response <String> updateEmployee(Employee employee){
    	if(employee==null)
    		return new Response<String>(StatusCode.FAILURE,MessageConstant.MISSING_RECORDS);
		try {
			employeeManagerService.updateEmployee(employee);
		} catch (EmployeeServiceException e) {
			return new Response<String>(StatusCode.FAILURE,MessageConstant.SERVICE_ERROR+e.getMessage());
		}catch(Exception e){
			return new Response<String>(StatusCode.FAILURE,MessageConstant.SYSTEM_ERROR+e.getMessage());
		}
    	return new Response<String>(StatusCode.SUCCESS,MessageConstant.SUCCESS);
    }
    
    public Response <String> addEmployee(Employee employee){
    	if(employee==null)
    		return new Response<String>(StatusCode.FAILURE,MessageConstant.MISSING_RECORDS);
		try {
			employeeManagerService.addEmployee(employee);
		} catch (EmployeeServiceException e) {
			return new Response<String>(StatusCode.FAILURE,MessageConstant.SERVICE_ERROR+e.getMessage());
		}catch(Exception e){
			return new Response<String>(StatusCode.FAILURE,MessageConstant.SYSTEM_ERROR+e.getMessage());
		}
    	return new Response<String>(StatusCode.SUCCESS,MessageConstant.SUCCESS);
    }
}
