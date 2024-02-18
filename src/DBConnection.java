import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	// Declaring SQL classes
	Connection conn;
	PreparedStatement prep_stmt;
	ResultSet resultSet;
	
	// DB Credentials
	private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private final String dbName = "studentinventory";
	private final String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
	private final String dbUsername = "root";
	private final String dbPassword = "";
	
	DBConnection(){
		Connect();
	}
	
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
		}
	}
}
