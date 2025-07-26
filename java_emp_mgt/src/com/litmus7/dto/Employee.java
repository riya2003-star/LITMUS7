<<<<<<<< HEAD:emp_mgt/src/com/litmus7/employee/java/Employee.java
package com.litmus7.employee.java;
========
package com.litmus7.dto;
>>>>>>>> 940d752 (updated):java_emp_mgt/src/com/litmus7/dto/Employee.java

import java.sql.Date;

public class Employee {

	private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private double salary;
    private Date joinDate;

    public Employee(int empId, String firstName, String lastName, String email, String phone,
                    String department, double salary, Date joinDate) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    public int getEmpId() { return empId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public Date getJoinDate() { return joinDate; }

}
