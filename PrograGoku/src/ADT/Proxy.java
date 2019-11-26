package ADT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Proxy {
	public static StringBuilder log = new StringBuilder();	
	
	public static void generateFile() {
	       SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_z");
	        Date date = new Date(System.currentTimeMillis());
	        
            String userHomeFolder = System.getProperty("user.home");
            String fileSeparator = System.getProperty("file.separator");
            String absoluteFilePath = userHomeFolder+fileSeparator+formatter.format(date)+".txt";
            PrintWriter writer;
			try {
				writer = new PrintWriter(new File(absoluteFilePath));
				writer.write(log.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
}
