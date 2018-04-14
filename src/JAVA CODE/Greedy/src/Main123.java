import java.util.ArrayList;

public class Main123 {

	public static ArrayList<Precints> precintlist;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser parseFile = new Parser();
		ArrayList<String> fileAsString = parseFile.getStringHolder();//This reads the file into a string
		PrecintCreator precintCreator = new PrecintCreator(fileAsString);//this creats the precints from the file
		int numOfGroups = 8;
		GreedyGrouping greedy = new GreedyGrouping(precintCreator.getPrecintList(),numOfGroups, precintCreator.getTotalNumberOfVotes());
		ArrayList<Precints> precintListWithGroups = greedy.precintList;
		ArrayList<Group> groupList = greedy.getGroupedPrecints();
		for(int i = 0;i<groupList.size();i++){
			System.out.println("Group: " + i);
			Group group = groupList.get(i);
			for(int j = 0;j<group.getGroupedPrecintList().size();j++){
				group.getGroupedPrecintList().get(j).printAll();
			}
		}
		 
		/*
		precintlist = precintCreator.getPrecintList();
		Precints precint123 = precintlist.get(20);
		System.out.println("Expected: " );
		for(int i = 0;i<precint123.adjacentList.size();i++){
			System.out.println(precint123.adjacentList.get(i).getPrecintName());
		}
		System.out.println("");
		System.out.println("Observed: ");
		for(int i = 0;i<precint123.adjacentList.size();i++){
			Precints precint = getLeastAdjacentPrecints1(precint123);
			System.out.println(precint.getPrecintName() + " Size: " + precint.adjacentList.size());
			int index = precint.getPlaceInIndex();
			precintlist.get(index).setGrouped(true);
			System.out.print("Boolean: " + NoMoreAdjacentPrecints(precint123));
		}
		*/

	}

	/**
	 * this method gets the precint in the precint list with the least amount
	 * of adjacent precints
	 * compare the 2 Precints 
	 * @param precint
	 * @return
	 */
	private static Precints getPrecintsWithLeastAdjacentPrecints(Precints precint){
		Precints returnPrecint = null;
		for(int i = 0;i<precint.adjacentList.size()-1;i++){

			returnPrecint = precint.adjacentList.get(i);//This is the current precint
			if(!returnPrecint.isGrouped()){
				int size = returnPrecint.adjacentList.size();
				int returnPrecintIndex = returnPrecint.getPlaceInIndex();
				Precints checkPrecint = precint.adjacentList.get(i+1);//This is the next precint
				int checkSize =  checkPrecint.adjacentList.size();
				int checkPrecintindex = checkPrecint.getPlaceInIndex();
				Precints returnMasterprecint = precintlist.get(returnPrecintIndex);
				Precints checkMasterPrecint2 = precintlist.get(checkPrecintindex);


				if(!returnMasterprecint.isGrouped()&&!checkMasterPrecint2.isGrouped()){
					if((size>checkSize)){
						size = checkSize;
						returnPrecint =  precint.adjacentList.get(i);
					}
				}
			}
		}
		return returnPrecint;
	}

	private static Precints getLeastAdjacentPrecints1(Precints precints){

		ArrayList<Precints> nonGroupedList = returnNonGroupedList(precints);
		Precints returnPrecint = null;
		if(nonGroupedList.size()>=1){
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

	private static ArrayList<Precints>returnNonGroupedList(Precints precint){
		ArrayList<Precints> nonGroupedList = new ArrayList<Precints>();
		for(int i = 0;i<precint.adjacentList.size();i++){
			Precints adjacentPrecint = precint.adjacentList.get(i);
			int index = adjacentPrecint.getPlaceInIndex();
			Precints checkPrecint = precintlist.get(index);
			if(!checkPrecint.isGrouped()){
				nonGroupedList.add(adjacentPrecint);
			}
		}
		return nonGroupedList;
	}
	
	/**
	 * this method checks to see if the precint has anymore adjacent precints with adjacencies
	 * @param precint
	 * @return
	 */
	private static boolean NoMoreAdjacentPrecints(Precints precint){
		for(int i = 0;i<precint.adjacentList.size();i++){//This is the main precint
			Precints checkPrecint = precint.adjacentList.get(i);//This is the adjacent precint
			for(int j = 0;j<checkPrecint.adjacentList.size();j++){//Checks the adjacent precints of the adjacent pretins
				Precints checkPrecint2 = checkPrecint.adjacentList.get(j);//This is the adjacent precint of the first precint
				int index = checkPrecint2.getPlaceInIndex();
				Precints checkThisPrecint = precintlist.get(index);
				if(!checkThisPrecint.isGrouped()){//If one of the adjacent precints is not grouped return false
					return false;
				}

			}
		}
		return true;
	}
}
