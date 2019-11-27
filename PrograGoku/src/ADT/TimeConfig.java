package ADT;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeConfig {
     public static int clockUpdateLapse;
     public static int maxYearDays;
     private static TimeConfig config;
     
     private TimeConfig() {
    	generate(); 
	}
     
     public static TimeConfig getInstance() {
    	 if(config == null) {
    		 config = new TimeConfig();
    	 }
    	 return config;
     }
     
     private void generate() {
    	 Path filePath = Paths.get("res/time config.txt");
    	 Scanner scanner;
		try {
			scanner = new Scanner(filePath);
    	 List<String> tokens = new ArrayList<>();
    	 while (scanner.hasNext()) {	
    	    	 tokens.add(scanner.next());
    	 }
    	 maxYearDays = Integer.getInteger(tokens.get(1));
    	 clockUpdateLapse = Integer.getInteger(tokens.get(1));
		} catch (IOException e) {
			e.printStackTrace();
		}   	 
    	 
     }
     
}
