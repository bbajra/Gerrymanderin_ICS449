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
		/**
         * Congressional District
         *//**
        changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"1\"");
        changeGeoJson.writeToGeoJson("rep", "\"CongDist\":\"2\"");
        changeGeoJson.writeToGeoJson("rep", "\"CongDist\":\"3\"");
        changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"4\"");
        changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"5\"");
        changeGeoJson.writeToGeoJson("rep", "\"CongDist\":\"6\"");
        changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"7\"");
        changeGeoJson.writeToGeoJson("dem", "\"CongDist\":\"8\"");
        */

        /**
         * Presidential district
         *//**
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Hennepin\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Ramsey\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Washington\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Dakota\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Olmsted\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Carlton\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"St. Louis\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Lake\"");
        changeGeoJson.writeToGeoJson("dem", "\"County\":\"Cook\"");
        */

        /**
         * MN Senate
         */
        /**
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"3\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"6\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"4\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"7\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"11\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"19\"");

        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"36\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"37\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"40\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"41\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"42\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"43\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"45\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"46\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"48\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"49\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"50\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"51\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"52\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"53\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"54\"");

        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"57\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"58\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"59\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"60\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"61\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"62\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"63\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"64\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"65\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"66\"");
        changeGeoJson.writeToGeoJson("dem", "\"MNSenDist\":\"67\"");
        */

        /**
         * MN House
         */
        String[] mnHouse={"3A", "3A", "3B", "4A", "4B", "6A", "6B", "7A",  "7B", "11A", "19A", "19B", "20B", "25B", "26A", "27B", "28A", "36B", "37A", "40A", "40B", "41A", "41B", "42B", "43A", "43B", "44B", "45A", "45B", "46A", "46B", "48A", "49B", "50A", "50B", "51A", "51B", "52A", "53A", "57A", "59A", "59B", "60A", "60B", "61A", "61B", "62A", "62B", "63A", "63B", "64A", "64B", "65A", "65B", "66A", "66B", "67A", "67B"};
        int i;
        for(i=0; i<mnHouse.length; i++){
            changeGeoJson.writeToGeoJson("dem", "\"MNLegDist\":\""+mnHouse[i]+"\"");
        }
		ArrayList<String> modifiedList = changeGeoJson.getList();
		parser.writeToFile(modifiedList);
		System.out.println("Completed");
		
		
		
	}

}
