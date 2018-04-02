import java.sql.*;
/**
 * This class constanlty changes the local variable of the class
 * @author Vizeng Lee
 *
 */
public class DataBaseConnection {
	private Connection connection;
	private String TotalNumberOfVoters = "0";
	private String RepublicanVoters = "0";
	private String DemocratVoters = "0";
	public DataBaseConnection(){
		connect();
	}
	
	/**
	 * Connects to the database
	 */
	private void connect(){
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		 connection = null;

		try {
			connection = DriverManager
			//Database has no password anymore
			.getConnection("jdbc:mysql://127.0.0.1:3306/ics499","root", "");
			

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

/**
 * This method takes the precint name and the type of vote and gets the data from the sql database
 * and puts it into the objects.
 * @param PrecintName
 * @param typeOfVote
 * typeOfVote is either
 * Presidental
 * Congressional
 * "MN Senate
 * MN House
 */
	//public void setPopulations(String PrecintName, String typeOfVote){
	public void setPopulations(String PrecintCode, String typeOfVote){	
	String voteType = null;
		/*
		 * 4 types of vote type
		 */
		if(typeOfVote == "Presidental"){
				voteType = "usprs";
		}else if(typeOfVote == "Congressional"){
			voteType ="usrep";
		}else if(typeOfVote =="MN Senate"){
			voteType="MNSEN";
		}else if(typeOfVote == "MN House"){
			voteType="MNLEG";
		}
		
		Statement stmt = null;
		String query = " SELECT totvoting, "+voteType + "r, "+ 
		//voteType + "dfl from ics499.results where pctname = " + "'" + PrecintCode + "'";
		voteType + "dfl from ics499.results where vtdid = " + "'" + PrecintCode + "'";
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			//This test the data
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        if(i==1){
			        	setTotalNumberOfVoters(columnValue);
			        }else if(i==2){
			        	setRepublicanVoters(columnValue);
			        }else if(i==3){
			        	setDemocratVoters(columnValue);
			        }
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
			    System.out.println("");
			}
			//End test
			
			/**
			String TotalVoters = rs.getString("totvoting");
			String RepublicanVote= rs.getString(voteType + "r");
			String DemocratVote  = rs.getString(voteType + "dfl");
			setTotalNumberOfVoters(TotalVoters.toString());
			setDemocratVoters(DemocratVote.toString());
			setRepublicanVoters(RepublicanVote.toString());
			**/
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//This creates the statement for the Database Connection
	}
	/**
	 * closes the connection
	 */
	public void closeConnection(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Was not able to close");
			e.printStackTrace();
		}
	}

	public String getTotalNumberOfVoters() {
		return TotalNumberOfVoters;
	}

	private void setTotalNumberOfVoters(String totalNumberOfVoters) {
		TotalNumberOfVoters = totalNumberOfVoters;
	}

	public String getRepublicanVoters() {
		return RepublicanVoters;
	}

	private void setRepublicanVoters(String republicanVoters) {
		RepublicanVoters = republicanVoters;
	}

	public String getDemocratVoters() {
		return DemocratVoters;
	}

	private void setDemocratVoters(String democratVoters) {
		DemocratVoters = democratVoters;
	}
}
