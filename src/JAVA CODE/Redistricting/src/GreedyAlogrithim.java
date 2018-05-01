import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class GreedyAlogrithim {

	private ArrayList<Precints> precintList;//This holds all the precints
	private int numberOfGroups;//This number tell how many groups we group the precint by
	private int totalNumberOfVoters;
	private int numberOfVotersperGroup;
	private ArrayList<Group> groupedPrecints = new ArrayList<Group>();

	public GreedyAlogrithim(ArrayList<Precints> precintList, int numberOfGroups, int totalNumberOfvotes){
		this.precintList = precintList;
		this.numberOfGroups = numberOfGroups;
		this.totalNumberOfVoters = totalNumberOfvotes;
		findNumberOfVotersPerGroup();
		getMyIndexInMap();
		adjacentCreator();
		//getGreedy();
		addAdjacentToGeoJsonCode();
		
		

	}

	public void addAdjacentToGeoJsonCode(){
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			precint.addAdjacentPrecintsToString();
		}
	}

	/**
	 * this is the greedy algorithim that will group the precints based on population
	 */
	public void getGreedy(){

		int numOfVotersInGroup = 0;
		Precints precintToBeGrouped;
		Precints currentPrecint = getRandomPrecint();
		Precints previousPrecint;
		int numberOfReverses= 0;//This is how many times we go back and look for another precint
		//lets do max at 5
		Group group;
		for(int i = 1;i<=numberOfGroups;i++){
			group = new Group(i);
			System.out.println("Starting to group");
			while(group.getPopulationAmount()<=numberOfVotersperGroup&& numberOfReverses<5 ){
				group = groupAllAdjacentPrecints(group, currentPrecint);
				previousPrecint = currentPrecint;
				if(NoMoreAdjacentPrecints(currentPrecint)){//if there are no more adjacent precints....//Check this method
					currentPrecint = getRandomAdjacentPrecint(currentPrecint);
					numberOfReverses++;

				}else{
					currentPrecint = getPrecintsWithLeastAdjacentPrecints(currentPrecint);
				}

				System.out.println("Group: " + i + " filledPop: " + group.getPopulationAmount() + " final Amount: " + numberOfVotersperGroup);

			}
			numberOfReverses = 0;
			System.out.println("Finished Grouping group: " + i );

			groupedPrecints.add(group);
			currentPrecint = getPrecintsWithLeastAdjacentPrecints(currentPrecint);

		}
		/**
		 * this gives the straggeling precints a group
		 */
		System.out.println("Begging to group straggelers");
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			if(!precint.isGrouped()){
				Precints randomAdjacentPrecint = getRandomAdjacentPrecint(precint);
				int groupNumber = randomAdjacentPrecint.getGroupNumber();
				group(precint,groupNumber);
			}
		}
		System.out.println("Finishing grouping straggelers");



	}
	/**
	 * gets a random adjacent precint
	 * @param precint
	 * @return
	 */
	private Precints getRandomAdjacentPrecint(Precints precint){
		int randomNumber = generateRandomAdjacentListNumber(precint);
		Precints randomPrecint = precint.adjacentList.get(randomNumber);
		if(randomPrecint.isGrouped()){
			while(randomPrecint.isGrouped()==true){
				randomNumber = generateRandomAdjacentListNumber(randomPrecint);
				randomPrecint = randomPrecint.adjacentList.get(randomNumber);
			}
		}

		return randomPrecint;
	}


	/**
	 * this method gets the precint in the precint list with the least amount
	 * of adjacent precints
	 * @param precint
	 * @return
	 */
	private Precints getPrecintsWithLeastAdjacentPrecints(Precints precint){
		Precints returnPrecint = precint.adjacentList.get(0);
		int size = returnPrecint.adjacentList.size();
		for(int i = 1;i<precint.adjacentList.size();i++){
			int checkSize =  precint.adjacentList.get(i).adjacentList.size();
			if(size>checkSize){
				size = checkSize;
				returnPrecint =  precint.adjacentList.get(i);
			}
		}
		return returnPrecint;
	}

	/**
	 * this method checks to see if the precint has anymore adjacent precints with adjacencies
	 * @param precint
	 * @return
	 */
	private boolean NoMoreAdjacentPrecints(Precints precint){
		for(int i = 0;i<precint.adjacentList.size();i++){
			Precints checkPrecint = precint.adjacentList.get(i);
			for(int j = 0;j<checkPrecint.adjacentList.size();j++){
				Precints checkPrecint2 = checkPrecint.adjacentList.get(j);
				
				if(checkPrecint2.isGrouped()==false){//If one of the adjacent precints is not grouped return false
					return false;
				}

			}
			return true;
		}
		return true;
	}

	/**
	 * gets the adjacentprecint list fom the precint and groups them all
	 * @param group
	 * @param precint
	 */
	public Group groupAllAdjacentPrecints(Group group, Precints precint){
		group.addToPrecintList(precint);
		group(precint,group.getGroupNumber());
		int index1 = precint.getPlaceInIndex();
		precintList.get(index1).setGrouped(true);
		Precints adjacentPrecint;
		precintList.get(index1).setGroupNumber(group.getGroupNumber());
		for(int i = 0;i<precint.adjacentList.size();i++){
			 adjacentPrecint = precint.adjacentList.get(i);
			group(adjacentPrecint,group.getGroupNumber());
			group.addToPrecintList(adjacentPrecint);
			int index = adjacentPrecint.getPlaceInIndex();
			precintList.get(index).setGrouped(true);
			precintList.get(index).setGroupNumber(group.getGroupNumber());
		}
		return group;
	}


	/**
	 * this sets the precint
	 * @param precint
	 * @param i
	 */
	private void group(Precints precint, int i){
		precint.setGrouped(true);
		precint.setGroupNumber(i);
	}
	/**
	 * returns a random precint that hasn't been grouped
	 * @return
	 */
	private Precints getRandomPrecint(){
		int randomNumber = generateRandomNumber();
		Precints randomPrecint = precintList.get(randomNumber);
		if(randomPrecint.isGrouped()){
			while(randomPrecint.isGrouped() == true){
				int randomNumber2 = generateRandomNumber();

				randomPrecint = precintList.get(randomNumber2);
			}
		}
		return randomPrecint;
	}
	/**
	 * genereates a random number
	 * min = 0
	 * max = size of PrecintList
	 * @return
	 */
	private int generateRandomNumber(){

		Random rand = new Random(); 
		int value = rand.nextInt(precintList.size()); 
		return value;
	}
	/**
	 * genereates a random number
	 * min = 0
	 * max = size of PrecintList
	 * @return
	 */
	private int generateRandomAdjacentListNumber(Precints precint){

		Random rand = new Random(); 
		int value = rand.nextInt(precint.adjacentList.size()); 
		return value;
	}


	public ArrayList<Precints> getPrecintList() {
		return precintList;
	}




	/**
	 * This method creates a arrayList that shows all the precints it is adjacent to.
	 *
	 */
	public void adjacentCreator(){
		DecimalFormat df2 = new DecimalFormat(".##");
		//gets the precint list to compare one precint to another
		for(int i = 0;i<precintList.size();i++){
			Precints precint1 = precintList.get(i);
			System.out.println("Precint: " + i);
			double percent = (((double)i / 4120)*100); 
			System.out.println(df2.format(percent) + "% completed");
			ArrayList<String> longLatList1 = precint1.getLongLatList();
			for(int k = 0;k<longLatList1.size();k =k+2){
				String lon = longLatList1.get(k);
				String lat = longLatList1.get(k+1);
				String lonLat = getLongLatCordinatesString(lon,lat);// this it the varibale used to compare
				for(int j =0;j<precintList.size();j++){
					Precints precint2 = precintList.get(j);
					if(precint1.getPrecintName() != precint2.getPrecintName()&&!precint1.adjacentList.contains(precint2)){
						String precint2Compare = precint2.getGeoJsonCode();
						if(precint2Compare.contains(lonLat)){
							precint1.adjacentList.add(precint2);
						}
					}
				}

			}


		}

	}

	/**
	 * This method adds the to the precint object what place in the list it is
	 * 
	 */
	public void getMyIndexInMap(){
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			precint.setPlaceInIndex(i);
		}
	}

	/*
	 * This finds how many voters each group shold approximately have
	 */
	private void findNumberOfVotersPerGroup(){
		int numberofVoters = totalNumberOfVoters / numberOfGroups;
		setNumberOfVotersperGroup(numberofVoters);

	}

	private void setNumberOfVotersperGroup(int numberOfVotersperGroup) {
		this.numberOfVotersperGroup = numberOfVotersperGroup;
	}

	/**
	 * This string sets the longitude and latitude into a string so it can be read in the
	 * geoJson
	 * Example
	 * lon = -93.6926
	 * lat = 46.5447
	 * return String = [-93.6926,46.5447]
	 * @param lon
	 * @param lat
	 * @return
	 */
	public String getLongLatCordinatesString(String lon, String lat){
		String template1 = "[";
		String templte2 = ",";
		String template3 = "]";
		String finalString = template1 + lon + templte2 + lat + template3;
		return finalString;
	}


	public ArrayList<Group> getGroupedPrecints() {
		return groupedPrecints;
	}

}
