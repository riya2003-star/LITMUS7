package com.litmus7.controller;

import com.litmus7.dto.Response;
import com.litmus7.service.EmployeeManagerService;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerController {
	
	List<Response<String>> response=new  ArrayList<>();
    public List<Response<String>> writeDataToDB(String csvPath) {
    	if(csvPath == null || !csvPath.toLowerCase().endsWith(".csv")) {
    		response.add(new Response<>(100,"It is not CSV file"));
    	}else {
    		EmployeeManagerService service=new EmployeeManagerService();
        	if(service.writeDataToDB(csvPath))
        		response.add(new Response<>(200,"suceess"));
        	else
        		response.add(new Response<>(100,"failed"));
    	}
    	   	
        return response;
    }
}
