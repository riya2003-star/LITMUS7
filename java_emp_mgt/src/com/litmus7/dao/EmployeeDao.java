package com.litmus7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.constant.Constant;
import com.litmus7.dto.Employee;
import com.litmus7.util.DataBaseConnection;

public class EmployeeDao {
	public void saveEmployee(Employee employee){
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement insertStmt = conn.prepareStatement(Constant.INSERT_EMPLOYEE)) {
			
			insertStmt.setInt(1, employee.getEmployeeId());
            insertStmt.setString(2, employee.getFirstName());
            insertStmt.setString(3, employee.getLastName());
            insertStmt.setString(4, employee.getEmail());
            insertStmt.setString(5, employee.getPhone());
            insertStmt.setString(6, employee.getDepartment());
            insertStmt.setDouble(7, employee.getSalary());
            insertStmt.setDate(8, employee.getJoinDate());

            insertStmt.executeUpdate();
		}catch (SQLException e) {
			    e.printStackTrace();
		}
    }
	
	public List<Employee> getAllEmployees(){
		List<Employee>employees = new ArrayList<>();
		try(Connection conn = DataBaseConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(Constant.GET_EMPLOYEES) ){
			
			while (rs.next()) {
				Employee employee = new Employee(
		                rs.getInt("emp_id"),
		                rs.getString("first_name"),
		                rs.getString("last_name"),
		                rs.getString("email"),
		                rs.getString("phone"),
		                rs.getString("department"),
		                rs.getDouble("salary"),
		                rs.getDate("join_date")
		            );
	            
	            employees.add(employee);
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	
	public boolean employeeExists(int employeeID) {
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(Constant.COUNT_EMPLOYEE_ID)){
			stmt.setInt(1, employeeID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
