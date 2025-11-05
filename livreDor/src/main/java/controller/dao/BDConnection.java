package controller.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {

	private static Connection connection = null;
	
	private BDConnection() throws SQLException {
		try {
			Class.forName(Confige.DRIVER_MYSQL);
			this.connection = DriverManager.getConnection(Confige.URL , Confige.USER, Confige.PWD);
		}catch (ClassNotFoundException e) {
			System.err.println("Probléme de driver \n" + e.getMessage());
		}
		
	}
	public static Connection getConnection() throws SQLException {
		try {
			
		if(connection == null || connection.isClosed()) {
			new BDConnection();
		}
		}catch(SQLException e) {
			System.err.println("Propleme de connection");
		}
		return connection;
	}
	
	
}
