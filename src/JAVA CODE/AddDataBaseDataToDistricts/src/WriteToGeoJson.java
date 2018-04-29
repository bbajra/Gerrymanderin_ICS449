import java.util.ArrayList;

public class WriteToGeoJson {
	private DataBaseConnection conn;
	private ArrayList<String> originalArrayListOfString;
	private ArrayList<String> newArrayList = new ArrayList<String>();
	private String typeOfVote;
	
	
	
	public WriteToGeoJson(DataBaseConnection conn, ArrayList<String> org, String typeOfvote){
		this.conn = conn;
		this.originalArrayListOfString = org;
		this.typeOfVote = typeOfvote;
		setToNewArrayList();
	}
	
	/**
	 * Write to a new ArrayList the new lines of code
	 */
	public void setToNewArrayList(){
		String dem = "DemocratVoters";
		String rep = "RepublicanVoters";
		String totVotes = "TotalNumberOfVoters";
		for(int i = 0;i<originalArrayListOfString.size();i++){
			String LineInGeoJson = originalArrayListOfString.get(i);
			//This is just to diffeerntiat data from non important lines
			if(LineInGeoJson.contains("TotalNumberOfVoters")){
				char[] arrayOfLineInGeoJson = LineInGeoJson.toCharArray();
				//String precintName = getPrecintName(arrayOfLineInGeoJson);
				//conn.setPopulations(precintName, typeOfVote);//sets the conn object to get database
				String precintCode = getPrecintCode(arrayOfLineInGeoJson);
				conn.setPopulations(precintCode, typeOfVote);
				int demIndex = findInsertIndex(arrayOfLineInGeoJson, dem);
				char[] demVotes = conn.getDemocratVoters().toCharArray();
				char[] addedDemVotes = insertPopIntoGeoJsonString(arrayOfLineInGeoJson, demIndex, demVotes);
				int repIndex = findInsertIndex(addedDemVotes, rep);
				char[] repVotes = conn.getRepublicanVoters().toCharArray();
				char[] addedRepVotes = insertPopIntoGeoJsonString(addedDemVotes, repIndex, repVotes);
				int totVotesIndex = findInsertIndex(addedRepVotes, totVotes);
				char[] totVotesAsChar = conn.getTotalNumberOfVoters().toCharArray();
				char[] addedAllVotes = insertPopIntoGeoJsonString(addedRepVotes, totVotesIndex, totVotesAsChar);
				String FinalLine = new String(addedAllVotes);
				newArrayList.add(FinalLine);

			}else{
				newArrayList.add(LineInGeoJson);
			}
		}
	}
	
	
	/**
	 * Gets Precint Name from the line 
	 * @param org
	 * @return
	 */
	public String getPrecintName(char[] org){
		String precintName = "";
		for(int i = 44;i<org.length;i++){
			if(org[i]=='"'){
				return precintName;
			}else{
				precintName = precintName + org[i];
			}
		}
		return precintName;
		
	}
	/**
	 * returns precint code
	 * @param org
	 * @return
	 */
	public String getPrecintCode(char[] org){
		String GoeJsoncode = new String(org);
		int index = getPropertyInt(GoeJsoncode,"PrecinctID");
		String pctCode = "";
		for(int i = index;i<org.length;i++){
			if(org[i]=='"'){
				return pctCode;
			}else{
				pctCode = pctCode+ org[i];
			}
		}
		return pctCode;
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
	/**
	 * This method concats everything together
	 * @param originalString
	 * @param indexOfMove
	 * @param valueToBeInserted
	 */
	public char[] insertPopIntoGeoJsonString(char[] originalString, int indexOfMove, char[] valueToBeInserted){
		//This is the new char array that will hold everything
		char[] newStringWithValues = new char[originalString.length + valueToBeInserted.length];
		System.arraycopy(originalString, 0, newStringWithValues, 0, indexOfMove);//Copys the orignal array to the array
		//String x = new String(newStringWithValues);
		//System.out.println("This is the new String1: " );
		//System.out.println(x);
		System.arraycopy(valueToBeInserted, 0, newStringWithValues, indexOfMove, valueToBeInserted.length);//copys values to be inserted
		//String y = new String(newStringWithValues);
		//System.out.println("This is the new String2: " );
		//System.out.println(y);
		System.arraycopy(originalString, indexOfMove, newStringWithValues, indexOfMove+valueToBeInserted.length, originalString.length-indexOfMove);
		String z = new String(newStringWithValues);
		//System.out.println("This is the new String3: " );
		//System.out.println(z);
		return newStringWithValues;
	}
	/**
	 * Finds the index where the votes should be inserted
	 * @param org
	 * @param type
	 * @return
	 */
	public static int findInsertIndex(char[] org, String type){
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

	public ArrayList<String> getNewArrayList() {
		return newArrayList;
	}
}
