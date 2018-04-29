import java.util.ArrayList;
import java.util.Random;



public class GreedyGrouping {

	ArrayList<Precints> precintList;
	int numOfGroups;
	int totalNumberOfVotes;
	int totalNummberOfVotersPerGroup;
	int count = 0;

	private ArrayList<Group> groupedPrecints = new ArrayList<Group>();
	private ArrayList<Precints> order = new ArrayList<Precints>();

	public GreedyGrouping(ArrayList<Precints> precintList, int numOfGroups, int totalNumberOfVotes){
		this.precintList = precintList;
		this.numOfGroups = numOfGroups;
		this.totalNumberOfVotes = totalNumberOfVotes;
		getNumberOfVotersPerGroup();
		getGreedy3();
		cleanup();
		cleanup();
		printUngroupedPrecints();
		findWinners();
		//setWinners();

	}

	private void getNumberOfVotersPerGroup(){
		this.totalNummberOfVotersPerGroup = totalNumberOfVotes/numOfGroups;
	}

	//For this one we're gonna say it only has 2 options
	//1. Keep getting the least adjacent, 
	//2.once it cannot get the least adjacent Precint anymore it will go to a 
	// random precints in the group
	//3. once all the random precints are done it will be set as finished
	private void getGreedy3(){		
		Precints startingPrecint = getRandomPrecint();
		//Precints startingPrecint = precintList.get(5);
		Group group;
		for(int i = 1;i<=numOfGroups;i++){
			group = new Group(i);//Create new group
			boolean isFinished = false;//Set to false
			while(group.getPopulationAmount()<=totalNummberOfVotersPerGroup&& isFinished == false){//While the pop
				if(returnNonGroupedList(startingPrecint)!=null){//If the precint has adjacent precints that are not grouped
					System.out.println("1");
					groupAllAdjacentPrecints(group, startingPrecint);//Group them all
					startingPrecint = getPrecintsWithLeastAdjacentPrecints(startingPrecint);//The new starting precint is the precint with the least amount of adjacent precint
					System.out.println("-----------Getting Precint With Least Adjacents");
				}else if(returnNonGroupedList(startingPrecint)==null){//if the precint has no more groupable adjacent Precints
					System.out.println("2");
					//fix it to get least adjcaent from Groups instead of AdjacentList, this might work
					if(getRandomPercintFromGroup2(group)!=null){
						System.out.println("3");
						startingPrecint = getRandomPercintFromGroup2(group);
						System.out.println("********Starting precint is now: " + startingPrecint.getPrecintName());
					}else{
						System.out.println("4");
						isFinished = true;
						System.out.println("Group: " + group.getGroupNumber() + " has finished Grouping");
						if(isFinished == true && group.getPopulationAmount()==0){
							isFinished =false;
							startingPrecint = getRandomPrecint();
						}

					}
				}
				
				
			}
			if(isFinished=true){
				startingPrecint = getRandomPrecint();
			}
			groupedPrecints.add(group);

		}

		int count =0;
		for(int k = 0;k<precintList.size();k++){
			Precints precint = precintList.get(k);

			if(!precint.isGrouped()){
				//System.out.println(precint.getPrecintName());
				count++;
			}
		}
		//System.out.println("Number of Ungrouped Precints = " + count);

	}

