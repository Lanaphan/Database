package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	public static Connection con;
	
	public Database() throws Exception {
		con = getConnection();
		createTable();
	}
	
	//OK TO DELETE, EXAMPLE PURPOSE ONLY
	public void createTable() throws Exception {
		try  {
			PreparedStatement create = con.prepareStatement
			("CREATE TABLE IF NOT EXISTS User(displayName VARCHAR(15), password VARCHAR(25), email VARCHAR(45), date_of_birth DATE, PRIMARY KEY(email));");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function Complete");
		}
		
	}
	public Connection getConnection() throws Exception {
		try  {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:4701/MusicVoyager"; // if using local host "jdbc:mysql://localhost:3306/databaseName" OR if using IP address "jdbc:mysql://IP_ADDRESS:3306/databaseName" 
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
