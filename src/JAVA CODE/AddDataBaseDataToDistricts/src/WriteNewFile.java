import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteNewFile {

	private String FileName;
	private ArrayList<String> readyToWriteToFileList;
	public WriteNewFile(String newFileName,ArrayList<String> finalList){
		this.FileName = newFileName;
		this.readyToWriteToFileList = finalList;
		write();
	}
	/**
	 * writes the data to a file
	 */
	public void write(){
		File file = new File("C:/Projects/ICS499/AddDataBaseDataToDistricts/Lib/"+FileName+".txt");
		try {
			PrintWriter pw = new PrintWriter(file);
			for(int i = 0;i<readyToWriteToFileList.size();i++){
				String line = readyToWriteToFileList.get(i);
				pw.println(line);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Something failed when writting");
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
