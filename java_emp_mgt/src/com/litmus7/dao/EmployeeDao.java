package com.litmus7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.litmus7.constant.Constant;
import com.litmus7.dto.Employee;

public class EmployeeDao {
	public static void insertDataToDb(Employee emp,Connection conn){
		try (PreparedStatement checkStmt = conn.prepareStatement(Constant.COUNT_EMPLOYEE_ID);
			     PreparedStatement insertStmt = conn.prepareStatement(Constant.INSERT_EMPLOYEE)) {

			    checkStmt.setInt(1, emp.getEmpId());
			    try (ResultSet rs = checkStmt.executeQuery()) {
			        if (rs.next() && rs.getInt(1) == 0) {
			            insertStmt.setInt(1, emp.getEmpId());
			            insertStmt.setString(2, emp.getFirstName());
			            insertStmt.setString(3, emp.getLastName());
			            insertStmt.setString(4, emp.getEmail());
			            insertStmt.setString(5, emp.getPhone());
			            insertStmt.setString(6, emp.getDepartment());
			            insertStmt.setDouble(7, emp.getSalary());
			            insertStmt.setDate(8, emp.getJoinDate());

			            insertStmt.executeUpdate();
			        }
			    }
			}catch (SQLException e) {
			    System.err.println("Error inserting employee with ID: " + emp.getEmpId());
			    e.printStackTrace();
			}
      
	}
}
