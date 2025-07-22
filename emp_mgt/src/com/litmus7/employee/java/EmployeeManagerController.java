package com.litmus7.employee.java;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class EmployeeManagerController {

	static {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
	
    public Connection getConnection() throws SQLException {
    	final String url = "jdbc:mysql://localhost:3306/employee_details";
        final String user = "root";
        final String pass = "admin@2003";
        return DriverManager.getConnection(url, user, pass);
    }

    public List<Employee> readCSV(String filePath) {
        List<Employee> employees = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if (lineNum == 1) continue; // Skip header

                String[] data = line.split(",");
                new Validate();
				if (Validate.isValidRecord(data)) {
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
                    System.err.println("Error in line " + lineNum + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }

        return employees;
    }



    public Response writeDataToDB(String csvPath) {
    	Response response=new Response();
    	response.errorMessage="Data imported successfully";
        response.statusCode=200;
        try (Connection conn = getConnection()) {
        	String checkQuery = "SELECT COUNT(*) FROM employee WHERE emp_id = ?";
            String insertQuery = "INSERT INTO employee (emp_id, first_name, last_name, email, phone, department, salary, join_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            List<Employee> employees = readCSV(csvPath);
            for (Employee emp : employees) {
            	checkStmt.setInt(1, emp.getEmpId());
                ResultSet rs = checkStmt.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("Employee ID " + emp.getEmpId() + " already exists. Skipping...");
                    continue;
                }

                insertStmt.setInt(1, emp.getEmpId());
                insertStmt.setString(2, emp.getFirstName());
                insertStmt.setString(3, emp.getLastName());
                insertStmt.setString(4, emp.getEmail());
                insertStmt.setString(5, emp.getPhone());
                insertStmt.setString(6, emp.getDepartment());
                insertStmt.setDouble(7, emp.getSalary());
                insertStmt.setDate(8, emp.getJoinDate());

                insertStmt.executeUpdate();
                System.out.println("Inserted Employee ID: " + emp.getEmpId());
            }
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
            response.errorMessage="Database Error";
            response.statusCode=500;
        }
        return response;
    }

}