	/*
	private void getGreedy2(){
		//Start with random precint
		Precints startingPrecint = getRandomPrecint();
		for(int i = 1;i<=numOfGroups;i++){
			Group group = new Group(i);
			boolean isFinished = false;
			while(group.getPopulationAmount()<=totalNumberOfVotes|| isFinished == true){
				groupAllAdjacentPrecints(group, startingPrecint);
				if(returnNonGroupedList(startingPrecint)!=null){
					if(getPrecintsWithLeastAdjacentPrecints(startingPrecint)!=null){
						startingPrecint = getPrecintsWithLeastAdjacentPrecints(startingPrecint);
						System.out.println("-----------Getting Precint With Least Adjacents");
					}
					//System.out.println("Starting precint is now: " + startingPrecint.getPrecintName());
				}else if(returnNonGroupedList(startingPrecint)==null){
					//startingPrecint = random GroupedPrecint that an and adjacentPrecint
					if(getRandomPrecintFromGroup(group)!=null){
						startingPrecint = getRandomPrecintFromGroup(group);
						System.out.println("********Starting precint is now: " + startingPrecint.getPrecintName());
					}else{
						isFinished = true;
						System.out.println("Group: " + group.getGroupNumber() + " has finished Grouping");
					}
				}
			}



		}	

	}
	 */
	//The only thing we have to do is change the precint,
	/*
	public void getGreedy1(){
		//Precints currentPrecint = getRandomPrecint();
		//int reverse = 0;//Check line = 5 adjacent precints, check reverse = 10 check group of adjacent precints
		Precints precint = getRandomPrecint();
		boolean ifFinished = false;
		for(int i = 1;i<=numOfGroups;i++){
			int reverse = 0;//Check line = 5 adjacent precints, check reverse = 10 check group of adjacent precints
			Group group = new Group(i);

			while(group.getPopulationAmount()<=totalNummberOfVotersPerGroup){
				groupAllAdjacentPrecints(group, precint);
				System.out.println("group: " + group.getGroupNumber() + "votes: " + group.getPopulationAmount() + "Precint: " + precint.getPrecintName());

				if(!NoMoreAdjacentPrecints(precint)){//get adjacent precint from precint
					precint = getPrecintsWithLeastAdjacentPrecints(precint);

				}else if(reverse ==5){//get adjacent precint from group
					precint = group.getRandomAdjacentPrecintFromGroup();
					reverse = 0;
				}else{//if done
					precint = getRandomAdjacentPrecint(precint);
					reverse++;

				}

			}
			groupedPrecints.add(group);
			precint = getPrecintsWithLeastAdjacentPrecints(precint);
		}


	}
	 */

	/**
	 * use this method to search the Group Object for an adjacent precint
	 * This method is called so that we don't fall in a loop of grouping the same precints over and over again
	 */
	private Precints searchGroupForAdjacentPrecint(Group group){
		Precints returnPrecint = group.getRandomAdjacentPrecintFromGroup();

		return returnPrecint;


	}
	public ArrayList<Group> getGroupedPrecints() {
		return groupedPrecints;
	}

