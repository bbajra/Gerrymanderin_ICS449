import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
*Created by Vizeng Lee
*
**/
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Long start = System.currentTimeMillis();
		// TODO Auto-generated method stub
		ReadAndWrite Raw = new ReadAndWrite("MNHouse");
		ArrayList<String> unMod = Raw.getStringHolder();
		//11
		String x = unMod.get(11);
		char[] test = x.toCharArray();
		BoundaryCreator BC = new BoundaryCreator(unMod);
		ArrayList<Line> LineList = BC.getLineList();
		ArrayList<LongLatHolder> longlat = BC.getLongLatHolderList();
		ArrayList<String> unModed = BC.getUnModed();
		BC.deleteUseLess(1);
		ArrayList<Line> listList = BC.getLineListFinal();
		File file = new File("C:/Users/Vizeng Lee/Desktop/test.txt");
		PrintWriter pw = new PrintWriter(file);
		//283539 times 
	
		/*
		for(int i = 0;i<listList.size();i++){
			Line line1 = listList.get(i);
			pw.println("");
			pw.println("Line1 CongDist: " +  line1.getPoint1().getCongDist() + " CountyID: " + line1.getPoint1().getCountyID() + 
					" LEG DIST: " + line1.getPoint1().getLegDist() + " SenateDist: " + line1.getPoint1().getSenateDist() + 
					" LONG: " + line1.getPoint1().getX() + " LAT: " + line1.getPoint1().getY());
			pw.println("Line1 CongDist: " +  line1.getPoint2().getCongDist() + " CountyID: " + line1.getPoint2().getCountyID() + 
					" LEG DIST: " + line1.getPoint2().getLegDist() + " SenateDist: " + line1.getPoint2().getSenateDist() + 
					" LONG: " + line1.getPoint2().getX() + " LAT: " + line1.getPoint2().getY());
			
			System.out.println("Line1 CongDist: " +  line1.getPoint1().getCongDist() + " CountyID: " + line1.getPoint1().getCountyID() + 
					" LEG DIST: " + line1.getPoint1().getLegDist() + " SenateDist: " + line1.getPoint1().getSenateDist() + 
					" LONG: " + line1.getPoint1().getX() + " LAT: " + line1.getPoint1().getY());
			System.out.println("Line1 CongDist: " +  line1.getPoint2().getCongDist() + " CountyID: " + line1.getPoint2().getCountyID() + 
					" LEG DIST: " + line1.getPoint2().getLegDist() + " SenateDist: " + line1.getPoint2().getSenateDist() + 
					" LONG: " + line1.getPoint2().getX() + " LAT: " + line1.getPoint2().getY());
			
		}*/
		
		
		
		System.out.println("Size of Final List" + listList.size());
		pw.close();
		Long end = System.currentTimeMillis();
		Long finish = start-end;
		System.out.println("Finished in : " + finish + "NS");
		

	}

}
