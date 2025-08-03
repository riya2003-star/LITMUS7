package com.litmus7.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.dao.EmployeeDao;
import com.litmus7.dto.Employee;
import com.litmus7.util.ReadCsv;
import com.litmus7.util.Validate;


public class EmployeeManagerService {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public List<Employee> loadCSV(String filePath) {
    	List<Employee> employees = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String[]>lines=ReadCsv.readCsv(filePath);
        for(String[] data:lines) {
        	if (Validate.validEmployeeDetails(data)) {  			
                continue;
            }
            try {
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
                System.err.println("Error in line " + e.getMessage());
            }
        }
        return employees;
    }
	
	
	public int writeDataToDB(String csvPath) {
		int countInsertedEmployees=0;
		List<Employee> employees = loadCSV(csvPath);
		for (Employee employee : employees) {
			if(employeeDao.employeeExists(employee.getEmployeeId())) {
				continue;
			}
			countInsertedEmployees++;
        	employeeDao.saveEmployee(employee);
        }
		return countInsertedEmployees;
	}
	
	public List<Employee> getAllEmployees(){
		return employeeDao.getAllEmployees();
	}
}
