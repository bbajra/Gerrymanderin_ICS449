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
				+ "Group: " + groupNumber);
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


	
}
