import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ReadAndWrite {
	private String FileName;
	private String FileLocation = "C:/wamp64/www/src/TestData/data";
	private File file = new File(FileLocation + "/" + FileName);
	private PrintWriter pw;
	private ArrayList<String> StringHolder = new ArrayList<String>();

	public ReadAndWrite(String FileName){
		setFileName(FileName);
		readData();
		
	}
	
	
	/*
	 * this method takes the file line by line and reads it into an array
	 */
	public void readData() {
		String line;
		
		//int i = 1;
		try {
			FileReader geoJson = new FileReader("C:/wamp64/www/src/data/testData/mnprecinct.geojson");
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
	
	
	private void setFileName(String fileName){
		this.FileName = fileName;
	}

	/**
	 * Use this code to write to the file
	 * @param cordinateX
	 * @param cordinateY
	 */
	public void writeToFile(String cordinateX, String cordinateY){

	}
	
	
	public ArrayList<String> getStringHolder() {
		return StringHolder;
	}
}
