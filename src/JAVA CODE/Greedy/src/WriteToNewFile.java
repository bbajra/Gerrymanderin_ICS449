import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * use this class to Write the new GeoJsonCode also
 * @author Vizeng Lee
 *
 */
public class WriteToNewFile {

	ArrayList<String> fileAsString;
	ArrayList<Group> groupOfPrecints;
	ArrayList<Precints> order;
	
	public WriteToNewFile(ArrayList<String> fileAsString, ArrayList<Group> groupOfPrecints, String fileName 
			,ArrayList<Precints> order){
		this.fileAsString = fileAsString;
		this.groupOfPrecints = groupOfPrecints;
		this.order = order;
		setWinners();
		//setOrder();
		writeNewGeoJsonCode(fileName);
		writeNewOrderFile();
		
	}
	public void writeNewOrderFile(){
		try {
			PrintWriter pw = new PrintWriter("C:/Projects/ICS499/Greedy/output/OrderFile.txt");
			for(int i = 0;i<order.size();i++){
				Precints precint = order.get(i);
				pw.println();
				pw.println("Precint Name: " + precint.getPrecintName() + " Group: " + precint.getGroupNumber() + " Order: " + i + " Adjacent Precints: " );
				for(int  j = 0;j<precint.adjacentListAsString.size();j++){
					String adjacent = precint.adjacentListAsString.get(j);
					pw.print( adjacent + ", ");
					if(j == precint.adjacentListAsString.size()-1){
						pw.println();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Something failed when writting");
			e.printStackTrace();
		}
	}
	/**
	 * sets it so we know what order the precints are grouped in
	 */
	public void setOrder(){
		for(int i =0;i<order.size();i++){
			Precints precint = order.get(i);
			String name1 = precint.getPrecintName();
			for(int j=0;j<groupOfPrecints.size();j++){
				Group group = groupOfPrecints.get(j);
				for(int k = 0;k<group.groupedPrecintList.size();k++){
					Precints precint1 = group.groupedPrecintList.get(k);
					if(name1.equals(precint1.getPrecintName())){
						precint1.setPrecintName(name1 + i); 
						break;
					}
				}
			}
		}
	}
	
	public void setWinners(){
		for(int i = 0;i<groupOfPrecints.size();i++){
			Group group = groupOfPrecints.get(i);
			String winningParty = group.getWinningParty();
			String groupNumber = String.valueOf(group.getGroupNumber());
			for(int j = 0;j<group.groupedPrecintList.size();j++){
				Precints precint = group.getGroupedPrecintList().get(j);
				String newGeoJson;
				String geoJson = precint.getGeoJsonCode();
				
				newGeoJson = writeWinnersToGeoJson(geoJson, winningParty, "party");
				newGeoJson = writeGroupToGeoJson(newGeoJson, groupNumber, "group");
	
				precint.setGeoJsonCode(newGeoJson);
			}
		}
		
	}
	
	public String writeGroupToGeoJson(String geoJson, String value, String propertyName){
		String returnString = "";
		char[] org = geoJson.toCharArray();
		char[] insertValue = value.toCharArray();
		char[] newString = new char[geoJson.length() + value.length()];
		int index = getPropertyInt(geoJson, propertyName);
		//System.out.println(org[index]);
		System.arraycopy(org, 0, newString, 0, index);
		returnString = String.valueOf(newString);
		//System.out.println(returnString);
		System.arraycopy(insertValue, 0, newString, index, insertValue.length);
		returnString = String.valueOf(newString);
		//System.out.println(returnString);
		System.arraycopy(org, index, newString, index+insertValue.length, org.length-index);
		returnString = String.valueOf(newString);
		//System.out.println(returnString);
		return returnString;
	}
	
	public  String writeWinnersToGeoJson(String geoJson, String winningParty, String propertyName){
		String returnString = "";
		char[] org = geoJson.toCharArray();
		char[] insertString = winningParty.toCharArray();
		char[] newString = new char[geoJson.length() + insertString.length];
		int index = getPropertyInt(geoJson, propertyName);
		System.arraycopy(org, 0, newString, 0, index);
		String test = String.valueOf(newString);
		//System.out.println(test);
		System.arraycopy(insertString, 0, newString, index, insertString.length);
		String test1 = String.valueOf(newString);
		//System.out.println(test1);
		System.arraycopy(org, index, newString, index+insertString.length, org.length-index);
		returnString = String.valueOf(newString);
		return returnString;
	}
	private int getPropertyInt(String geoJsonCode, String property){
		char[] org = geoJsonCode.toCharArray();
		String newType = property + "\":\"";
		int length = newType.length() ;
		int indexOfEndOfType = 0;
		int j;
		int k;
		String match = "";
		for(int i = 43;i<org.length;i++){// i = 43 because the first 44 characters are all the same //THe legtnh has to be23
			j = i;
			for(k=j+length;j<k;j++){
				match = match + org[j];
			}
			if(match.equals(newType)){
				indexOfEndOfType = j;
				return indexOfEndOfType;
			}else{
				match = "";
			}
		}
		return indexOfEndOfType;
	}
	
	public void writeNewGeoJsonCode(String fileName){
		File file = new File( "C:/Projects/ICS499/Greedy/output/" +fileName);
		try {
			PrintWriter pw = new PrintWriter(file);
			System.out.println("Writting out groups");
			for(int i = 0;i<10;i++){
				String orgString = fileAsString.get(i);
					pw.println(orgString);
			}

			for(int j = 0;j<groupOfPrecints.size();j++){
				Group group = groupOfPrecints.get(j);
				for(int k = 0;k<group.groupedPrecintList.size();k++){

					Precints precint = group.groupedPrecintList.get(k);

					String geoJsonCode = precint.getGeoJsonCode();
					geoJsonCode = removeLastChar(geoJsonCode);
					if(j==groupOfPrecints.size()-1&&k==group.groupedPrecintList.size()-1){
						geoJsonCode = removeLastChar(geoJsonCode);
					}
					if(precint.getPrecintName().equals("Wood Lake Twp")){
						geoJsonCode = geoJsonCode + ',';
					}
					pw.println(geoJsonCode);
					//System.out.println(geoJsonCode);
				}
			}
			pw.println("]");
			pw.println("}");
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Something failed when writting");
			e.printStackTrace();
		}
		
	}
	private String removeLastChar(String str) {
	    return str.substring(0, str.length() - 1);
	}
	
	
}
