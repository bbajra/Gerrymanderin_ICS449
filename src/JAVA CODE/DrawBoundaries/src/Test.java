import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		
		ArrayList<LongLatHolder> list = new ArrayList<LongLatHolder>();

		String x = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Kelliher Twp\",\"PrecinctID\":\"270070125\",\"County\":\"Beltrami\",\"CountyID\":\"4\",\"CongDist\":\"7\",\"MNSenDist\":\"2\",\"MNLegDist\":\"2A\",\"CtyComDist\":\"4\",\"party\":\"Democrat\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[["
				+ "[-94.4236,48.0198],"
				+ "[-94.4397,48.0196],"
				+ "[-94.4452,48.0197],"
				+ "[-94.4609,48.0192],"
				+ "[-94.4829,48.019],"
				+ "[-94.5003,48.0189],"
				+ "[-94.5042,48.0188],"
				+ "[-94.5314,48.019],"
				+ "[-94.547,48.0192],"
				+ "[-94.5467,47.9907],"
				+ "[-94.5468,47.9761],"
				+ "[-94.5467,47.9617],"
				+ "[-94.5469,47.9472],"
				+ "[-94.5473,47.9328],"
				+ "[-94.5261,47.9327],"
				+ "[-94.5044,47.9328],"
				+ "[-94.5003,47.9328],"
				//+ "[-94.4828,47.9328],"
				//+ "[-94.461,47.9329],"
				+ "[-94.4605,47.9547],"
				+ "[-94.4284,47.9549],"
				+ "[-94.4283,47.933],"
				+ "[-94.4175,47.9331],"
				+ "[-94.4175,47.9476],"
				+ "[-94.4173,47.9622],"
				+ "[-94.4174,47.9767],"
				+ "[-94.4176,47.9914],"
				+ "[-94.4176,48.0],[-94.4177,48.0059],[-94.4178,48.0198],[-94.4236,48.0198]]]}}";
		String y = "{\"type\":\"Feature\",\"properties\":{\"Precinct\":\"Shooks Twp\",\"PrecinctID\":\"270070210\",\"County\":\"Beltrami\",\"CountyID\":\"4\",\"CongDist\":\"8\",\"MNSenDist\":\"2\",\"MNLegDist\":\"2A\",\"CtyComDist\":\"4\",\"party\":\"Democrat\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[["
				+ "[-94.461,47.9329],"
				+ "[-94.4828,47.9328],"
				+ "[-94.5003,47.9328],"
				+ "[-94.5044,47.9328],"
				+ "[-94.5261,47.9327],"
				+ "[-94.5473,47.9328],"
				+ "[-94.5474,47.9181],"
				+ "[-94.5479,47.889],"
				+ "[-94.548,47.8747],"
				+ "[-94.5483,47.8602],"
				+ "[-94.5484,47.8456],"
				+ "[-94.5046,47.8457],"
				+ "[-94.5002,47.8457],"
				+ "[-94.4832,47.8454],"
				+ "[-94.4403,47.8452],"
				+ "[-94.4296,47.8452],"
				+ "[-94.4187,47.8459],"
				+ "[-94.4188,47.86],"
				+ "[-94.418,47.8886],"
				+ "[-94.4177,47.903],"
				+ "[-94.4175,47.9175],"
				+ "[-94.4175,47.9331],"
				+ "[-94.461,47.9329]]]}},";
		char[] x1 = x.toCharArray();
		char[] y1 = y.toCharArray();
		for(int j = 0;j<x1.length;j++){
			if(j+2<=x1.length &&x1[j]=='[' && x1[j+1]=='-' ){
				LongLatHolder obj= new LongLatHolder();
				String lat = "";
				String lon = "";
				j++;
				while(x1[j] != ','){
					lat = lat + x1[j];
					j++;

				}
				System.out.println(lat);
				obj.setX(lat);
				if(x1[j]==','){
					j++;
					while(x1[j] != ']'){
						lon = lon + x1[j];
						j++;
					}
					System.out.println(lon);
					System.out.println("");
					obj.setY(lon);

				}
				LongLatHolder obj1 = Setparameters(obj,x);
				list.add(obj1);

				//System.out.println("LONG " + lon);
				//System.out.println("LAT " + lat);
			}
		}
		for(int i = 0;i<list.size();i++){
			LongLatHolder obj2 = list.get(i);
			obj2.printAll();
		}
	
	}
	
	
	public static LongLatHolder Setparameters(LongLatHolder obj, String x){
		//8 cong dist
		if(x.contains("\"CongDist\":\"8\"")){
			obj.setCongDist("8");
		}else if((x.contains("\"CongDist\":\"7\""))){
			obj.setCongDist("7");
		}else if((x.contains("\"CongDist\":\"6\""))){
			obj.setCongDist("6");
		}else if((x.contains("\"CongDist\":\"5\""))){
			obj.setCongDist("5");
		}else if((x.contains("\"CongDist\":\"4\""))){
			obj.setCongDist("4");
		}else if((x.contains("\"CongDist\":\"3\""))){
			obj.setCongDist("3");
		}else if((x.contains("\"CongDist\":\"2\""))){
			obj.setCongDist("2");
		}else if((x.contains("\"CongDist\":\"1\""))){
			obj.setCongDist("1");
		}
		//87 counties
		if(x.contains("\"CountyID\":\"1\"")){
			obj.setCountyID("1");
		}else if((x.contains("\"CountyID\":\"2\""))){
			obj.setCountyID("2");
		}else if((x.contains("\"CountyID\":\"3\""))){
			obj.setCountyID("3");
		}else if((x.contains("\"CountyID\":\"4\""))){
			obj.setCountyID("4");
		}else if((x.contains("\"CountyID\":\"5\""))){
			obj.setCountyID("5");
		}else if((x.contains("\"CountyID\":\"6\""))){
			obj.setCountyID("6");
		}else if((x.contains("\"CountyID\":\"7\""))){
			obj.setCountyID("7");
		}else if((x.contains("\"CountyID\":\"8\""))){
			obj.setCountyID("8");
		}else if((x.contains("\"CountyID\":\"9\""))){
			obj.setCountyID("9");
		}else if((x.contains("\"CountyID\":\"10\""))){
			obj.setCountyID("10");
		}else if((x.contains("\"CountyID\":\"11\""))){
			obj.setCountyID("11");
		}else if((x.contains("\"CountyID\":\"12\""))){
			obj.setCountyID("12");
		}else if((x.contains("\"CountyID\":\"13\""))){
			obj.setCountyID("13");
		}else if((x.contains("\"CountyID\":\"14\""))){
			obj.setCountyID("14");
		}else if((x.contains("\"CountyID\":\"15\""))){
			obj.setCountyID("15");
		}else if((x.contains("\"CountyID\":\"16\""))){
			obj.setCountyID("16");
		}else if((x.contains("\"CountyID\":\"17\""))){
			obj.setCountyID("17");
		}else if((x.contains("\"CountyID\":\"18\""))){
			obj.setCountyID("18");
		}else if((x.contains("\"CountyID\":\"19\""))){
			obj.setCountyID("19");
		}else if((x.contains("\"CountyID\":\"20\""))){
			obj.setCountyID("20");
		}else if((x.contains("\"CountyID\":\"21\""))){
			obj.setCountyID("21");
		}else if((x.contains("\"CountyID\":\"22\""))){
			obj.setCountyID("22");
		}else if((x.contains("\"CountyID\":\"23\""))){
			obj.setCountyID("23");
		}else if((x.contains("\"CountyID\":\"24\""))){
			obj.setCountyID("24");
		}else if((x.contains("\"CountyID\":\"25\""))){
			obj.setCountyID("25");
		}else if((x.contains("\"CountyID\":\"26\""))){
			obj.setCountyID("26");
		}else if((x.contains("\"CountyID\":\"27\""))){
			obj.setCountyID("27");
		}else if((x.contains("\"CountyID\":\"28\""))){
			obj.setCountyID("28");
		}else if((x.contains("\"CountyID\":\"29\""))){
			obj.setCountyID("29");
		}else if((x.contains("\"CountyID\":\"30\""))){
			obj.setCountyID("30");
		}else if((x.contains("\"CountyID\":\"31\""))){
			obj.setCountyID("31");
		}else if((x.contains("\"CountyID\":\"32\""))){
			obj.setCountyID("32");
		}else if((x.contains("\"CountyID\":\"33\""))){
			obj.setCountyID("33");
		}else if((x.contains("\"CountyID\":\"34\""))){
			obj.setCountyID("34");
		}else if((x.contains("\"CountyID\":\"35\""))){
			obj.setCountyID("35");
		}else if((x.contains("\"CountyID\":\"36\""))){
			obj.setCountyID("36");
		}else if((x.contains("\"CountyID\":\"37\""))){
			obj.setCountyID("37");
		}else if((x.contains("\"CountyID\":\"38\""))){
			obj.setCountyID("38");
		}else if((x.contains("\"CountyID\":\"39\""))){
			obj.setCountyID("39");
		}else if((x.contains("\"CountyID\":\"40\""))){
			obj.setCountyID("40");
		}else if((x.contains("\"CountyID\":\"41\""))){
			obj.setCountyID("41");
		}else if((x.contains("\"CountyID\":\"42\""))){
			obj.setCountyID("42");
		}else if((x.contains("\"CountyID\":\"43\""))){
			obj.setCountyID("43");
		}else if((x.contains("\"CountyID\":\"44\""))){
			obj.setCountyID("44");
		}else if((x.contains("\"CountyID\":\"45\""))){
			obj.setCountyID("45");
		}else if((x.contains("\"CountyID\":\"46\""))){
			obj.setCountyID("46");
		}else if((x.contains("\"CountyID\":\"47\""))){
			obj.setCountyID("47");
		}else if((x.contains("\"CountyID\":\"48\""))){
			obj.setCountyID("48");
		}else if((x.contains("\"CountyID\":\"49\""))){
			obj.setCountyID("49");
		}else if((x.contains("\"CountyID\":\"50\""))){
			obj.setCountyID("50");
		}else if((x.contains("\"CountyID\":\"51\""))){
			obj.setCountyID("51");
		}else if((x.contains("\"CountyID\":\"52\""))){
			obj.setCountyID("52");
		}else if((x.contains("\"CountyID\":\"53\""))){
			obj.setCountyID("53");
		}else if((x.contains("\"CountyID\":\"54\""))){
			obj.setCountyID("54");
		}else if((x.contains("\"CountyID\":\"55\""))){
			obj.setCountyID("55");
		}else if((x.contains("\"CountyID\":\"56\""))){
			obj.setCountyID("56");
		}else if((x.contains("\"CountyID\":\"57\""))){
			obj.setCountyID("57");
		}else if((x.contains("\"CountyID\":\"58\""))){
			obj.setCountyID("58");
		}else if((x.contains("\"CountyID\":\"59\""))){
			obj.setCountyID("59");
		}else if((x.contains("\"CountyID\":\"60\""))){
			obj.setCountyID("60");
		}else if((x.contains("\"CountyID\":\"61\""))){
			obj.setCountyID("61");
		}else if((x.contains("\"CountyID\":\"62\""))){
			obj.setCountyID("62");
		}else if((x.contains("\"CountyID\":\"63\""))){
			obj.setCountyID("63");
		}else if((x.contains("\"CountyID\":\"64\""))){
			obj.setCountyID("64");
		}else if((x.contains("\"CountyID\":\"65\""))){
			obj.setCountyID("65");
		}else if((x.contains("\"CountyID\":\"66\""))){
			obj.setCountyID("66");
		}else if((x.contains("\"CountyID\":\"67\""))){
			obj.setCountyID("67");
		}else if((x.contains("\"CountyID\":\"68\""))){
			obj.setCountyID("68");
		}else if((x.contains("\"CountyID\":\"69\""))){
			obj.setCountyID("69");
		}else if((x.contains("\"CountyID\":\"70\""))){
			obj.setCountyID("70");
		}else if((x.contains("\"CountyID\":\"71\""))){
			obj.setCountyID("71");
		}else if((x.contains("\"CountyID\":\"72\""))){
			obj.setCountyID("72");
		}else if((x.contains("\"CountyID\":\"73\""))){
			obj.setCountyID("73");
		}else if((x.contains("\"CountyID\":\"74\""))){
			obj.setCountyID("74");
		}else if((x.contains("\"CountyID\":\"75\""))){
			obj.setCountyID("75");
		}else if((x.contains("\"CountyID\":\"76\""))){
			obj.setCountyID("76");
		}else if((x.contains("\"CountyID\":\"77\""))){
			obj.setCountyID("77");
		}else if((x.contains("\"CountyID\":\"78\""))){
			obj.setCountyID("78");
		}else if((x.contains("\"CountyID\":\"79\""))){
			obj.setCountyID("79");
		}else if((x.contains("\"CountyID\":\"80\""))){
			obj.setCountyID("80");
		}else if((x.contains("\"CountyID\":\"81\""))){
			obj.setCountyID("81");
		}else if((x.contains("\"CountyID\":\"82\""))){
			obj.setCountyID("82");
		}else if((x.contains("\"CountyID\":\"83\""))){
			obj.setCountyID("83");
		}else if((x.contains("\"CountyID\":\"84\""))){
			obj.setCountyID("84");
		}else if((x.contains("\"CountyID\":\"85\""))){
			obj.setCountyID("85");
		}else if((x.contains("\"CountyID\":\"86\""))){
			obj.setCountyID("86");
		}else if((x.contains("\"CountyID\":\"87\""))){
			obj.setCongDist("87");
		}
		//67 mn sen dist
		if(x.contains("\"MNSenDist\":\"1\",")){
			obj.setSenateDist("1");
		}else if((x.contains("\"MNSenDist\":\"2\","))){
			obj.setSenateDist("2");
		}else if((x.contains("\"MNSenDist\":\"3\","))){
			obj.setSenateDist("3");
		}else if((x.contains("\"MNSenDist\":\"4\","))){
			obj.setSenateDist("4");
		}else if((x.contains("\"MNSenDist\":\"5\","))){
			obj.setSenateDist("5");
		}else if((x.contains("\"MNSenDist\":\"6\","))){
			obj.setSenateDist("6");
		}else if((x.contains("\"MNSenDist\":\"7\","))){
			obj.setSenateDist("7");
		}else if((x.contains("\"MNSenDist\":\"8\","))){
			obj.setSenateDist("8");
		}else if((x.contains("\"MNSenDist\":\"9\","))){
			obj.setSenateDist("9");
		}else if((x.contains("\"MNSenDist\":\"10\","))){
			obj.setSenateDist("10");
		}else if((x.contains("\"MNSenDist\":\"11\","))){
			obj.setSenateDist("11");
		}else if((x.contains("\"MNSenDist\":\"12\","))){
			obj.setSenateDist("12");
		}else if((x.contains("\"MNSenDist\":\"13\","))){
			obj.setSenateDist("13");
		}else if((x.contains("\"MNSenDist\":\"14\","))){
			obj.setSenateDist("14");
		}else if((x.contains("\"MNSenDist\":\"15\","))){
			obj.setSenateDist("15");
		}else if((x.contains("\"MNSenDist\":\"16\","))){
			obj.setSenateDist("16");
		}else if((x.contains("\"MNSenDist\":\"17\","))){
			obj.setSenateDist("17");
		}else if((x.contains("\"MNSenDist\":\"18\","))){
			obj.setSenateDist("18");
		}else if((x.contains("\"MNSenDist\":\"19\","))){
			obj.setSenateDist("19");
		}else if((x.contains("\"MNSenDist\":\"20\","))){
			obj.setSenateDist("20");
		}else if((x.contains("\"MNSenDist\":\"21\","))){
			obj.setSenateDist("21");
		}else if((x.contains("\"MNSenDist\":\"22\","))){
			obj.setSenateDist("22");
		}else if((x.contains("\"MNSenDist\":\"23\","))){
			obj.setSenateDist("23");
		}else if((x.contains("\"MNSenDist\":\"24\","))){
			obj.setSenateDist("24");
		}else if((x.contains("\"MNSenDist\":\"25\","))){
			obj.setSenateDist("25");
		}else if((x.contains("\"MNSenDist\":\"26\","))){
			obj.setSenateDist("26");
		}else if((x.contains("\"MNSenDist\":\"27\","))){
			obj.setSenateDist("27");
		}else if((x.contains("\"MNSenDist\":\"28\","))){
			obj.setSenateDist("28");
		}else if((x.contains("\"MNSenDist\":\"29\","))){
			obj.setSenateDist("29");
		}else if((x.contains("\"MNSenDist\":\"30\","))){
			obj.setSenateDist("30");
		}else if((x.contains("\"MNSenDist\":\"31\","))){
			obj.setSenateDist("31");
		}else if((x.contains("\"MNSenDist\":\"32\","))){
			obj.setSenateDist("32");
		}else if((x.contains("\"MNSenDist\":\"33\","))){
			obj.setSenateDist("33");
		}else if((x.contains("\"MNSenDist\":\"34\","))){
			obj.setSenateDist("34");
		}else if((x.contains("\"MNSenDist\":\"35\","))){
			obj.setSenateDist("35");
		}else if((x.contains("\"MNSenDist\":\"36\","))){
			obj.setSenateDist("36");
		}else if((x.contains("\"MNSenDist\":\"37\","))){
			obj.setSenateDist("37");
		}else if((x.contains("\"MNSenDist\":\"38\","))){
			obj.setSenateDist("38");
		}else if((x.contains("\"MNSenDist\":\"39\","))){
			obj.setSenateDist("39");
		}else if((x.contains("\"MNSenDist\":\"40\","))){
			obj.setSenateDist("40");
		}else if((x.contains("\"MNSenDist\":\"41\","))){
			obj.setSenateDist("41");
		}else if((x.contains("\"MNSenDist\":\"42\","))){
			obj.setSenateDist("42");
		}else if((x.contains("\"MNSenDist\":\"43\","))){
			obj.setSenateDist("43");
		}else if((x.contains("\"MNSenDist\":\"44\","))){
			obj.setSenateDist("44");
		}else if((x.contains("\"MNSenDist\":\"45\","))){
			obj.setSenateDist("45");
		}else if((x.contains("\"MNSenDist\":\"46\","))){
			obj.setSenateDist("46");
		}else if((x.contains("\"MNSenDist\":\"47\","))){
			obj.setSenateDist("47");
		}else if((x.contains("\"MNSenDist\":\"48\","))){
			obj.setSenateDist("48");
		}else if((x.contains("\"MNSenDist\":\"49\","))){
			obj.setSenateDist("49");
		}else if((x.contains("\"MNSenDist\":\"50\","))){
			obj.setSenateDist("50");
		}else if((x.contains("\"MNSenDist\":\"51\","))){
			obj.setSenateDist("51");
		}else if((x.contains("\"MNSenDist\":\"52\","))){
			obj.setSenateDist("52");
		}else if((x.contains("\"MNSenDist\":\"53\","))){
			obj.setSenateDist("53");
		}else if((x.contains("\"MNSenDist\":\"54\","))){
			obj.setSenateDist("54");
		}else if((x.contains("\"MNSenDist\":\"55\","))){
			obj.setSenateDist("55");
		}else if((x.contains("\"MNSenDist\":\"56\","))){
			obj.setSenateDist("56");
		}else if((x.contains("\"MNSenDist\":\"57\","))){
			obj.setSenateDist("57");
		}else if((x.contains("\"MNSenDist\":\"58\","))){
			obj.setSenateDist("58");
		}else if((x.contains("\"MNSenDist\":\"59\","))){
			obj.setSenateDist("59");
		}else if((x.contains("\"MNSenDist\":\"60\","))){
			obj.setSenateDist("60");
		}else if((x.contains("\"MNSenDist\":\"61\","))){
			obj.setSenateDist("61");
		}else if((x.contains("\"MNSenDist\":\"62\","))){
			obj.setSenateDist("62");
		}else if((x.contains("\"MNSenDist\":\"63\","))){
			obj.setSenateDist("63");
		}else if((x.contains("\"MNSenDist\":\"64\","))){
			obj.setSenateDist("64");
		}else if((x.contains("\"MNSenDist\":\"65\","))){
			obj.setSenateDist("65");
		}else if((x.contains("\"MNSenDist\":\"66\","))){
			obj.setSenateDist("66");
		}else if((x.contains("\"MNSenDist\":\"67\","))){
			obj.setSenateDist("67");
		}


		//mn legDist 1A-67B
		if(x.contains("MNLegDist\":\"1A")){
			obj.setLegDist("1A");
		}else if(x.contains("MNLegDist\":\"2B")){
			obj.setLegDist("2B");
		}else if(x.contains("MNLegDist\":\"3A")){
			obj.setLegDist("3A");
		}else if(x.contains("MNLegDist\":\"3B")){
			obj.setLegDist("3B");
		}else if(x.contains("MNLegDist\":\"4A")){
			obj.setLegDist("4A");
		}else if(x.contains("MNLegDist\":\"4B")){
			obj.setLegDist("4B");
		}else if(x.contains("MNLegDist\":\"5A")){
			obj.setLegDist("5A");
		}else if(x.contains("MNLegDist\":\"5B")){
			obj.setLegDist("5B");
		}else if(x.contains("MNLegDist\":\"6A")){
			obj.setLegDist("6A");
		}else if(x.contains("MNLegDist\":\"6B")){
			obj.setLegDist("6B");
		}else if(x.contains("MNLegDist\":\"7A")){
			obj.setLegDist("7A");
		}else if(x.contains("MNLegDist\":\"7B")){
			obj.setLegDist("7B");
		}else if(x.contains("MNLegDist\":\"8A")){
			obj.setLegDist("8A");
		}else if(x.contains("MNLegDist\":\"8B")){
			obj.setLegDist("8B");
		}else if(x.contains("MNLegDist\":\"9A")){
			obj.setLegDist("9A");
		}else if(x.contains("MNLegDist\":\"9B")){
			obj.setLegDist("9B");
		}else if(x.contains("MNLegDist\":\"10A")){
			obj.setLegDist("10A");
		}else if(x.contains("MNLegDist\":\"10B")){
			obj.setLegDist("10B");
		}else if(x.contains("MNLegDist\":\"11A")){
			obj.setLegDist("11A");
		}else if(x.contains("MNLegDist\":\"11B")){
			obj.setLegDist("11B");
		}else if(x.contains("MNLegDist\":\"12A")){
			obj.setLegDist("12A");
		}else if(x.contains("MNLegDist\":\"12B")){
			obj.setLegDist("12B");
		}else if(x.contains("MNLegDist\":\"13A")){
			obj.setLegDist("13A");
		}else if(x.contains("MNLegDist\":\"13B")){
			obj.setLegDist("13B");
		}else if(x.contains("MNLegDist\":\"14A")){
			obj.setLegDist("14A");
		}else if(x.contains("MNLegDist\":\"14B")){
			obj.setLegDist("14B");
		}else if(x.contains("MNLegDist\":\"15A")){
			obj.setLegDist("15A");
		}else if(x.contains("MNLegDist\":\"15B")){
			obj.setLegDist("15B");
		}else if(x.contains("MNLegDist\":\"16A")){
			obj.setLegDist("16A");
		}else if(x.contains("MNLegDist\":\"16B")){
			obj.setLegDist("16B");
		}else if(x.contains("MNLegDist\":\"17A")){
			obj.setLegDist("17A");
		}else if(x.contains("MNLegDist\":\"17B")){
			obj.setLegDist("17B");
		}else if(x.contains("MNLegDist\":\"18A")){
			obj.setLegDist("18A");
		}else if(x.contains("MNLegDist\":\"18B")){
			obj.setLegDist("18B");
		}else if(x.contains("MNLegDist\":\"19A")){
			obj.setLegDist("19A");
		}else if(x.contains("MNLegDist\":\"19B")){
			obj.setLegDist("19B");
		}else if(x.contains("MNLegDist\":\"20A")){
			obj.setLegDist("20A");
		}else if(x.contains("MNLegDist\":\"20B")){
			obj.setLegDist("20B");
		}else if(x.contains("MNLegDist\":\"21A")){
			obj.setLegDist("21A");
		}else if(x.contains("MNLegDist\":\"21B")){
			obj.setLegDist("21B");
		}else if(x.contains("MNLegDist\":\"22A")){
			obj.setLegDist("22A");
		}else if(x.contains("MNLegDist\":\"22B")){
			obj.setLegDist("22B");
		}else if(x.contains("MNLegDist\":\"23A")){
			obj.setLegDist("23A");
		}else if(x.contains("MNLegDist\":\"23B")){
			obj.setLegDist("23B");
		}else if(x.contains("MNLegDist\":\"24A")){
			obj.setLegDist("24A");
		}else if(x.contains("MNLegDist\":\"24B")){
			obj.setLegDist("24B");
		}else if(x.contains("MNLegDist\":\"25A")){
			obj.setLegDist("25A");
		}else if(x.contains("MNLegDist\":\"25B")){
			obj.setLegDist("25B");
		}else if(x.contains("MNLegDist\":\"26A")){
			obj.setLegDist("26A");
		}else if(x.contains("MNLegDist\":\"26B")){
			obj.setLegDist("26B");
		}else if(x.contains("MNLegDist\":\"27A")){
			obj.setLegDist("27A");
		}else if(x.contains("MNLegDist\":\"27B")){
			obj.setLegDist("27B");
		}else if(x.contains("MNLegDist\":\"28A")){
			obj.setLegDist("28A");
		}else if(x.contains("MNLegDist\":\"28B")){
			obj.setLegDist("28B");
		}else if(x.contains("MNLegDist\":\"29A")){
			obj.setLegDist("29A");
		}else if(x.contains("MNLegDist\":\"29B")){
			obj.setLegDist("29B");
		}else if(x.contains("MNLegDist\":\"30A")){
			obj.setLegDist("30A");
		}else if(x.contains("MNLegDist\":\"30B")){
			obj.setLegDist("30B");
		}else if(x.contains("MNLegDist\":\"31A")){
			obj.setLegDist("31A");
		}else if(x.contains("MNLegDist\":\"31B")){
			obj.setLegDist("31B");
		}else if(x.contains("MNLegDist\":\"32A")){
			obj.setLegDist("32A");
		}else if(x.contains("MNLegDist\":\"32B")){
			obj.setLegDist("32B");
		}else if(x.contains("MNLegDist\":\"33A")){
			obj.setLegDist("33A");
		}else if(x.contains("MNLegDist\":\"33B")){
			obj.setLegDist("33B");
		}else if(x.contains("MNLegDist\":\"34A")){
			obj.setLegDist("34A");
		}else if(x.contains("MNLegDist\":\"34B")){
			obj.setLegDist("34B");
		}else if(x.contains("MNLegDist\":\"35A")){
			obj.setLegDist("35A");
		}else if(x.contains("MNLegDist\":\"35B")){
			obj.setLegDist("35B");
		}else if(x.contains("MNLegDist\":\"36A")){
			obj.setLegDist("36A");
		}else if(x.contains("MNLegDist\":\"36B")){
			obj.setLegDist("36B");
		}else if(x.contains("MNLegDist\":\"37A")){
			obj.setLegDist("37A");
		}else if(x.contains("MNLegDist\":\"37B")){
			obj.setLegDist("37B");
		}else if(x.contains("MNLegDist\":\"38A")){
			obj.setLegDist("38A");
		}else if(x.contains("MNLegDist\":\"38B")){
			obj.setLegDist("38B");
		}else if(x.contains("MNLegDist\":\"39A")){
			obj.setLegDist("39A");
		}else if(x.contains("MNLegDist\":\"39B")){
			obj.setLegDist("39B");
		}else if(x.contains("MNLegDist\":\"40A")){
			obj.setLegDist("40A");
		}else if(x.contains("MNLegDist\":\"40B")){
			obj.setLegDist("40B");
		}else if(x.contains("MNLegDist\":\"41A")){
			obj.setLegDist("41A");
		}else if(x.contains("MNLegDist\":\"41B")){
			obj.setLegDist("41B");
		}else if(x.contains("MNLegDist\":\"42A")){
			obj.setLegDist("42A");
		}else if(x.contains("MNLegDist\":\"42B")){
			obj.setLegDist("42B");
		}else if(x.contains("MNLegDist\":\"43A")){
			obj.setLegDist("43A");
		}else if(x.contains("MNLegDist\":\"43B")){
			obj.setLegDist("43B");
		}else if(x.contains("MNLegDist\":\"44A")){
			obj.setLegDist("44A");
		}else if(x.contains("MNLegDist\":\"44B")){
			obj.setLegDist("44B");
		}else if(x.contains("MNLegDist\":\"45A")){
			obj.setLegDist("45A");
		}else if(x.contains("MNLegDist\":\"45B")){
			obj.setLegDist("45B");
		}else if(x.contains("MNLegDist\":\"46A")){
			obj.setLegDist("46A");
		}else if(x.contains("MNLegDist\":\"46B")){
			obj.setLegDist("46B");
		}else if(x.contains("MNLegDist\":\"47A")){
			obj.setLegDist("47A");
		}else if(x.contains("MNLegDist\":\"47B")){
			obj.setLegDist("47B");
		}else if(x.contains("MNLegDist\":\"48A")){
			obj.setLegDist("48A");
		}else if(x.contains("MNLegDist\":\"48B")){
			obj.setLegDist("48B");
		}else if(x.contains("MNLegDist\":\"49A")){
			obj.setLegDist("49A");
		}else if(x.contains("MNLegDist\":\"49B")){
			obj.setLegDist("49B");
		}else if(x.contains("MNLegDist\":\"50A")){
			obj.setLegDist("50A");
		}else if(x.contains("MNLegDist\":\"50B")){
			obj.setLegDist("50B");
		}else if(x.contains("MNLegDist\":\"51A")){
			obj.setLegDist("51A");
		}else if(x.contains("MNLegDist\":\"51B")){
			obj.setLegDist("51B");
		}else if(x.contains("MNLegDist\":\"52A")){
			obj.setLegDist("52A");
		}else if(x.contains("MNLegDist\":\"52B")){
			obj.setLegDist("52B");
		}else if(x.contains("MNLegDist\":\"53A")){
			obj.setLegDist("53A");
		}else if(x.contains("MNLegDist\":\"53B")){
			obj.setLegDist("53B");
		}else if(x.contains("MNLegDist\":\"54A")){
			obj.setLegDist("54A");
		}else if(x.contains("MNLegDist\":\"54B")){
			obj.setLegDist("54B");
		}else if(x.contains("MNLegDist\":\"55A")){
			obj.setLegDist("55A");
		}else if(x.contains("MNLegDist\":\"55B")){
			obj.setLegDist("55B");
		}else if(x.contains("MNLegDist\":\"56A")){
			obj.setLegDist("56A");
		}else if(x.contains("MNLegDist\":\"56B")){
			obj.setLegDist("56B");
		}else if(x.contains("MNLegDist\":\"57A")){
			obj.setLegDist("57A");
		}else if(x.contains("MNLegDist\":\"57B")){
			obj.setLegDist("57B");
		}else if(x.contains("MNLegDist\":\"58A")){
			obj.setLegDist("58A");
		}else if(x.contains("MNLegDist\":\"58B")){
			obj.setLegDist("58B");
		}else if(x.contains("MNLegDist\":\"59A")){
			obj.setLegDist("59A");
		}else if(x.contains("MNLegDist\":\"59B")){
			obj.setLegDist("59B");
		}else if(x.contains("MNLegDist\":\"60A")){
			obj.setLegDist("60A");
		}else if(x.contains("MNLegDist\":\"60B")){
			obj.setLegDist("60B");
		}else if(x.contains("MNLegDist\":\"61A")){
			obj.setLegDist("61A");
		}else if(x.contains("MNLegDist\":\"61B")){
			obj.setLegDist("61B");
		}else if(x.contains("MNLegDist\":\"62A")){
			obj.setLegDist("62A");
		}else if(x.contains("MNLegDist\":\"62B")){
			obj.setLegDist("62B");
		}else if(x.contains("MNLegDist\":\"63A")){
			obj.setLegDist("63A");
		}else if(x.contains("MNLegDist\":\"63B")){
			obj.setLegDist("63B");
		}else if(x.contains("MNLegDist\":\"64A")){
			obj.setLegDist("64A");
		}else if(x.contains("MNLegDist\":\"64B")){
			obj.setLegDist("64B");
		}else if(x.contains("MNLegDist\":\"65A")){
			obj.setLegDist("65A");
		}else if(x.contains("MNLegDist\":\"65B")){
			obj.setLegDist("65B");
		}

		return obj;

	}
}
