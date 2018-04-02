import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteToGeoJson {

	public ArrayList<Precints> precintList;
	public ArrayList<Group> groupList;
	public WriteToGeoJson(ArrayList<Precints> precintList,ArrayList<Group> groupList){
		this.precintList = precintList;
		this.groupList = groupList;
		writeOutToFile();
	}
	
	
	public void writeOutToFile(){
		File file = new File("C:/Projects/ICS499/Redistricting/Data/ResultsText2.txt");
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.println("Writting out groups");
			for(int i = 0;i<groupList.size();i++){
				Group group = groupList.get(i);
				System.out.println("Printing group: " + i );
				pw.println("Printing group: " + i );
				ArrayList<Precints> groupPrecintList = group.getGroupedPrecintList();
				for(int j = 0;j<groupPrecintList.size();j++){
					Precints precint = groupPrecintList.get(j);
					precint.printAll();
					pw.println("Precint Name: " + precint.getPrecintName() + " Democratic Voters: " + precint.getDemVotes()
					+ " Republican Voters: " + precint.getRepVotes()+ " Total Number Of Voters: " + precint.getTotVotes()
					+ "Group: " + precint.getGroupNumber());
					pw.println();
					
				}
				System.out.println();
				pw.println();
			}
			
			
			for(int i = 0;i<precintList.size();i++){
				Precints precint = precintList.get(i);
				pw.println("Precint Name: " + precint.getPrecintName() + " Democratic Voters: " + precint.getDemVotes()
						+ " Republican Voters: " + precint.getRepVotes()+ " Total Number Of Voters: " + precint.getTotVotes()
						+ "Group: " + precint.getGroupNumber());
			}
			
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Something failed when writting");
			e.printStackTrace();
		}
	}
}
