
import java.util.ArrayList;
import java.util.Random;

public class Group {

	private int groupNumber;
	private int populationAmount = 0;
	public ArrayList<Precints> groupedPrecintList = new ArrayList<Precints>();
	
	public Group(int groupNumber){
		this.groupNumber = groupNumber;
	}
	
	private void addPopulation(int population){
		populationAmount = populationAmount + population;
				
	}
	
	public void addToPrecintList(Precints groupedPrecint){
		groupedPrecintList.add(groupedPrecint);
		
		int pop = Integer.parseInt(groupedPrecint.getTotVotes());
		addPopulation(pop);
		System.out.println("inside group: " + groupNumber + " Added Precint: " + groupedPrecint.getPrecintName() + " totalNumberPop: " + populationAmount);
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public int getPopulationAmount() {
		return populationAmount;
	}

	public ArrayList<Precints> getGroupedPrecintList() {
		return groupedPrecintList;
	}
	
	public Precints getRandomAdjacentPrecintFromGroup(){
		int randomIndex = generateRandomNumber();
		Precints precint = groupedPrecintList.get(randomIndex);
		return precint;
	}
	private int generateRandomNumber(){

		Random rand = new Random(); 
		int value = rand.nextInt(groupedPrecintList.size()); 
		return value;
	}
}
