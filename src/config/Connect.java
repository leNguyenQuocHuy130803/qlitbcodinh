package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

	public static Connection getConnect() {
		Connection connect = null;
		try {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/qltbcodinh", "root", "");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return connect;
	}
}
