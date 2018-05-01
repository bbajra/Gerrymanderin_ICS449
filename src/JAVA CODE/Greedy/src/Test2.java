
public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 9;
		for(int i = 0;i<10;i++){
			System.out.println("i" + i);
			for(int j = 0;j<10;j++){
				System.out.println("j"+j);
				if(j==x){
					System.out.println("Here");
					break;
				}
			}
		}
	}

}
