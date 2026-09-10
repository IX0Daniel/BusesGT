package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author dz
 */
public class ConexionDB {
    
    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "BUS";
    private static final String USERNAME = "AdminBUS";
    private static final String PASSWORD = "BUS-PSWRD";
    private static final String URL = "jdbc:mariadb://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private ConexionDB() {}

    public static Connection getConnection() throws SQLException {
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar con la base de datos"+e);  
        }
        
        return null;   
        
    }
    
    
}
