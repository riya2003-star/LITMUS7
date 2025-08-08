package com.litmus7.employeemanager.service;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger logger = LogManager.getLogger(EmployeeManagerService.class);
	
	public List<Employee> loadCSV(String filePath) throws EmployeeServiceException {
		logger.trace("Entering loadCSV with path: {}", filePath);
    	List<Employee> employees = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String[]> lines;
		try {
			lines = ReadCsv.readCsv(filePath);
		} catch (FileNotFoundException e) {
			logger.error("CSV file not found at path: {}", filePath);
			throw new EmployeeServiceException("Csv file not found",e);
		}
        for(String[] data:lines) {
        	if (!Validate.validEmployeeDetails(data)) { 
        		logger.warn("Invalid employee details skipped: {}", (Object) data);
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
            	logger.error("Error while parsing employee data: {}", (Object) data);
            	throw new EmployeeServiceException("Invalid employee details during parsing", e);
            }
        }
        logger.info("CSV loaded successfully with {} valid employees", employees.size());
        return employees;
    }
	
	
	public int writeDataToDB(String csvPath) throws EmployeeServiceException  {
		logger.trace("Starting writeDataToDB with CSV path: {}", csvPath);
		int countInsertedEmployees=0;
		List<Employee> employees = loadCSV(csvPath);
		for (Employee employee : employees) {
			try {
				if(employeeDao.employeeExists(employee.getEmployeeId())) {
					logger.warn("Employee with ID {} already exists. Skipping.", employee.getEmployeeId());
					continue;
				}
	        	employeeDao.saveEmployee(employee);
	        	logger.info("Employee inserted: ID {}", employee.getEmployeeId());
	        	countInsertedEmployees++;
			}
			catch(EmployeeDaoException e) {
				logger.error("Error saving employee: ID {}", employee.getEmployeeId());
				throw new EmployeeServiceException("Service layer failed to save data to db",e);
			}
			
        }
		logger.info("Total employees inserted: {}", countInsertedEmployees);
		return countInsertedEmployees;
	}
	
	public List<Employee> getAllEmployees() throws EmployeeServiceException{
		logger.trace("Fetching all employees from DB");
		try {
			List<Employee> employees=employeeDao.getAllEmployees();
			logger.info("Retrieved {} employees from DB", employees.size());
			return employees;
		} catch (EmployeeDaoException e) {
			logger.error("Failed to fetch all employees", e);
			throw new EmployeeServiceException("Service layer failed to fetch all employee details",e);
		}
	}
	
	public Employee getEmployeeById(int employeeId) throws EmployeeServiceException, EmployeeNotFoundException{
		logger.trace("Fetching employee by ID: {}", employeeId);
		try {
			Employee employee = employeeDao.getEmployeeById(employeeId);
			logger.info("Employee found: ID {}", employeeId);
			return employee;
		} catch (EmployeeDaoException e) {
			logger.error("Error fetching employee with ID {}", employeeId);
			throw new EmployeeServiceException("Service layer failed to fetch the employee details",e);
		}
	}
	
	public void deleteEmployeeById(int employeeId) throws EmployeeServiceException, EmployeeNotFoundException{
		logger.trace("Deleting employee with ID: {}", employeeId);
		try {
			employeeDao.deleteEmployeeById(employeeId);
			logger.info("Employee deleted: ID {}", employeeId);
		} catch (EmployeeDaoException e) {
			logger.error("Failed to delete employee: ID {}", employeeId, e);
			throw new EmployeeServiceException("Service layer failed to delete the employee",e);
		}
	}
	
	public void updateEmployee(Employee employee) throws EmployeeServiceException, EmployeeNotFoundException{
		logger.trace("Updating employee: {}", employee);
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
			logger.warn("Invalid employee details provided for update: {}", employee);
			throw new EmployeeServiceException("Service layer failed due to invalid employee details");
		}
		try {
			if(!employeeDao.employeeExists(employee.getEmployeeId())) {
				logger.warn("Employee not found for update: ID {}", employee.getEmployeeId());
				throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeId() + " not found.");
			}
			employeeDao.updateEmployee(employee);
			logger.info("Employee updated: ID {}", employee.getEmployeeId());
		} catch (EmployeeDaoException e) {
			logger.error("Error updating employee: {}", employee);
			throw new EmployeeServiceException("Service layer failed to update the employee",e);
		}
	}
	
	public void addEmployee(Employee employee) throws EmployeeServiceException, EmployeeExistException{
		logger.trace("Adding new employee: {}", employee);
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
			logger.warn("Invalid employee details provided for add: {}", employee);
			throw new EmployeeServiceException("Service layer failed due to invalid employee details");
		}
		try {
			if(employeeDao.employeeExists(employee.getEmployeeId())) {
				logger.warn("Employee already exists: ID {}", employee.getEmployeeId());
				throw new EmployeeExistException("Employee with ID " + employee.getEmployeeId() + " already exist.");
			}
			employeeDao.saveEmployee(employee);
			logger.info("Employee added successfully: ID {}", employee.getEmployeeId());
		} catch (EmployeeDaoException e) {
			logger.error("Error adding employee: {}", employee, e);
			throw new EmployeeServiceException("Service layer failed to update the employee",e);
		}
	}
	
	public int addEmployeesInBatch(List<Employee> employeeList) throws EmployeeServiceException{
		logger.trace("Batch adding {} employees", employeeList.size());
		Iterator<Employee> iterator = employeeList.iterator();
		while (iterator.hasNext()) {
		    Employee employee = iterator.next();
		    boolean isValid = Validate.validEmployeeDetails(new String[]{
		        String.valueOf(employee.getEmployeeId()),
		        employee.getFirstName(),
		        employee.getLastName(),
		        employee.getEmail(),
		        employee.getPhone(),
		        employee.getDepartment(),
		        String.valueOf(employee.getSalary()),
		        String.valueOf(employee.getJoinDate())
		    });

		    if (!isValid) {
		    	logger.warn("Invalid employee data in batch. Skipping: {}", employee);
		        iterator.remove();
		        continue;
		    }
		    try {
				if(employeeDao.employeeExists(employee.getEmployeeId())) {
					logger.warn("Duplicate employee in batch. Skipping: ID {}", employee.getEmployeeId());
					iterator.remove();
					continue;
				}
			} catch (EmployeeDaoException e) {
				logger.error("Error checking existence in batch: ID {}", employee.getEmployeeId());
				throw new EmployeeServiceException("Service layer failed to update the employee",e);
			}
		}
		if(employeeList.isEmpty()) {
			logger.warn("No valid employees to insert in batch");
			throw new EmployeeServiceException("Service layer failed to update the employee");
		}
		
		try {
			int inserted = employeeDao.addEmployeesInBatch(employeeList);
			logger.info("Successfully inserted {} employees in batch", inserted);
			return inserted;
		} catch (EmployeeDaoException e) {
			logger.error("Batch insertion failed");
			throw new EmployeeServiceException("Service layer failed to update the employee",e);
		}
	}
	
	public void transferEmployeesToDepartment(List<Integer>employeeIds, String newDepartment) throws EmployeeServiceException{
		logger.trace("Transferring {} employees to department {}", employeeIds.size(), newDepartment);
		try {
			employeeDao.transferEmployeesToDepartment(employeeIds,newDepartment);
			logger.info("Transferred {} employees to department {}", employeeIds.size(), newDepartment);
		} catch (EmployeeDaoException e) {
			logger.error("Failed to transfer employees");
			throw new EmployeeServiceException("Service layer failed to update the employee",e);
		}
	}
}
