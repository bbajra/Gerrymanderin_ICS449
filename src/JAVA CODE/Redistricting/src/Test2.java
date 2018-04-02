import java.util.ArrayList;
import java.util.Random;

public class Test2 {

	public static void main(String[] args) {

		for(int i = 0;i<100;i++){
			int x = generateRandomAdjacentListNumber(10);
			System.out.println(x);
		}
		
	}
	
	private static  int generateRandomAdjacentListNumber(int precint){

		Random rand = new Random(); 
		int value = rand.nextInt(precint); 
		return value;
	}
}
