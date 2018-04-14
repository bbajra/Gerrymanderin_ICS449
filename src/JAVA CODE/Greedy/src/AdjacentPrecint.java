
public class AdjacentPrecint {
/**
 * this class holds the precints name and its index in the precintList
 */
	private String precintName;
	private int indexInList;
	public AdjacentPrecint(String name, int index){
		this.precintName = name;
		this.indexInList = index;
	}
	
	public String getPrecintName() {
		return precintName;
	}
	public int getIndexInList() {
		return indexInList;
	}
}
