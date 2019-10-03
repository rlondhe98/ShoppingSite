package com.utilities;
import java.sql.*;
public class ConnectionManager {
	
	private static Connection conn;
	public ConnectionManager() {}
	public static Connection createConnection() {
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String username="HR";
		String pass="hr";
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,username,pass);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}

}
