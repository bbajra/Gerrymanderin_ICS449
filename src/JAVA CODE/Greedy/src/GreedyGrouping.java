import java.util.ArrayList;
import java.util.Random;



public class GreedyGrouping {

	ArrayList<Precints> precintList;
	int numOfGroups;
	int totalNumberOfVotes;
	int totalNummberOfVotersPerGroup;

	private ArrayList<Group> groupedPrecints = new ArrayList<Group>();

	public GreedyGrouping(ArrayList<Precints> precintList, int numOfGroups, int totalNumberOfVotes){
		this.precintList = precintList;
		this.numOfGroups = numOfGroups;
		this.totalNumberOfVotes = totalNumberOfVotes;
		getNumberOfVotersPerGroup();
		//getGreedy();
		//getGreedy1();
		//getGreedy2();
		getGreedy3();

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
			group = new Group(i);
			boolean isFinished = false;
			while(group.getPopulationAmount()<=totalNummberOfVotersPerGroup&& isFinished == false){
				if(returnNonGroupedList(startingPrecint)!=null){//This part is working
					groupAllAdjacentPrecints(group, startingPrecint);
					startingPrecint = getPrecintsWithLeastAdjacentPrecints(startingPrecint);
					System.out.println("-----------Getting Precint With Least Adjacents");
				}else if(returnNonGroupedList(startingPrecint)==null){//TODO: Fix this part,
					//fix it to get least adjcaent from Groups instead of AdjacentList, this might work
					if(getRandomPrecintFromGroup2(group)!=null){
					startingPrecint = getRandomPrecintFromGroup2(group);
					System.out.println("********Starting precint is now: " + startingPrecint.getPrecintName());
					}else{
						isFinished = true;
						System.out.println("Group: " + group.getGroupNumber() + " has finished Grouping");
						
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
				System.out.println(precint.getPrecintName());
				count++;
			}
		}
		System.out.println("Number of Ungrouped Precints = " + count);
		
	}


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

	//The only thing we have to do is change the precint,
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



	private boolean precintIsGrouped(Precints precint){
		int index = precint.getPlaceInIndex();
		Precints precint1 = precintList.get(index);
		if(precint1.isGrouped()){
			return true;
		}
		return false;
	}
	/**
	 * returns an arrayList of nonGroupedPrecints
	 * @param precint
	 * @return
	 */
	private ArrayList<Precints>returnNonGroupedList(Precints precint){
		ArrayList<Precints> nonGroupedList = null;
		if(precint!=null){
			nonGroupedList = new ArrayList<Precints>();
			for(int i = 0;i<precint.adjacentList.size();i++){//the precint here is null
				Precints adjacentPrecint = precint.adjacentList.get(i);
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
	 * this is the 2nd phase if the the adjacent precints run out
	 * I want a randomPrecint from the group that has adjacentPrecints
	 * @param group
	 * @return
	 */
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
	
	private Precints getRandomPrecintFromGroup2(Group group){
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
	
	

}
