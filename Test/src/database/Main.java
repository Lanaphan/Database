package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) throws Exception {
		getConnection();
	}
	
	public static void createTable() throws Exception {
		try  {
			Connection con = getConnection();
			PreparedStatement create con.prepareStatement
			("CREATE TABLE IF NOT EXISTS "
					+ "User(displayName VARCHAR(15), password VARCHAR(25), email VARCHAR(45), date_of_birth DATE), PRIMARY KEY(email)");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	public static Connection getConnection() throws Exception {
		try  {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/databaseName"; // if using local host "jdbc:mysql://localhost:3306/databaseName" OR if using IP address "jdbc:mysql://IP_ADDRESS:3306/databaseName" 
			String username = "team5";
			String password = "database0431";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connecte");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
}
