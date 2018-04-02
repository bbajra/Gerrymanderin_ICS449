import java.util.ArrayList;
import java.util.Queue;

public class PrecintCreator {

	private ArrayList<String> untouchedList;
	private ArrayList<Precints> precintList = new ArrayList<Precints>();
	private int TotalNumberOfVotes;
	

	/**
	 * constructor that takes in A line from the geoJson file
	 * @param geoJsonList
	 */
	public PrecintCreator(ArrayList<String> geoJsonList){
		this.untouchedList = geoJsonList;
		start();
	}
	/**
	 * runs through the arrayList and finds the string that has the precints
	 * and puts it in the method createPrecuints
	 */
	private void start(){
		String geoJsonCode;
		for(int i = 0;i<untouchedList.size();i++){
			geoJsonCode = untouchedList.get(i);
			if(geoJsonCode.contains("DemocratVoters")){
				createPrecints(geoJsonCode);
			}else{
				
			}
		}
	}
	
	
	/**
	 * This method takes the String of precints in the geojson and creates the precints
	 * @param geoJsonCode
	 */
	private void createPrecints(String geoJsonCode){
		char[] org = geoJsonCode.toCharArray();
		String precintName = getPrecintNameFromCode(geoJsonCode);
		String demVotes = getDemocrateVotersFromCode(geoJsonCode);
		String repVotes = getRepublicanVotersFromCode(geoJsonCode);
		String totVotes = getTotalVotersFromCode(geoJsonCode);
		ArrayList<String> LongLatList = getLongLatIntoArray(org);
		addTotalVotes(totVotes);
		Precints precint = new Precints(geoJsonCode, precintName, demVotes, repVotes, totVotes, LongLatList);
		precintList.add(precint);
		System.out.println("Added to precint "  + precintName);
		
		
		
	}
	/**
	 * get precint name from code
	 * precint name char = 11
	 * Precinct":"
	 * @param geoJsonCode
	 * @return
	 */
	private String getPrecintNameFromCode(String geoJsonCode){
		String precintName = "";
		char[] org = geoJsonCode.toCharArray();
		for(int i = 44;i<org.length;i++){
			if(org[i]=='"'){
				return precintName;
			}else{
				precintName = precintName + org[i];
			}
		}
		return precintName;
	}
	
	
	
	private String getDemocrateVotersFromCode(String geoJsonCode){
		String democraticVotes = "";
		int index = getPropertyInt(geoJsonCode,"DemocratVoters" );//this index is the index where the value is
		char[] org = geoJsonCode.toCharArray();
		for(int i = index;i<org.length;i++){
			if(org[i]=='"'){
				return democraticVotes;
			}else{
				democraticVotes = democraticVotes + org[i];
			}
		}
		return democraticVotes;
	}
	private String getTotalVotersFromCode(String geoJsonCode){
		String totalVotes = "";
		int index = getPropertyInt(geoJsonCode,"TotalNumberOfVoters");//this index is the index where the value is
		char[] org = geoJsonCode.toCharArray();
		for(int i = index;i<org.length;i++){
			if(org[i]=='"'){
				return totalVotes;
			}else{
				totalVotes=totalVotes + org[i];
			}
		}
		return totalVotes;
		
	}
	private String getRepublicanVotersFromCode(String geoJsonCode){
		String republicanVotes = "";
		int index = getPropertyInt(geoJsonCode,"RepublicanVoters");//this index is the index where the value is
		char[] org = geoJsonCode.toCharArray();
		for(int i = index;i<org.length;i++){
			if(org[i]=='"'){
				return republicanVotes;
			}else{
				republicanVotes = republicanVotes + org[i];
			}
		}
		return republicanVotes;
		
	}
	
	
	/***
	 * 
	 * @param GeoJsonCode
	 * @return
	 */
	/*
	public ArrayList<String> getLongLatIntoArray(String GeoJsonCode){
		char[] geoJsonAsChar = GeoJsonCode.toCharArray();
		ArrayList<String> LongLatList = new ArrayList<String>();
		String LongAndLat = "";
		//int startingIndex =  getPropertyInt(GeoJsonCode, "coordinates");
		for(int i = 0; i<geoJsonAsChar.length;i++){
			System.out.println("running " + i);
			char valueInCode = geoJsonAsChar[i];
			if(geoJsonAsChar[i]=='['&&geoJsonAsChar[i+1] != '['){
				while(geoJsonAsChar[i]!=','){
					LongAndLat = LongAndLat + geoJsonAsChar[i];
				}
				LongLatList.add(LongAndLat);
				System.out.println("Added " + LongAndLat);
				LongAndLat = "";
			}else if(geoJsonAsChar[i]=='['&&geoJsonAsChar[i+1] == '['){
				
			}else if(geoJsonAsChar[i]==']'&&geoJsonAsChar[i+1] == ']'){
				return LongLatList;
			}else{
				System.out.println("I messed up on line index: " + i);
			}
		}
		
		
		return LongLatList;
	}
	*/
	
	public ArrayList<String> getLongLatIntoArray(char[] StringChar){
		//4136
		ArrayList<String> longLat = new ArrayList<String>();
					for(int j = 0;j<StringChar.length;j++){
						if(j+2<=StringChar.length &&StringChar[j]=='[' && StringChar[j+1]=='-' ){
							String lat = "";
							String lon = "";
							j++;
							while(StringChar[j] != ','){
								lat = lat + StringChar[j];
								j++;

							}
							longLat.add(lat);
							if(StringChar[j]==','){
								j++;
								while(StringChar[j] != ']'){
									lon = lon + StringChar[j];
									j++;
								}
								longLat.add(lon);
							}
							//System.out.println(i);
							//System.out.println("LONG " + lon);
							//System.out.println("LAT " + lat);
						}
					}
					return longLat;

				}
			
	
	
	
	
	
	/**
	 * finds the starting index of where the value of the parameter given is
	 * Check this one to make sure it works
	 * @param geoJsonCode
	 * @return
	 */
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
	private void addTotalVotes(String totvotes){
		int totalVotes;
		totalVotes = Integer.parseInt(totvotes);
		
		TotalNumberOfVotes = TotalNumberOfVotes + totalVotes;
	}
	
	
	public int getTotalNumberOfVotes() {
		return TotalNumberOfVotes;
	}
	
	
	
	public ArrayList<Precints> getPrecintList() {
		return precintList;
	}


	
}
