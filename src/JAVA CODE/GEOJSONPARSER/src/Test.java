
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = "\"party\":\"\"";
		System.out.println(x);
		x = x.replaceFirst("\"party\":\"\"", "\"party\":\"Republican\"");
		System.out.print(x);
		
	}

}
