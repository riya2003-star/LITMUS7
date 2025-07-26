package com.litmus7.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.dao.EmployeeDao;
import com.litmus7.dto.Employee;
import com.litmus7.ui.DataBaseConnection;
import com.litmus7.ui.ReadCsv;
import com.litmus7.util.Validate;


public class EmployeeManagerService {
	
	public List<Employee> loadCSV(String filePath) {
    	List<Employee> employees = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String>lines=new ArrayList<>();
        lines =ReadCsv.readCsv(filePath);
        for(String line:lines) {
        	String[] data = line.split(",");
        	if (Validate.validLength(data) & Validate.validEmpID(data[0]) & Validate.validName(data[1]) & Validate.validEmail(data[3]) 
            		& Validate.validPhone(data[4]) & Validate.validDate(data[6])) {  			
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
	
	
	public boolean writeDataToDB(String csvPath) {
		List<Employee> employees = loadCSV(csvPath);
		try (Connection conn = DataBaseConnection.getConnection()) {
            
            for (Employee emp : employees) {
            	EmployeeDao.insertDataToDb(emp,conn);
            }
            return true;
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
        }
		return false;
	}
	
	
}
