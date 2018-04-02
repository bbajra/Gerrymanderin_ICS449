import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Vizeng Lee
 *Main class used to run the project
 */
public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Starting");
		final long startTime = System.currentTimeMillis();
		Parser parse = new Parser();
		//Remember to change the file name
		ArrayList<String> readFile = parse.getStringHolder();//This is the file converted into a ArrayString
		PrecintCreator createPrecint = new PrecintCreator(readFile);
		ArrayList<Precints> precintList = createPrecint.getPrecintList();//This is the ArrayList holding all the precints
		
		
		
		
		GreedyAlogrithim greedy = new GreedyAlogrithim(precintList, 12, createPrecint.getTotalNumberOfVotes());
		ArrayList<Group> greedyGroups = greedy.getGroupedPrecints();
		ArrayList<Precints> precintListDone = greedy.getPrecintList();
		WriteToGeoJson wgj = new WriteToGeoJson(precintListDone, greedyGroups);
		System.out.println("begging to print groups");
		for(int i = 0;i<greedyGroups.size();i++){
			Group group = greedyGroups.get(i);
			System.out.println("Printing group: " + i );
			ArrayList<Precints> groupPrecintList = group.getGroupedPrecintList();
			/*
			for(int j = 0;j<100;j++){
				Precints precint = groupPrecintList.get(j);
				precint.printAll();
				
			}
			System.out.println();*/
		}
		
		System.out.println("Finnished printing groups");
		
		final long endTime = System.currentTimeMillis()- startTime;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(endTime);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(endTime);
		System.out.println("Number of precints: " + precintList.size());
		System.out.println(" total Number of Votes: " + createPrecint.getTotalNumberOfVotes());
		System.out.println("Total execution time: " + minutes + " minutes " + seconds + " Seconds " + endTime +  " ms");
		
		System.out.println("DONE");
	}

}
