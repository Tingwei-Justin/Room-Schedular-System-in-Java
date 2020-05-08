
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getRoomQuery;
    private static ResultSet resultSet;
    
    public static ArrayList<RoomEntry> getAllPossibleRooms() {
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<>();
        
        try {
            getRoomQuery = connection.prepareStatement("select name, seats from rooms order by seats");
            resultSet = getRoomQuery.executeQuery();
            
            while(resultSet.next()) {
                String name = resultSet.getString(1);
                int seats = resultSet.getInt(2);
                RoomEntry room = new RoomEntry(name, seats);
                rooms.add(room);
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public void addRoom() {
        
    }
    
    public void dropRoom() {
        
    }
}
