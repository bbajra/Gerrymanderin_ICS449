
public class Line {

	private LongLatHolder point1;
	private LongLatHolder point2;
	
	public LongLatHolder getPoint1() {
		return point1;
	}

	public void setPoint1(LongLatHolder point1) {
		this.point1 = point1;
	}

	public LongLatHolder getPoint2() {
		return point2;
	}

	public void setPoint2(LongLatHolder point2) {
		this.point2 = point2;
	}

	public Line(){
		
	}
	
	public void printAllLongLat(){
		System.out.println("Point 1");
		point1.printAll();
		System.out.println("Point 2");
		point2.printAll();
	}
	
	public void printLongLat1(){
		System.out.println("Point 1");
		point1.printAll();
	}
	
	public void printLongLat2(){
		System.out.println("Point 2");
		point2.printAll();
		
	}
	
	
	
}
