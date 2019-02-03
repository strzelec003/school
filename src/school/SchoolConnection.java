package school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolConnection {
    private static Connection conn;
    private SchoolConnection() {}
    public static Connection connect()
    {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "school";
        String userName = "root";
        String password = "mysql";
        try
        {
            if(conn==null)
            {
                conn = DriverManager.getConnection(url+dbName,userName,password);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}