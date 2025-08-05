package com.litmus7.employeemanager.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCsv{
	public static List<String[]> readCsv(String filePath) throws FileNotFoundException {
		List<String[]> lines=new ArrayList<>();
		Scanner scanner=null;
		try {
			scanner = new Scanner(new FileReader(filePath));
			int lineNum = 0;
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine();
	            lineNum++;
	            if (lineNum == 1) continue; // Skip header
	            String[] datas=line.split(",");
	            lines.add(datas);
	        }
		}finally {
			if(scanner!=null) scanner.close();
		}
		return lines;
	}
}
