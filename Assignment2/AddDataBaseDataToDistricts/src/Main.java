import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/**
 * This project connects to the database and adds the votes from the database
 * into the geojson code
 * Change the variable typeOfVote to change the votes
 * @author Vizeng Lee
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final long startTime = System.currentTimeMillis();
		DataBaseConnection conn = new DataBaseConnection();//Database connection
		Parser parse = new Parser();//Reads the data
		ArrayList<String> listOfGeoJson = parse.getStringHolder();//Gets the data that is put into ana array list
		//Type Of Vote is changed based on what is needed 
		 //String typeOfVote =  "Presidental";
		 String typeOfVote =  "Congressional";
		 //String typeOfVote ="MN Senate";
		 //String typeOfVote ="MN House";
		WriteToGeoJson run = new WriteToGeoJson(conn, listOfGeoJson, typeOfVote);//adds to the geojson and puts it into a ArrayList
		ArrayList<String> finalList = run.getNewArrayList();//This is the final list
		WriteNewFile writeMyFile = new WriteNewFile("Test2",finalList);//FInaly writes it to a file
		final long endTime = System.currentTimeMillis();
		long Seconds = ( endTime/ 1000) % 60;

		System.out.println("Total execution time: " + (Seconds) + "seconds");
		System.out.println("DONE");
		
		
		
	}
	
	
	


}
