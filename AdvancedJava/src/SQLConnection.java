import java.sql.*;

public class SQLConnection {
	// this class is to initiate the only connection with our SQL Server
	
	protected static String url = "jdbc:postgresql://localhost:5432/postgres"; // sql server link
	protected static String user ="postgres"; // sql server username
	protected static String password = "Pandas12"; // sql server password

	
	// connect
	static void connect() {
		Connection con = null;
		
		try {
			System.out.println("Connecting...");
			con = DriverManager.getConnection(url,user,password);
			System.out.println("Coonected \r\n");
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
	//		try {
	//			con.close();
	//		} catch (SQLException e) {
	//			//ignored
	//		}	
		}
  }
	
	public static void search() {
		Connection con = null;

		try {
			System.out.println("Enter name (starts with, min 2 characters):\r\n");
			String lookUpValue = Main.lookUp.next();
			
			if(lookUpValue.length() < 2) {
				System.out.println("Not enough charecters. Please try again.");
				search();
			}
			
			String output = lookUpValue.substring(0, 1).toUpperCase() + lookUpValue.substring(1);
				
			
			con = DriverManager.getConnection(url,user,password);
			String sqlLookUp = "'" + output + "%'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, age, department, role, salary FROM employee where name like " + sqlLookUp);
			System.out.println("========================================================================");
			System.out.println("ID | Name (age) | Department | Role | Salary \r\n");
			System.out.println("------------------------------------------------------------------------");
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String department = rs.getString(4);
				String role = rs.getString(5);
				int salary = rs.getInt(6);
				System.out.println(id + " | " + name + "(" + age + ")" + " | " + department + " | " + role + " | " + salary);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

		}	
		System.out.println("========================================================================");
		carryOn();
		
	}
	
	public static void filter() {
		Connection con = null;

		try {
			System.out.println("Enter field:\r\n");
			String field = Main.lookUp.next();
			
			System.out.println("Enter role:\r\n");
			String sqlrole = Main.lookUp.next();
					
			String outputField = field.substring(0, 1).toUpperCase() + field.substring(1);
			String outputRole = sqlrole.substring(0, 1).toUpperCase() + sqlrole.substring(1);
				
			
			con = DriverManager.getConnection(url,user,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, age, department, role, salary FROM employee WHERE " + outputField + " = '" + outputRole + "'");
			System.out.println("========================================================================");
			System.out.println("ID | Name (age) | Department | Role | Salary \r\n");
			System.out.println("------------------------------------------------------------------------");
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				String department = rs.getString(4);
				String role = rs.getString(5);
				int salary = rs.getInt(6);
				System.out.println(id + " | " + name + "(" + age + ")" + " | " + department + " | " + role + " | " + salary);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

		}	
		System.out.println("========================================================================");
		carryOn();
	}
	
	public static void report() {
		Connection con = null;

		try {
			System.out.println("Financial Report:\r\n");
			con = DriverManager.getConnection(url,user,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT department, min(salary), ceil(avg(salary)), max(salary), ceil(avg(salary/12)) FROM employee group by department");
			System.out.println("========================================================================");
			System.out.println("Department | Minimum | Average | Maximum | Monthly Average \r\n");
			System.out.println("------------------------------------------------------------------------");
			while(rs.next()) {
				String department = rs.getString(1);
				int min = rs.getInt(2);
				int avg = rs.getInt(3);
				int max = rs.getInt(4);
				int avgM = rs.getInt(5);
				System.out.println(department + " | " + min + " | " + avg + " | " + max + " | " + avgM);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {

		}	
		System.out.println("========================================================================");
		carryOn();
	}
	
	public static void carryOn() {
		System.out.println("\r\n");
		System.out.println("Want to continue search (y/n) ?\r\n");
		String lookUpValue = Main.lookUp.next();
		String l = lookUpValue.toUpperCase();
		
		switch(l) {
		case("Y"):
			EmployeeManagement.emMenu();
			break;
		case("N"):
			System.out.println("Goodbye!!");
			break;
		default:
			System.out.println("Invalid response please try again. :)");
			carryOn();
			break;
		}
	}
}
