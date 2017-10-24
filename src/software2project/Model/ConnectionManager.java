package software2project.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;
    String connectionUrl = "jdbc:mysql://52.206.157.109/U04gcW";
    private Connection connection = null;
    
    private ConnectionManager(){
    }
    
    public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
    
    private boolean openConnection(){
        try {		
            connection = DriverManager.getConnection(connectionUrl, "U04gcW", "53688234998");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public Connection getConnection(){
        if (connection == null) {
            if (openConnection()) {
                    return connection;
            } else {
                    return null;
            }
        }
        return connection;
	}
    
    public void close() {
		
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
		}
	}
}
