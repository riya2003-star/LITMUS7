package employeemanager;

import java.io.FileReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerController {
	
	private static final String url = "jdbc:mysql://localhost:3306/employee_datails";
    private static final String user = "root";
    private static final String pass = "riya@2003";
    static {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
	
    public Connection getConnection() throws SQLException {
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
                if (data.length != 8) {
                    System.out.println("Skipping invalid line " + lineNum);
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

    public void writeToDB(Employee emp, Connection conn) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM employee WHERE emp_id = ?";
        String insertQuery = "INSERT INTO employee (emp_id, first_name, last_name, email, phone, department, salary, join_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            checkStmt.setInt(1, emp.getEmpId());
            ResultSet rs = checkStmt.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("Employee ID " + emp.getEmpId() + " already exists. Skipping...");
                return;
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
    }

    public void writeDataToDB(String csvPath) {
        try (Connection conn = getConnection()) {
            List<Employee> employees = readCSV(csvPath);
            for (Employee emp : employees) {
                writeToDB(emp, conn);
            }
        } catch (SQLException e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }

}
