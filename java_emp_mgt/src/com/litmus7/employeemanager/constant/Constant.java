package com.litmus7.employeemanager.constant;

public class Constant {

	public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    public static final String PHONE_REGEX = "^[6-9]\\d{9}$"; // Indian 10-digit number starting with 6-9
    public static final String SALARY_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$"; // Optional decimals
    public static final String DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$"; // yyyy-mm-dd

    // SQL Queries
    public static final String COUNT_EMPLOYEE_ID = "SELECT COUNT(*) FROM employee WHERE emp_id = ?";
    public static final String INSERT_EMPLOYEE = 
        "INSERT INTO employee (emp_id, first_name, last_name, email, phone, department, salary, join_date) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_EMPLOYEES ="SELECT emp_id, first_name, last_name, email, phone, department, salary, join_date FROM employee";
    public static final String GET_EMPLOYEE_BY_ID ="SELECT emp_id, first_name, last_name, email, phone, department, salary, join_date FROM employee WHERE emp_id = ?";
    public static final String DELETE_EMPLOYEE_BY_ID ="DELETE FROM employee WHERE emp_id = ?";
    public static final String UPDATE_EMPLOYEE = "UPDATE employee SET first_name = ?, last_name = ?, email = ?, phone = ?, department = ?, salary = ?, join_date = ? WHERE emp_id = ?";
    public static final String TRANSFER_DEPARTMENT="UPDATE employee SET department = ? WHERE emp_id = ?";
}
