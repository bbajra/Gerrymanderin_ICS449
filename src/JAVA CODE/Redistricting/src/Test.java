import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static ArrayList<String> LongLatHolder = new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Aitkin\",\"PrecinctID\":\"270010005\",\"County\":\"Aitkin\",\"CountyID\":\"1\",\"CongDist\":\"8\",\"MNSenDist\":\"10\",\"MNLegDist\":\"10B\",\"CtyComDist\":\"1\",\"party\":\"Democrat\",\"TotalNumberOfVoters\":\"100\",\"RepublicanVoters\":\"49\",\"DemocratVoters\":\"47\"},\"geometry\":" ; 
		String a = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Balsam Twp\",\"PrecinctID\":\"270010020\",\"County\":\"Aitkin\",\"CountyID\":\"1\",\"CongDist\":\"8\",\"MNSenDist\":\"10\",\"MNLegDist\":\"10B\",\"CtyComDist\":\"5\",\"party\":\"Democrat\",\"TotalNumberOfVoters\":\"\",\"RepublicanVoters\":\"\",\"DemocratVoters\":\"\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-93.0598,46.853],[-93.081,46.8528],[-93.1233,46.8531],[-93.1252,46.8532],[-93.1651,46.8533],[-93.1893,46.8532],[-93.1899,46.8241],[-93.19,46.81],[-93.1907,46.7955],[-93.1908,46.781],[-93.1914,46.7668],[-93.1666,46.7666],[-93.1456,46.7668],[-93.1253,46.7663],[-93.1247,46.7663],[-93.1037,46.7662],[-93.0827,46.7661],[-93.0616,46.7666],[-93.061,46.7808],[-93.0611,46.7955],[-93.0604,46.8241],[-93.0598,46.853]]]}},";
		//Needs total Long and lat ex: [-93.1914,46.7668]
		String b = "[-93.1914,46.7668]";
		/*
		if(a.contains(b) ){
			System.out.println("true");
		}else{
			System.out.println("False");
		}
		*/
		//char[] y = x.toCharArray();
		//String z = getPrecintCode(y);
		//System.out.println(z);
		putintoArray3(x);
		
		for(int i = 0;i<LongLatHolder.size();i++){
			System.out.println("[" + i + "] " + LongLatHolder.get(i));
		}
	}
	/**
	 * input would be a charp[]
	 */
	public void putIntoArray6(char[] StringChar){
		//4136
					for(int j = 0;j<StringChar.length;j++){
						if(j+2<=StringChar.length &&StringChar[j]=='[' && StringChar[j+1]=='-' ){
							String lat = "";
							String lon = "";
							j++;
							while(StringChar[j] != ','){
								lat = lat + StringChar[j];
								j++;

							}
							precintList.add(lat);
							if(StringChar[j]==','){
								j++;
								while(StringChar[j] != ']'){
									lon = lon + StringChar[j];
									j++;
								}
								precintList.add(lon);
							}
							System.out.println(i);
							//System.out.println("LONG " + lon);
							//System.out.println("LAT " + lat);
						}
					}

				}
			}

	
	
	
	
	
	
	public static void putintoArray3(String x){
		Scanner scan = new Scanner(x);
		while(scan.hasNextLong()){
			float a = scan.nextLong();
			String b = String.valueOf(a);
			LongLatHolder.add(b);
			
		}
	}
	public static void putIntoArray2(String x){
		char[] StringChar = x.toCharArray();
		for(int j = 0;j<StringChar.length;j++){
			if(j+2<=StringChar.length &&StringChar[j]=='[' && StringChar[j+1]=='-' ){
				String lat = "";
				String lon = "";
				j++;
				while(StringChar[j] != ','){
					lat = lat + StringChar[j];
					j++;

				}
				LongLatHolder.add(lat);
				if(StringChar[j]==','){
					j++;
					while(StringChar[j] != ']'){
						lon = lon + StringChar[j];
						j++;
					}
					LongLatHolder.add(lon);
					
				}
				//System.out.println("LONG " + lon);
				//System.out.println("LAT " + lat);
			}
		}
	}
	
	
	/**
	 * 
	 * This gets the long and lat from the string and puts it  into an array
	 */
	public void putIntoArray(){
		//4136
				for(int i = 0;i<unModed.size();i++){//start at i = 10, because i =11 has the code
					String x = unModed.get(i);
					char[] StringChar = x.toCharArray();
					for(int j = 0;j<StringChar.length;j++){
						if(j+2<=StringChar.length &&StringChar[j]=='[' && StringChar[j+1]=='-' ){
							LongLatHolder obj= new LongLatHolder();
							String lat = "";
							String lon = "";
							j++;
							while(StringChar[j] != ','){
								lat = lat + StringChar[j];
								j++;

							}
							obj.setX(lat);
							if(StringChar[j]==','){
								j++;
								while(StringChar[j] != ']'){
									lon = lon + StringChar[j];
									j++;
								}
								obj.setY(lon);
							}
							LongLatHolder obj1 = Setparameters(obj,x);
							longLatHolderList.add(obj1);
							System.out.println(i);
							//System.out.println("LONG " + lon);
							//System.out.println("LAT " + lat);
						}
					}

				}
			}

	/**
	 * returns precint code
	 * @param org
	 * @return
	 */
	public static String getPrecintCode(char[] org){
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
