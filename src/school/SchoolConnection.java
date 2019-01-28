package school;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolConnection {
    private static String url = "jdbc:mysql://localhost:3306/school";
    private static String login = "root";
    private static String password = "mysql";
    private Connection connection;


    public static Connection connect() {
        if(connection != null){
            return connection;
        }
        else
{        
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
        }
    }
}