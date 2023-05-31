package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private static final String URL = System.getenv("JDBC_DATABASE_URL");
	
	public static Connection connect() throws SQLException {
		//Luo yhteyden ja palauttaa sen 
		
		return DriverManager.getConnection(URL);
	}
}
