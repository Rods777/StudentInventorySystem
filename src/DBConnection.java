import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class DBConnection {
	// Declaring SQL classes
	Connection conn = null;
	PreparedStatement prep_stmt;
	ResultSet resultSet;
	ResultSetMetaData rsmd;
	
	// DB Credentials
	private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private final String dbName = "studentinventory";
	private final String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
	private final String dbUsername = "root";
	private final String dbPassword = "";

	
	void Connect(){
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			if(conn != null) {
				System.out.println("Sucessfully Connected to Database!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
			JOptionPane.showMessageDialog(null, 
					"Connection Error: Please Connect to Database First!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
