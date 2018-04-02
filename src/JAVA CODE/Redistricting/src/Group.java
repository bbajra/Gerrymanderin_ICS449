import java.util.ArrayList;

public class Group {

	private int groupNumber;
	private int populationAmount = 0;
	private ArrayList<Precints> groupedPrecintList = new ArrayList<Precints>();
	
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
	
}
