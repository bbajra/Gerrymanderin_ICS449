

import java.util.ArrayList;

public class Precints {

	private String geoJsonCode;//This is the line of code all the data is from
	private String precintName;//Name of the precint
	private String demVotes;//Number of democrat voters
	private String repVotes;//Number of republican voters
	private String totVotes;//Number of total voters
	private ArrayList<String> LongLatList = new ArrayList<String>();//This holds the latitude and longtiude ex:[-93.1914,46.7668]
	private boolean grouped = false;//if the precint is not grouped yet set it as null
	private int groupNumber;//This is Which groups its in
	private int placeInIndex;//This is where the object is inside the Array
	public ArrayList<Precints> adjacentList = new ArrayList<Precints>();
	public ArrayList<String> adjacentListAsString = new ArrayList<String>();//This holds the adjacent precint name, used a refernce to find the acutal precints
	public ArrayList<AdjacentPrecint> adjacentPrecintList = new ArrayList<AdjacentPrecint>();//This holds the index of where the precint is in the list
	
	public ArrayList<String> getAdjacentListAsString() {
		return adjacentListAsString;
	}

	public ArrayList<String> getLongLatList() {
		return LongLatList;
	}
	public Precints(String geoJsonCode, String precintName, String demVotes, String repVotes, String totVotes, ArrayList<String> longlatList){
		this.geoJsonCode = geoJsonCode;
		this.precintName = precintName;
		this.demVotes = demVotes;
		this.repVotes = repVotes;
		this.totVotes = totVotes;
		this.LongLatList = longlatList;
	}
	public String getGeoJsonCode() {
		return geoJsonCode;
	}
	public String getPrecintName() {
		return precintName;
	}
	public String getDemVotes() {
		return demVotes;
	}
	public String getRepVotes() {
		return repVotes;
	}
	public String getTotVotes() {
		return totVotes;
	}
	
	public int getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}
	public boolean isGrouped() {
		return grouped;
	}
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}
	public int getPlaceInIndex() {
		return placeInIndex;
	}
	public void setPlaceInIndex(int placeInIndex) {
		this.placeInIndex = placeInIndex;
	}
	public void printAll(){
		System.out.println("Precint Name: " + precintName + " Democratic Voters: " + demVotes
				+ " Republican Voters: " + repVotes + " Total Number Of Voters: " + totVotes
				+ " Group: " + groupNumber);
		//System.out.println("Longitue and Latitude");
		for(int i = 0;i<LongLatList.size();i++){
			//System.out.println(LongLatList.get(i));
		}
	
	}
	public void printAdjacentPrecints(){
		System.out.println("Adjacent Precints");
		for(int i = 0;i<adjacentList.size();i++){
			Precints precint1 = adjacentList.get(i);
			String name = precint1.getPrecintName();
			System.out.println("Precint: [" + i + "] : " + name);
		}
	}
	/**
	 * adds adjacent precints into the geoJson code
	 */
	public void addAdjacentPrecintsToString(){
		
		String orgString = geoJsonCode;
		char[] org = orgString.toCharArray();
		char[] precintsToInsertToProperty = turnAdjacentPrecintsIntoString().toCharArray();
		char[] finalStringArray = new char[org.length+precintsToInsertToProperty.length];
		int indexOfMove = findInsertIndex(org, "AdjacentPrecints");
		System.arraycopy(org, 0, finalStringArray, 0, indexOfMove);
		String x = new String(finalStringArray);
		System.out.println(x);
		System.arraycopy(precintsToInsertToProperty, 0, finalStringArray, indexOfMove,precintsToInsertToProperty.length-1);//copys values to be inserted
		String y = new String(finalStringArray);
		System.out.println(y);
		System.arraycopy(org, indexOfMove, finalStringArray, indexOfMove+precintsToInsertToProperty.length-1, org.length-indexOfMove);
		String z = new String(finalStringArray);
		System.out.println(z);
		String finalString = new String(finalStringArray);
		this.geoJsonCode = finalString;
	}
	/**
	 * turns precints names into a String of precints name
	 * When copying the array List make sure to not copy the last part
	 * @return
	 */
	private String turnAdjacentPrecintsIntoString(){
		String adjacentPrecintsAsString = "";
		for(int i = 0;i<adjacentList.size();i++){
			Precints precint = adjacentList.get(i);
			String precintName = precint.getPrecintName();
			adjacentPrecintsAsString = adjacentPrecintsAsString + precintName + ",";
		}
		return adjacentPrecintsAsString;
	}
	
	/**
	 * Finds the index where the votes should be inserted
	 * @param org
	 * @param type
	 * @return
	 */
	private int findInsertIndex(char[] org, String type){
		String newType = type + "\":\"";
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
	public void AddToAdjacentListAsString(String precintNameAsString){
		adjacentListAsString.add(precintNameAsString);
	}
	
	public void printNameAndGroups(){
		System.out.println(precintName + ": Group: " + groupNumber);
	}
}