	/**
	 * this is the greedy algorithim that will group the precints based on population
	 */
	/*
	public void getGreedy(){

		int numOfVotersInGroup = 0;
		Precints precintToBeGrouped;
		Precints currentPrecint = getRandomPrecint();
		Precints previousPrecint;
		int numberOfReverses= 0;//This is how many times we go back and look for another precint
		//lets do max at 5
		Group group;
		for(int i = 1;i<=numOfGroups;i++){
			group = new Group(i);
			System.out.println("Starting to group");
			while(group.getPopulationAmount()<=numOfVotersInGroup&& numberOfReverses<5 ){
				group = groupAllAdjacentPrecints(group, currentPrecint);
				previousPrecint = currentPrecint;
				if(NoMoreAdjacentPrecints(currentPrecint)){//if there are no more adjacent precints....//Check this method
					currentPrecint = getRandomAdjacentPrecint(currentPrecint);
					numberOfReverses++;

				}else{
					currentPrecint = getPrecintsWithLeastAdjacentPrecints(currentPrecint);
				}

				System.out.println("Group: " + i + " filledPop: " + group.getPopulationAmount() + " final Amount: " + totalNummberOfVotersPerGroup);

			}
			numberOfReverses = 0;
			System.out.println("Finished Grouping group: " + i );

			groupedPrecints.add(group);
			currentPrecint = getPrecintsWithLeastAdjacentPrecints(currentPrecint);

		}
	}
	 */
	/**
	 * returns a random precint that hasn't been grouped
	 * @return
	 */
	private Precints getRandomPrecint(){
		ArrayList<Precints> nonGroupedPrecints = new ArrayList<Precints>();
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			if(!precint.isGrouped()){
				nonGroupedPrecints.add(precint);
			}
		}
		int randomNumber = generateRandomNumberGivenMax(nonGroupedPrecints.size());
		Precints randomPrecint = nonGroupedPrecints.get(randomNumber);
		return randomPrecint;
	}

	/**
	 * will never get the max value 
	 * @param x
	 * @return
	 */
	private int generateRandomNumberGivenMax(int x){
		Random rand = new Random();
		int value = rand.nextInt(x);
		return value;
	}

	/**
	 * gets a random adjacent precint
	 * @param precint
	 * @return
	 */
	/*
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
	 */
	/**
	 * genereates a random number
	 * min = 0
	 * max = size of PrecintList
	 * @return
	 */
	/*
	private int generateRandomAdjacentListNumber(Precints precint){

		Random rand = new Random(); 
		int value = rand.nextInt(precint.adjacentList.size()); 
		return value;
	}
	 */
	/**
	 * gets the adjacentprecint list fom the precint and groups them all
	 * @param group
	 * @param precint
	 */
	private Group groupAllAdjacentPrecints(Group group, Precints precint){
		group(precint,group.getGroupNumber(),group);
		//int index1 = precint.getPlaceInIndex();
		Precints adjacentPrecint;
		//precintList.get(index1).setGroupNumber(group.getGroupNumber());
		System.out.println("Starting to groupAll Adjacents of: " + precint.getPrecintName() +  " into group: " + group.getGroupNumber());
		for(int i = 0;i<precint.adjacentList.size();i++){
			adjacentPrecint = precint.adjacentList.get(i);
			group(adjacentPrecint,group.getGroupNumber(),group);
		}
		return group;
	}
	/**
	 * this sets the precint
	 * @param precint
	 * @param i
	 */
	private void group(Precints precint, int i, Group group){
		//System.out.println("trying to group: " + precint.getPrecintName() + " into group: " + group.getGroupNumber() );
		int index = precint.getPlaceInIndex();
		Precints masterPrecint = precintList.get(index);
		if(!masterPrecint.isGrouped()){
			masterPrecint.setGrouped(true);
			masterPrecint.setGroupNumber(i);
			precint.setGrouped(true);
			precint.setGroupNumber(i);
			group.addToPrecintList(precint);
			order.add(precint);
			System.out.println("Sucessfully grouped " + masterPrecint.getPrecintName() + " in group: " + masterPrecint.getGroupNumber() );
		}else if(masterPrecint.isGrouped()){
			System.out.println("Failed. Precint: " + masterPrecint.getPrecintName() + " is already grouped in group: " + masterPrecint.getGroupNumber() );
		}
		else{
			System.out.println("Fail idk why");
		}

	}
	/**
	 * this method checks to see if the precint has anymore adjacent precints with adjacencies
	 * @param precint
	 * @return
	 */
	/*
	private boolean NoMoreAdjacentPrecints(Precints precint){
		for(int i = 0;i<precint.adjacentList.size();i++){//Checks the adjacent precint list
			Precints checkPrecint = precint.adjacentList.get(i);
			for(int j = 0;j<checkPrecint.adjacentList.size();j++){//Checks the adjacent precints of the adjacent pretins
				Precints checkPrecint2 = checkPrecint.adjacentList.get(j);//This is the adjacent precint of the first precint
				int index = checkPrecint2.getPlaceInIndex();
				Precints checkThisPrecint = precintList.get(index);
				if(!checkThisPrecint.isGrouped()){//If one of the adjacent precints is not grouped return false
					return false;
				}

			}
		}
		return true;
	}
	 */

	public ArrayList<Precints> getOrder() {
		return order;
	}

	/*
	private boolean precintIsGrouped(Precints precint){
		int index = precint.getPlaceInIndex();
		Precints precint1 = precintList.get(index);
		if(precint1.isGrouped()){
			return true;
		}
		return false;
	}
	 */
	/**
	 * returns an arrayList of nonGrouped adjacent precints
	 * checks to see if it has ajacent precints that are not grouped
	 * @param precint
	 * @return
	 */
	private ArrayList<Precints>returnNonGroupedList(Precints precint){
		ArrayList<Precints> nonGroupedList = null;
		if(precint!=null){
			nonGroupedList = new ArrayList<Precints>();
			for(int i = 0;i<precint.adjacentList.size();i++){//Gets adjacent List from Precints
				Precints adjacentPrecint = precint.adjacentList.get(i);//Gets Adjacent Precint
				int index = adjacentPrecint.getPlaceInIndex();
				Precints checkPrecint = precintList.get(index);
				if(!checkPrecint.isGrouped()){
					nonGroupedList.add(adjacentPrecint);
				}
			}
			if(nonGroupedList.size()==0){
				nonGroupedList = null;
			}
		}
		return nonGroupedList;
	}

	/**
	 * 
	 * This Method gets a ArrayList of the nonGroupedAdjacent PrecintList
	 * This method works 100%
	 * 
	 * @param precint
	 * @return
	 */
	private Precints getPrecintsWithLeastAdjacentPrecints(Precints precint){
		ArrayList<Precints> nonGroupedList = returnNonGroupedList(precint);
		Precints returnPrecint = null;
		if(nonGroupedList == null){
			returnPrecint = null;
		}
		else if(nonGroupedList.size()>=1){
			returnPrecint = nonGroupedList.get(0);
			for(int i = 0;i<nonGroupedList.size();i++){
				Precints checkPrecint = nonGroupedList.get(i);
				int checkSize = checkPrecint.adjacentList.size();
				int returnSize = returnPrecint.adjacentList.size();
				if(checkSize<returnSize){
					returnPrecint = checkPrecint;
				}
			}
		}
		return returnPrecint;
	}
	/**
	 * new
	 * @param precint
	 * @return
	 */
	private Precints getRandomPrecints123(Precints precint){
		Precints returnPrecint = null;
		ArrayList<Precints> temp = new ArrayList<Precints>();
		for(int i = 0;i<precint.adjacentList.size();i++){
			Precints precint1 = precint.adjacentList.get(i);
			if(returnNonGroupedList(precint)!=null){
			temp.add(precint1);	
			}
		}
		if(temp.size()!=0){
		int index = generateRandomNumberGivenMax(temp.size());
		returnPrecint = temp.get(index);
		return returnPrecint;
		} 
		return returnPrecint;
	}

	/**
	 * this is the 2nd phase if the the adjacent precints run out
	 * I want a randomPrecint from the group that has adjacentPrecints
	 * @param group
	 * @return
	 */
	/*
	private Precints getRandomPrecintFromGroup(Group group){
		Precints returnPrecint = null;
		for(int i = 0;i<group.groupedPrecintList.size();i++){
			Precints precint = group.groupedPrecintList.get(i);
			int index = precint.getPlaceInIndex();
			Precints masterPrecint = precintList.get(index);
			if(returnNonGroupedList(masterPrecint)!=null){
				returnPrecint = precint;
				return returnPrecint;
			}
		}
		return returnPrecint;
	}
	 */
	/**
	 * return a precint from group2 that have adjacent Precints that are not yet grouped
	 * @param group
	 * @return
	 */
	private Precints getRandomPercintFromGroup2(Group group){
		Precints returnPrecint = null;
		if(group ==null){
			return returnPrecint;
		}else {
			ArrayList<Precints> groupedPrecints = group.groupedPrecintList;
			ArrayList<Precints> listOfPrecintsWithUngroupedAdjacentPrecints = new ArrayList<Precints>();
			for(int i = 0;i<groupedPrecints.size();i++){
				Precints precint = groupedPrecints.get(i);
				if(returnNonGroupedList(precint)!= null){
					listOfPrecintsWithUngroupedAdjacentPrecints.add(precint);
				}
			}
			
			if(listOfPrecintsWithUngroupedAdjacentPrecints.size()==0){
				return returnPrecint;
			}else{
				int index = generateRandomNumberGivenMax(listOfPrecintsWithUngroupedAdjacentPrecints.size());
				returnPrecint = listOfPrecintsWithUngroupedAdjacentPrecints.get(index);
				return returnPrecint;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * this is the backup of the method
	 * @param group
	 * @return
	 */
	private Precints getRandomPrecintFromGroupBak(Group group){
		Precints returnPrecint = null;
		
		
		for(int i = 0;i<group.groupedPrecintList.size();i++){
			Precints precint = group.groupedPrecintList.get(i);
			ArrayList<Precints> nonGroupList = returnNonGroupedList(precint);
			if(nonGroupList!=null){//if it has adjacent Precints
				
				returnPrecint = precint;
				return returnPrecint;

			}
		}
		
		return returnPrecint;
	}
	
	/**
	 * returns a list of precints with AdjacentPrecints
	 * @param precint
	 * @return
	 */
	private ArrayList<Precints> getListOfAdjacentPrecints(ArrayList<Precints> groupedPrecintList){
		ArrayList<Precints> precintsWithAdjacentPrecintsThatAreNotGrouped = new ArrayList<Precints>();
		for(int i = 0;i<groupedPrecintList.size();i++){
			Precints precint = groupedPrecintList.get(i);//The precint from the group
			for(int j=0;j<precint.adjacentList.size();j++){
				Precints precintCheck = precint.adjacentList.get(j);//The adjacent Precint
				int index = precintCheck.getPlaceInIndex();
				Precints MasterPrecint = precintList.get(index);
				if(MasterPrecint.isGrouped()){
					precintsWithAdjacentPrecintsThatAreNotGrouped.add(precint);
					break;
				}
			}
		}
		return precintsWithAdjacentPrecintsThatAreNotGrouped;
	}

	/**
	 * This method gets the rest of the precints that are not 
	 * yet grouped and tries to group them as best as possible
	 */
	private void cleanup(){
		ArrayList<Precints> nonGroupedPrecints = new ArrayList<Precints>();
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			if(!precint.isGrouped()){
				nonGroupedPrecints.add(precint);
			}
		}
		while(!nonGroupedPrecints.isEmpty()){//This is all the precints that are not grouped taken from the master list
			for(int i = 0;i<nonGroupedPrecints.size();i++){
				Precints notGroupedPrecints = nonGroupedPrecints.get(i);
				ArrayList<Precints> groupedAdjacentPrecints = new ArrayList<Precints>();
				int groupNumber;
				int index = notGroupedPrecints.getPlaceInIndex();
				
				for(int j = 0;j<notGroupedPrecints.adjacentList.size();j++){
					Precints precint = notGroupedPrecints.adjacentList.get(j);
					if(precint.isGrouped()){//FInd the adjacent precint that is grouped
						groupedAdjacentPrecints.add(precint);
					}
				}
				if(!groupedAdjacentPrecints.isEmpty()){
					groupNumber = groupedAdjacentPrecints.get(generateRandomNumberGivenMax(groupedAdjacentPrecints.size())).getGroupNumber();
					Precints masterPrecint = precintList.get(index);
					masterPrecint.setGroupNumber(groupNumber);
					masterPrecint.setGrouped(true);
					int arrayListIndex = returnIndex(masterPrecint, nonGroupedPrecints);
					nonGroupedPrecints.remove(arrayListIndex);
					Group group = groupedPrecints.get(groupNumber-1);
					notGroupedPrecints.setGroupNumber(groupNumber);
					notGroupedPrecints.setGrouped(true);
					group.addToPrecintList(notGroupedPrecints);
					//Add to groups
					//System.out.println("+++++ Grouped outlier Percint : " + masterPrecint.getPrecintName() + "in group: " + masterPrecint.getGroupNumber() + " Index: " + masterPrecint.getPlaceInIndex());

				}
			}
			//printNumOfUngroupedPrecints();
			
			
		}
	}
	/**
	 * gets index of precint from the arraylist
	 * @param precint
	 * @param precintArrayList
	 * @return
	 */
	private int returnIndex(Precints precint,ArrayList<Precints> precintArrayList){
		int index = 0;
		for(int i = 0;i<precintArrayList.size();i++){
			Precints precintCheck = precintArrayList.get(i);
			if(precintCheck.getPrecintName().equals(precint.getPrecintName())){
				index = i;
				return index;
			}
			
		}
		return index;
	}
	
	
	private void printNumOfUngroupedPrecints(){
		int count = 0;
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			if(!precint.isGrouped()){
				//System.out.println("Precint not grouped: " + precint.getPrecintName());
				count++;
			}
		}
		System.out.println("Num of Precints: " + precintList.size());
		System.out.println("Total Amount of unGrouped Precints: " + count);
	}
	
	
	private void printUngroupedPrecints(){
		int count = 0;
		for(int i = 0;i<precintList.size();i++){
			Precints precint = precintList.get(i);
			if(!precint.isGrouped()){
				//System.out.println("Precint not grouped: " + precint.getPrecintName());
				count++;
			}
		}
		System.out.println("Total Amount of unGrouped Precints: " + count);
	}

	/*
	 * this writes into the 
	 */
	private void findWinners(){
		for(int i = 0;i<groupedPrecints.size();i++){
			Group group = groupedPrecints.get(i);
			group.findWinners();
		}
	}
	/**
	 * this write the winner into the geoJsonCode
	 * And changes it inside the masterPrecintList
	 */
	private void setWinners(){
		for(int i = 0;i<groupedPrecints.size();i++){
			Group group = groupedPrecints.get(i);
			String winner = group.getWinningParty();
			for(int j = 0;j<group.groupedPrecintList.size();j++){
				Precints precint = group.groupedPrecintList.get(j);
				String geoJson = precint.getGeoJsonCode();
				count++;
				System.out.println("Precint Name: " + precint.getPrecintName() + " Count: " + count);
				String newGeoJson = insertWinningPrecint(geoJson, winner);
				precint.setGeoJsonCode(newGeoJson);
				
			}
		}
	}
	
	private String insertWinningPrecint(String geoJsonString, String Winner){
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
				//System.out.println("Winner is repbulican but geoJson is demcrat so we need to change it to Republican");
				char[] newStringAsChar = new char[org.length+1];
				
				System.arraycopy(org, 0, newStringAsChar, 0, index-1);
				returnString = String.valueOf(newStringAsChar);
				//System.out.println(returnString);
				
				System.arraycopy(repChar, 0, newStringAsChar, index-1, repChar.length);
				returnString = String.valueOf(newStringAsChar);
				//System.out.println(returnString);
				int length  = org.length-index-8;
				System.arraycopy(org, index+8, newStringAsChar, index+9,length);//Length has to be 133
				//Length of org = 326
				//length of newString as char = 
				//index = 186
				returnString = String.valueOf(newStringAsChar);
				//System.out.println(returnString);
			}
	
		}else if(Winner == "Democrat"){
			if(org[index]=='R'){
				//Change it to Republic
				System.out.println("Winner is democrat, but geoJson is Republican, so we need to change it to democrat");
				char[] newStringAsChar = new char[org.length-2];
				
				System.arraycopy(org, 0, newStringAsChar, 0, index-1);
				returnString = String.valueOf(newStringAsChar);
				//System.out.println(returnString);
				
				System.arraycopy(demChar, 0, newStringAsChar, index-1, demChar.length);
				returnString = String.valueOf(newStringAsChar);
				//System.out.println(returnString);
				int length  = org.length-index-10;
				System.arraycopy(org, index+10, newStringAsChar, index+7,length);//Length has to be 133

				returnString = String.valueOf(newStringAsChar);
				//System.out.println(returnString);
				
			}
		}
		return returnString;
		
		
	}
	
	
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
	
	

	
	
	
	
	
	
}
