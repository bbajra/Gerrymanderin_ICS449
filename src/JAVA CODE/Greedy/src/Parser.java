

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class Parser {
	private ArrayList<String> StringHolder = new ArrayList<String>();
	String FileLocation = "C:/Projects/ICS499/Greedy/Data/GeoJsonWithAdjacentPrecints.txt";
	
	//String FileLocation = "C:/Projects/ICS499/Redistricting/Data/TestData.txt";
	public Parser(){
		readData();
	}
	public ArrayList<String> getStringHolder() {
		return StringHolder;
	}

	/*
	 * this method takes the file line by line and reads it into an array
	 */
	public void readData() {
		String line;
		
		//int i = 1;
		try {
			FileReader geoJson = new FileReader(FileLocation);
            BufferedReader br = new BufferedReader(geoJson);
            while((line = br.readLine()) != null) {
                //System.out.println("{" + i + "}   " + line );
                StringHolder.add(line);
                //i++;
            }
            br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
}
