package com.litmus7.employeemanager.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileReader;


public class DataBaseConnection {
	private static final String URL;
    private static final String USER_NAME;
    private static final String PASSWORD;
    static {
        Properties prop = new Properties();
        try {
        	FileReader reader = new FileReader("C:\\Users\\johns\\eclipse-workspace\\my\\hi\\demo.properties");
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL = prop.getProperty("url");
        USER_NAME = prop.getProperty("userName");
        PASSWORD = prop.getProperty("password");
    }

	
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
