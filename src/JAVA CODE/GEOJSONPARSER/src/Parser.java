import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Parser {
	private ArrayList<String> StringHolder = new ArrayList<String>();
	String FileLocation = "C:/wamp64/www/src/data/mnprecinct.geojson";
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
	
	public void writeToFile(ArrayList<String> modifiedList){
		
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(FileLocation));
			for(int i = 0;i<modifiedList.size();i++){
			pw.println(modifiedList.get(i));
			System.out.println(modifiedList.get(i));
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
