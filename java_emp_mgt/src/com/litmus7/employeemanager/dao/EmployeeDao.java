package com.litmus7.employeemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.constant.Constant;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.util.DataBaseConnection;

public class EmployeeDao {
	public void saveEmployee(Employee employee) throws EmployeeDaoException{
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
			throw new EmployeeDaoException("DataBase Error while saving an employee",e);
		}
    }
	
	public List<Employee> getAllEmployees() throws EmployeeDaoException{
		List<Employee>employees = new ArrayList<>();
		try(Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(Constant.GET_EMPLOYEES);
				ResultSet rs=stmt.executeQuery() ){
			
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
			throw new EmployeeDaoException("DataBase Error while fetching all the employees",e);
		}
		return employees;
	}
	
	public boolean employeeExists(int employeeID) throws EmployeeDaoException{
		boolean employeeExist = false;
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(Constant.COUNT_EMPLOYEE_ID)){
			stmt.setInt(1, employeeID);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				employeeExist= rs.getInt(1) > 0;
	        }
		} catch (SQLException e) {
			throw new EmployeeDaoException("DataBase Error",e);
		}
		return employeeExist;
	}
	
	public Employee getEmployeeById(int employeeId) throws EmployeeDaoException, EmployeeNotFoundException {
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(Constant.GET_EMPLOYEE_BY_ID)){
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Employee(
						rs.getInt("emp_id"),
		                rs.getString("first_name"),
		                rs.getString("last_name"),
		                rs.getString("email"),
		                rs.getString("phone"),
		                rs.getString("department"),
		                rs.getDouble("salary"),
		                rs.getDate("join_date")
						);
	        }else {
				throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
				}
		} catch (SQLException e) {
			throw new EmployeeDaoException("DataBase Error",e);
		}
	}
	
	public void deleteEmployeeById(int employeeId) throws EmployeeDaoException, EmployeeNotFoundException {
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(Constant.DELETE_EMPLOYEE_BY_ID)){
			stmt.setInt(1, employeeId);
			int rowsAffected =stmt.executeUpdate();
			System.out.println(rowsAffected);
			if(rowsAffected==0) {
				throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
			}
		} catch (SQLException e) {
			throw new EmployeeDaoException("DataBase Error",e);
		}
	}
	
	public void updateEmployee(Employee employee) throws EmployeeDaoException {
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(Constant.UPDATE_EMPLOYEE)){
			stmt.setString(1, employee.getFirstName());
			stmt.setString(2, employee.getLastName());
			stmt.setString(3, employee.getEmail());
			stmt.setString(4, employee.getPhone());
			stmt.setString(5, employee.getDepartment());
			stmt.setDouble(6, employee.getSalary());
			stmt.setDate(7,employee.getJoinDate());
			stmt.setInt(8, employee.getEmployeeId());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new EmployeeDaoException("DataBase Error",e);
		}
	}
	
	public int addEmployeesInBatch(List<Employee> employeeList) throws EmployeeDaoException{
		int successCount=0;
		try (Connection conn = DataBaseConnection.getConnection();
				PreparedStatement insertStmt = conn.prepareStatement(Constant.INSERT_EMPLOYEE)) {
			
			for (Employee emp : employeeList) {
	            try {
	            	insertStmt.setInt(1, emp.getEmployeeId());
	            	insertStmt.setString(2, emp.getFirstName());
	            	insertStmt.setString(3, emp.getLastName());
	            	insertStmt.setString(4, emp.getEmail());
	            	insertStmt.setString(5, emp.getPhone());
	            	insertStmt.setString(6, emp.getDepartment());
	            	insertStmt.setDouble(7, emp.getSalary());
	            	insertStmt.setDate(8, new java.sql.Date(emp.getJoinDate().getTime()));
	            	insertStmt.addBatch();
	            } catch (Exception e) {
	            	//logging
	            }
	            }
			int[] results = insertStmt.executeBatch();
			for (int result : results) {
	            if (result == Statement.SUCCESS_NO_INFO || result > 0) {
	                successCount++;
	            }
			}
		}catch (SQLException e) {
			throw new EmployeeDaoException("DataBase Error while saving an employee",e);
		}
		return successCount;
    }
	
	public void transferEmployeesToDepartment(List<Integer> employeeIds, String newDepartment) throws EmployeeDaoException {
		Connection conn=null;
		PreparedStatement pstmt = null;
	    try  {
	    	conn = DataBaseConnection.getConnection();
	        conn.setAutoCommit(false); 
	        pstmt = conn.prepareStatement(Constant.TRANSFER_DEPARTMENT);
	        
	        for (Integer empId : employeeIds) {
	            pstmt.setString(1, newDepartment);
	            pstmt.setInt(2, empId);
	            pstmt.addBatch();
	        }

	        int[] results = pstmt.executeBatch();
	        conn.commit(); // Commit if all succeed

	    } catch (SQLException e) {
	        try {
	            if(conn !=null) conn.rollback(); 
	        } catch (SQLException rollbackEx) {
	            throw new EmployeeDaoException("Rollback failed", rollbackEx);
	        }
	        throw new EmployeeDaoException("Failed to transfer employees", e);
	    }finally {
	    	try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	        	throw new EmployeeDaoException("Unable to close Connection", e);
	        }
	    }
	}

}
