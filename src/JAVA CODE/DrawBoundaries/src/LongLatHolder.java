/**
 * 
 * @author Vizeng Lee
 *Need County,= county ID
 *Congressional District,= congDist
 *Senate District=MNSenDist
 *MN House District= MNLEGDist
 *
 */
public class LongLatHolder {

	private String x;
	private String y;
	private String CountyID;
	private String congDist;
	private String senateDist;
	private String legDist;
	
	
	public LongLatHolder(){
	
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getCountyID() {
		return CountyID;
	}

	public void setCountyID(String countyID) {
		CountyID = countyID;
	}

	public String getCongDist() {
		return congDist;
	}

	public void setCongDist(String congDist) {
		this.congDist = congDist;
	}

	public String getSenateDist() {
		return senateDist;
	}

	public void setSenateDist(String senateDist) {
		this.senateDist = senateDist;
	}

	public String getLegDist() {
		return legDist;
	}

	public void setLegDist(String legDist) {
		this.legDist = legDist;
	}
	
	public void printAll(){
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		System.out.println("County ID: " + CountyID);
		System.out.println("Congerssinal Dist: " + congDist);
		System.out.println("Senate District: " + senateDist);
		System.out.println("Legeslative Dist: " + legDist);
		System.out.println("");
	}
	
}
