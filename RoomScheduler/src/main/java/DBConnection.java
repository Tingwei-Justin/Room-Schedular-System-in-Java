
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class DBConnection {
    private static Connection connection;
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";
    private static final String DATABASE_URL ="jdbc:derby://localhost:1527/RoomSchedulerDB;create=true";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
//                Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            }
            catch (SQLException sqlException)
            {
                System.out.println("Database connection error!");
                sqlException.printStackTrace();
                System.exit(1);
            }
//            catch (ClassNotFoundException e) {
//                System.out.println("class not find error!");
//                e.printStackTrace();
//                System.exit(1);
//            }
        }
        return connection;
    }
}
