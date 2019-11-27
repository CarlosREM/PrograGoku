package ADT;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimeConfig {
     private int clockUpdateLapse;
     private int maxYearDays;
     private int grow;
     private static TimeConfig config;
     
     public int getClockUpdateLapse() {
		return clockUpdateLapse;
     }
     public int getMaxYearDays() {
    	 return maxYearDays;
     }
     public int  getGrow() {
    	 return grow; 
     }
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
			System.out.println(tokens);
	    	maxYearDays = Integer.parseInt(tokens.get(1));
	    	clockUpdateLapse = Integer.parseInt(tokens.get(3));
	    	grow = Integer.parseInt(tokens.get(5));
		}
		catch (IOException e) {
			e.printStackTrace();
		}   	 
    	 
     }
     
}
