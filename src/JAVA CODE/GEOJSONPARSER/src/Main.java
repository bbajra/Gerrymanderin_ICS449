import java.util.ArrayList;
/**
*Created by Vizeng Lee
*
**/
//Congressional districts
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parser = new Parser();
		Writter changeGeoJson = new Writter(parser.getStringHolder());
		//This is based of the results in mySQL
		changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"1\"");
		changeGeoJson.writeToGeoJson("rep", "\"CongDist\":\"2\"");
		changeGeoJson.writeToGeoJson("rep", "\"CongDist\":\"3\"");
		changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"4\"");
		changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"5\"");
		changeGeoJson.writeToGeoJson("rep", "\"CongDist\":\"6\"");
		changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"7\"");
		changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"8\"");
		ArrayList<String> modifiedList = changeGeoJson.getList();
		parser.writeToFile(modifiedList);
		System.out.println("Completed");
		
		
		
	}

}
