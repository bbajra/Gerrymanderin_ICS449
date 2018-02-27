import java.util.ArrayList;

public class Writter {
	private ArrayList<String> List = new ArrayList<String>();
	Writter(ArrayList<String> string){
		setList(string);
	}
	
	private void setList(ArrayList<String> list) {
		List = list;
	}
	/**
	 * 
	 * @param partyAfiiliation type dem or rep
	 * @param TypeOfMap get copy from geoJson
	 */
	public void writeToGeoJson(String partyAfiiliation, String TypeOfMap){
		for(int i = 0;i<List.size();i++){
			String JsonLine = List.get(i);
			if(JsonLine.contains(TypeOfMap)){
				if(partyAfiiliation =="dem"){
				JsonLine = JsonLine.replaceAll("\"party\":\"\"", "\"party\":\"Democrat\"");
				List.set(i, JsonLine);
				
				}else if(partyAfiiliation == "rep"){
				JsonLine = JsonLine.replaceAll("\"party\":\"\"", "\"party\":\"Republican\"");
				List.set(i, JsonLine);
				}
			}
		}
	}

	public ArrayList<String> getList() {
		return List;
	}
	
}
