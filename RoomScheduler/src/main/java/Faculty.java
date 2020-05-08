import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Faculty
{
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement add;
    private static PreparedStatement list;
    private static ResultSet result;
    
    public static String addFaculty(String name)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            add = connection.prepareStatement("insert into Faculty (name) values (?)");
            add.setString(1, name);
            add.executeUpdate();
            return "Add ["+name+"] successfully!";
        }
        
        catch(SQLException sqlException)
        {
            System.out.println("Insert error");
        }
        return "Add ["+name+"] error! (You can not add the same faculty)";
        
    }
    
    public static ArrayList<String> getFacultyList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> faculty = new ArrayList<String>();
        
        try
        {
            list = connection.prepareStatement("select name from faculty order by name");
            result = list.executeQuery();
            
            while(result.next())
            {
                faculty.add(result.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return faculty;
        
    }   
}
