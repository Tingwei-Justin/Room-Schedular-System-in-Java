
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class ReservationQueries {
    private static Connection connection;
    private static PreparedStatement getRoomList;
    private static PreparedStatement getReservationList;
    private static PreparedStatement addReservation;
    private static ResultSet resultSet;
    
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> res = new ArrayList<>();   
        try
        {
            getReservationList = connection.prepareStatement("select Faculty, Room, Date, Seats, Timestamp from reservations where (date) = ?");
            getReservationList.setDate(1, date);
            resultSet = getReservationList.executeQuery();
            
            while(resultSet.next())
            {
                String curfaculty = resultSet.getString(1);
                String curroom = resultSet.getString(2);
                Date curdate = resultSet.getDate(3);
                int curseats = resultSet.getInt(4);
                Timestamp curtime = resultSet.getTimestamp(5);
                ReservationEntry entry = new ReservationEntry(curfaculty, curroom, curdate, curseats, curtime);
                res.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return res;
    }
    
    public void getReservationsByFaculty(String name) {
        
    }
    
    public static String addReservationEntry(String faculty, Date date, int seats) {
        Set<String> reservedSet = getRoomsReservedByDate(date);
        ArrayList<RoomEntry> allRooms = RoomQueries.getAllPossibleRooms();
        boolean success = false;
        Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
        String status = "Error happens!";
        for (RoomEntry room : allRooms) {
            String curRoom = room.getName();
            int curseats = room.getSeats();
            if (!reservedSet.contains(curRoom) && curseats >= seats){
                success = true;
                try {
                    connection = DBConnection.getConnection();
                    addReservation = connection.prepareStatement("INSERT INTO RESERVATIONS (Faculty, Room, Date, Seats, Timestamp) VALUES(?, ?, ?, ?, ?)");
                    addReservation.setString(1,faculty);
                    addReservation.setString(2,curRoom);
                    addReservation.setDate(3, date);  
                    addReservation.setInt(4,seats);
                    addReservation.setTimestamp(5, currentTimestamp);
                    addReservation.executeUpdate();
                    status = String.format("Successfully reserve: \nFaculty: %s, Date: %s, Room: %s, Seats:%s", faculty, date, curRoom, seats);
                    break;
                } catch(SQLException sqlException) {
                    status = "Error happens! Each faculty can only reserve 1 room per date.";
                }
            }
        }
        if (!success) {
            System.out.println("Not available");
            status = WaitlistQueries.addWaitlistEntry(faculty, date, seats, currentTimestamp);
            System.out.println(status);
            
        }
        return status;
    }
    
    public static Set<String> getRoomsReservedByDate(Date date) {
        connection = DBConnection.getConnection();
        Set<String> res = new HashSet<>();   
        try
        {
            System.out.println(date);
            getRoomList = connection.prepareStatement("select room from reservations where (date) = ?");
            getRoomList.setDate(1, date);
            resultSet = getRoomList.executeQuery();
            
            while(resultSet.next())
            {
                res.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return res;
    }
    
    public void deleteReservation() {
        
    }
    
}
