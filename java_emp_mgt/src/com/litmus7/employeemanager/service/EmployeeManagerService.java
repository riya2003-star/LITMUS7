package com.litmus7.employeemanager.service;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.dao.EmployeeDao;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeExistException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.util.ReadCsv;
import com.litmus7.employeemanager.util.Validate;


public class EmployeeManagerService {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public List<Employee> loadCSV(String filePath) throws EmployeeServiceException {
    	List<Employee> employees = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String[]> lines;
		try {
			lines = ReadCsv.readCsv(filePath);
		} catch (FileNotFoundException e) {
			throw new EmployeeServiceException("Csv file not found",e);
		}
        for(String[] data:lines) {
        	if (!Validate.validEmployeeDetails(data)) {  			
        		continue;
            }try {
                int empId = Integer.parseInt(data[0].trim());
                String firstName = data[1].trim();
                String lastName = data[2].trim();
                String email = data[3].trim();
                String phone = data[4].trim();
                String department = data[5].trim();
                double salary = Double.parseDouble(data[6].trim());
                java.util.Date utilDate = formatter.parse(data[7].trim());
                Date sqlDate = new Date(utilDate.getTime());

                Employee emp = new Employee(empId, firstName, lastName, email, phone, department, salary, sqlDate);
                employees.add(emp);
            } catch (Exception e) {
            	throw new EmployeeServiceException("Service layer failed due to invalid employee details");
            }
        }
        return employees;
    }
	
	
	public int writeDataToDB(String csvPath) throws EmployeeServiceException  {
		int countInsertedEmployees=0;
		List<Employee> employees = loadCSV(csvPath);
		for (Employee employee : employees) {
			try {
				if(employeeDao.employeeExists(employee.getEmployeeId())) {
					continue;
				}
	        	employeeDao.saveEmployee(employee);
	        	countInsertedEmployees++;
			}
			catch(EmployeeDaoException e) {
				throw new EmployeeServiceException("Service layer failed to save data to db",e);
			}
			
        }
		return countInsertedEmployees;
	}
	
	public List<Employee> getAllEmployees() throws EmployeeServiceException{
		try {
			return employeeDao.getAllEmployees();
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException("Service layer failed to fetch all employee details",e);
		}
	}
	
	public Employee getEmployeeById(int employeeId) throws EmployeeServiceException, EmployeeNotFoundException{
		try {
			return employeeDao.getEmployeeById(employeeId);
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException("Service layer failed to fetch the employee details",e);
		}
	}
	
	public void deleteEmployeeById(int employeeId) throws EmployeeServiceException, EmployeeNotFoundException{
		try {
			employeeDao.deleteEmployeeById(employeeId);
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException("Service layer failed to delete the employee",e);
		}
	}
	
	public void updateEmployee(Employee employee) throws EmployeeServiceException, EmployeeNotFoundException{
		if(!Validate.validEmployeeDetails(new String[]{
			    String.valueOf(employee.getEmployeeId()),
			    employee.getFirstName(),
			    employee.getLastName(),
			    employee.getEmail(),
			    employee.getPhone(),
			    employee.getDepartment(),
			    String.valueOf(employee.getSalary()),
			    String.valueOf(employee.getJoinDate())
			})) {
			throw new EmployeeServiceException("Service layer failed due to invalid employee details");
		}
		try {
			if(!employeeDao.employeeExists(employee.getEmployeeId())) {
				throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeId() + " not found.");
			}
			employeeDao.updateEmployee(employee);
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException("Service layer failed to update the employee",e);
		}
	}
	
	public void addEmployee(Employee employee) throws EmployeeServiceException, EmployeeExistException{
		if(!Validate.validEmployeeDetails(new String[]{
			    String.valueOf(employee.getEmployeeId()),
			    employee.getFirstName(),
			    employee.getLastName(),
			    employee.getEmail(),
			    employee.getPhone(),
			    employee.getDepartment(),
			    String.valueOf(employee.getSalary()),
			    String.valueOf(employee.getJoinDate())
			})) {
			throw new EmployeeServiceException("Service layer failed due to invalid employee details");
		}
		try {
			if(employeeDao.employeeExists(employee.getEmployeeId())) {
				throw new EmployeeExistException("Employee with ID " + employee.getEmployeeId() + " already exist.");
			}
			employeeDao.saveEmployee(employee);
		} catch (EmployeeDaoException e) {
			throw new EmployeeServiceException("Service layer failed to update the employee",e);
		}
	}
}
