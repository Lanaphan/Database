package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
	public static Connection con;
	
	public static void main(String[] args) throws Exception {
		con = getConnection();
		createTable();
		insert();

	//OK TO DELETE, EXAMPLE PURPOSE ONLY
	}	public static void insert() throws Exception {
		final String var1 = "John";
		final String var2 = "Miller";
		
		try  {
			PreparedStatement insert = con.prepareStatement
			("INSERT INTO User (firstname, lastname) VALUES ('"+var1+"', '"+var2+"');");
			insert.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Insert Complete");
		}
	}
		
	//OK TO DELETE, EXAMPLE PURPOSE ONLY
	public static void createTable() throws Exception {
		try  {
			PreparedStatement create = con.prepareStatement
			("CREATE TABLE IF NOT EXISTS User(firstname VARCHAR(15), lastname VARCHAR(25));");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Create Table Complete");
		}
		
	}
	public static Connection getConnection() throws Exception {
		try  {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:4701/TestDB"; // if using local host "jdbc:mysql://localhost:3306/databaseName" OR if using IP address "jdbc:mysql://IP_ADDRESS:3306/databaseName" 
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
