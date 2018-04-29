
import java.util.ArrayList;
import java.util.Random;

public class Group {

	private int groupNumber;
	private int populationAmount = 0;
	public ArrayList<Precints> groupedPrecintList = new ArrayList<Precints>();
	private int demAmount;
	private int repAmount;
	private String winningParty;
	
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
	
	public void findWinners(){
		setAmounts();
		setWinner();
	}
	
	private void setAmounts(){
		for(int i = 0;i<groupedPrecintList.size();i++){
			Precints precint = groupedPrecintList.get(i);
			int demVotes = Integer.parseInt(precint.getDemVotes());
			int repVotes = Integer.parseInt(precint.getRepVotes());
			demAmount = demAmount + demVotes;
			repAmount = repAmount + repVotes;
		}
		
	}
	private void setWinner(){
		if(demAmount>repAmount){
			winningParty = "Democrat";
		}else if(repAmount>demAmount){
			winningParty = "Republican";
		}
	}

	public int getDemAmount() {
		return demAmount;
	}

	public int getRepAmount() {
		return repAmount;
	}

	public String getWinningParty() {
		return winningParty;
	}
}
