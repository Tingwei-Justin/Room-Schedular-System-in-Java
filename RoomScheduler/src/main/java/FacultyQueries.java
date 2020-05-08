/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacultyQueries {
    private Connection connection;
    private PreparedStatement insertNewFaculty;
    
    public FacultyQueries() {
        try {
            connection = DBConnection.getConnection();
            insertNewFaculty = connection.prepareStatement("INSERT INTO Faculty VALUES (?)");
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    /*
        Add an new faculty into database and return number of rows updated
    */
//    public int addFaculty(Faculty faculty) {
//        try {
//            insertNewFaculty.setString(1, faculty.getName());
//            return insertNewFaculty.executeUpdate();
//        }
//        catch (SQLException sqlException){
//            sqlException.printStackTrace();
//            return 0;
//        }
//    }
    
//    public static void main(String[] argv) {
////        Faculty faculty = new Faculty("Justin");
//        FacultyQueries query = new FacultyQueries();
////        int res = query.addFaculty(faculty);
////        System.out.println(res);
//    }
}
