package employeemanager;

public class EmployeeManagerApp {

	public static void main(String[] args) {
		EmployeeManagerController controller = new EmployeeManagerController();
        String csvFilePath = "E:\\Users\\johns\\LITMUS\\LITMUS7\\java_emp_mgt\\employees.csv"; 
        controller.writeDataToDB(csvFilePath);
	}

}
