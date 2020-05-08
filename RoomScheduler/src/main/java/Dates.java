
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
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
public class Dates {
    private static Connection connection;
    private static PreparedStatement getDatesQuery;
    private static ResultSet result;

    public static ArrayList<Date> getAllDates() {
        connection = DBConnection.getConnection();
        ArrayList<Date> dates = new ArrayList<>();
        
        try {
            getDatesQuery = connection.prepareStatement("select date from dates order by date");
            result = getDatesQuery.executeQuery();
            
            while(result.next()) {
                dates.add(result.getDate(1));
            }
        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return dates;
    }
//    public static void addDate() {
//        
//    }
    public static void main(String[] argv) {
        ArrayList<Date> res = Dates.getAllDates();
        Set<String> set = ReservationQueries.getRoomsReservedByDate(res.get(0));
//        ArrayList<RoomEntry> rooms= RoomQueries.getAllPossibleRooms();
//        System.out.println(res);
//        System.out.println(set);
//        for (RoomEntry room : rooms) {
//            System.out.println(room.getName());
//            System.out.println(room.getSeats());
//        }
//         ReservationQueries.addReservationEntry("Iggy", res.get(1), 12);
        ArrayList<WaitlistEntry> waitlist = WaitlistQueries.getAllWaitList();
        for (WaitlistEntry entry : waitlist) {
            System.out.println(entry.getFaculty() + entry.getDate() + entry.getSeats() + entry.getTimestamp());
        }
    }
       
}
