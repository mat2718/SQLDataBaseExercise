import java.sql.*;

public class EmployeeManagement {
	
	
	//query
	// deal with results
//	Statement stmt = con.createStatement();
//	ResultSet rs = stmt.executeQuery("SELECT name, age FROM employee");
	
	static public void emMenu() {
		System.out.println("Employer Management Menu\r\n"
				+ "\r\n"
				+ "1.  Search\r\n"
				+ "2.  Filter\r\n"
				+ "3.  Report\r\n"
				+ "\r\n");
		
		System.out.println("Please select the number cooresponding to your desired menu selection (ex. 1, 2, or 3)\r\n");
		int selection = Main.input.nextInt();
		switch(selection) {
		case (1):
			SQLConnection.search();
			break;
		case (2):
			SQLConnection.filter();
			break;
		case (3):
			SQLConnection.report();
			break;
		default:
			System.out.println("Invalid selection! Please try again.\r\n");
			emMenu();
			break;
		}
	}
	
	
	
	
	

}