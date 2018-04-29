import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int x = generateRandomNumberGivenMax(10);
		String demString = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Aitkin\",\"PrecinctID\":\"270010005\",\"County\":\"Aitkin\",\"CountyID\":\"1\",\"CongDist\":\"8\",\"MNSenDist\":\"10\",\"MNLegDist\":\"10B\",\"CtyComDist\":\"1\",\"party\":\"\",\"TotalNumberOfVoters\":\"1000\",\"RepublicanVoters\":\"491\",\"DemocratVoters\":\"470\",\"AdjacentPrecints\":\"Aitkin Twp,Spencer Twp\"},\"geometry";
		String repString = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Aitkin\",\"PrecinctID\":\"270010005\",\"County\":\"Aitkin\",\"CountyID\":\"1\",\"CongDist\":\"8\",\"MNSenDist\":\"10\",\"MNLegDist\":\"10B\",\"CtyComDist\":\"1\",\"party\":\"Republican\",\"TotalNumberOfVoters\":\"1000\",\"RepublicanVoters\":\"491\",\"DemocratVoters\":\"470\",\"AdjacentPrecints\":\"Aitkin Twp,Spencer Twp\"},\"geometry";
		String tracyString = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Tracy\",\"PrecinctID\":\"270830170\",\"County\":\"Lyon\",\"CountyID\":\"42\",\"CongDist\":\"7\",\"MNSenDist\":\"22\",\"MNLegDist\":\"22A\",\"CtyComDist\":\"5\",\"party\":\"Democrat\",\"TotalNumberOfVoters\":\"928\",\"RepublicanVoters\":\"410\",\"DemocratVoters\":\"487\",\"AdjacentPrecints\":\"Monroe Twp\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\",";
		String GroupString = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Aitkin\",\"PrecinctID\":\"270010005\",\"County\":\"Aitkin\",\"CountyID\":\"1\",\"CongDist\":\"8\",\"MNSenDist\":\"10\",\"MNLegDist\":\"10B\",\"CtyComDist\":\"1\",\"party\":\"\",\"group\":\"\",\"TotalNumberOfVoters\":\"1000\",\"RepublicanVoters\":\"491\",\"DemocratVoters\":\"470\",\"AdjacentPrecints\":\"Aitkin Twp,Spencer Twp\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\"";
		char[] orgDem = demString.toCharArray();
		String Republican = "Republican";
		String Democrat = "Democrat";
		int index = getPropertyInt(demString, "party");
		
		writeGroupToGeoJson(GroupString, "6", "group");
		//String y = insertWinningPrecint(tracyString, Republican);
		//String x = insertWinningPrecint(demString, Republican);
		//System.out.println("1: " + x);
		//System.out.println(orgDem[index]); //This is the second " 
		//writeWinnersToGeoJson(demString, Republican);
	}
	public static String writeGroupToGeoJson(String geoJson, String value, String propertyName){
		String returnString = "";
		char[] org = geoJson.toCharArray();
		char[] insertValue = value.toCharArray();
		char[] newString = new char[geoJson.length() + value.length()];
		int index = getPropertyInt(geoJson, propertyName);
		System.out.println(org[index]);
		System.arraycopy(org, 0, newString, 0, index);
		returnString = String.valueOf(newString);
		System.out.println(returnString);
		System.arraycopy(insertValue, 0, newString, index, insertValue.length);
		returnString = String.valueOf(newString);
		System.out.println(returnString);
		System.arraycopy(org, index, newString, index+insertValue.length, org.length-index);
		returnString = String.valueOf(newString);
		System.out.println(returnString);
		return returnString;
	}
	
	
	
	
	public static void writeWinnersToGeoJson(String geoJson, String winningParty){
		char[] org = geoJson.toCharArray();
		char[] insertString = winningParty.toCharArray();
		char[] newString = new char[geoJson.length() + insertString.length];
		int index = getPropertyInt(geoJson, "party");
		System.arraycopy(org, 0, newString, 0, index);
		String test = String.valueOf(newString);
		System.out.println(test);
		System.arraycopy(insertString, 0, newString, index, insertString.length);
		String test1 = String.valueOf(newString);
		System.out.println(test1);
		System.arraycopy(org, index, newString, index+insertString.length, org.length-index);
		String test2 = String.valueOf(newString);
		System.out.println(test2);
		
	}
	/*
	private static String insertWinningPrecint(String geoJsonString, String Winner){
		char[] org = geoJsonString.toCharArray();
		int index = getPropertyInt(geoJsonString, "party");
		char[] repChar = "Republican".toCharArray();
		char[] demChar = "Democrat".toCharArray();
		String returnString = null;
		if(Winner == "Republican"){
			if(org[index]=='D'){//if winnder is repbulican and the code is Democrat
				//Change it to democrat
				//Democrat = 8 chars
				//Republican = 10 chars
				System.out.println("Winner is repbulican but geoJson is demcrat so we need to change it to Republican");
				char[] newStringAsChar = new char[org.length+1];
				
				System.arraycopy(org, 0, newStringAsChar, 0, index-1);
				returnString = String.valueOf(newStringAsChar);
				System.out.println(returnString);
				
				System.arraycopy(repChar, 0, newStringAsChar, index-1, repChar.length);
				returnString = String.valueOf(newStringAsChar);
				System.out.println(returnString);
				int length  = org.length-index-8;
				System.arraycopy(org, index+8, newStringAsChar, index+9,length);//Length has to be 133
				//Length of org = 326
				//length of newString as char = 
				//index = 186
				returnString = String.valueOf(newStringAsChar);
				System.out.println(returnString);
			}
	
		}else if(Winner == "Democrat"){
			if(org[index]=='R'){
				//Change it to Republic
				System.out.println("Winner is democrat, but geoJson is Republican, so we need to change it to democrat");
				char[] newStringAsChar = new char[org.length-2];
				
				System.arraycopy(org, 0, newStringAsChar, 0, index-1);
				returnString = String.valueOf(newStringAsChar);
				System.out.println(returnString);
				
				System.arraycopy(demChar, 0, newStringAsChar, index-1, demChar.length);
				returnString = String.valueOf(newStringAsChar);
				System.out.println(returnString);
				int length  = org.length-index-10;
				System.arraycopy(org, index+10, newStringAsChar, index+7,length);//Length has to be 133

				returnString = String.valueOf(newStringAsChar);
				System.out.println(returnString);
				
			}
		}
		return returnString;
		
	}
*/
	private static int getPropertyInt(String geoJsonCode, String property){
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
}
