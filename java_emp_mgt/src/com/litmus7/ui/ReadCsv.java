package com.litmus7.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCsv{
	public static List<String> readCsv(String filePath) {
		List<String> lines=new ArrayList<>();
		
		try (Scanner scanner = new Scanner(new FileReader(filePath))) {
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if (lineNum == 1) continue; // Skip header
                lines.add(line);
            }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lines;
		
	}
}
