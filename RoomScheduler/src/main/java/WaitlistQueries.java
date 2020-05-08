
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
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
public class WaitlistQueries {
    private static Connection connection;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement addWaitlist;
    private static ResultSet resultSet;
    private static String statuslabel;
    
    public void getWailistByDate(Date date) {
        
    }
    
    public void getWaitListByFaculty(String name) {
        
    }
    
    public static ArrayList<WaitlistEntry> getAllWaitList() {
        ArrayList<WaitlistEntry> allWaitList = new ArrayList<>();
        try {
            connection = DBConnection.getConnection();
            getWaitlist = connection.prepareStatement("SELECT Faculty, Date, Seats, Timestamp FROM WAITLIST ORDER BY Date, Timestamp");
            resultSet = getWaitlist.executeQuery();
            while(resultSet.next()) {
                String faculty = resultSet.getString(1);
                Date date = resultSet.getDate(2);
                int seats = resultSet.getInt(3);
                Timestamp timestamps = resultSet.getTimestamp(4);
                WaitlistEntry waitlist = new WaitlistEntry(faculty, date, seats, timestamps);
                allWaitList.add(waitlist);
            }
        }

        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allWaitList;
    }
    
    public static String addWaitlistEntry(String name, Date date, int seats, Timestamp timestamp) {

        try {
            connection = DBConnection.getConnection();
            addWaitlist = connection.prepareStatement("INSERT INTO WAITLIST (Faculty, Date, Seats, Timestamp) VALUES(?, ?, ?, ?)");
            addWaitlist.setString(1,name);
            addWaitlist.setDate(2, date);
            addWaitlist.setInt(3,seats);
            addWaitlist.setTimestamp(4, timestamp);
            addWaitlist.executeUpdate();   
            statuslabel = String.format("Successfully add into waitlist: \nfaculty:%s\nDate:%s\nSeats:%s", name, date, seats);
            return statuslabel;
        }

        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        return "Each faculty can only reserve 1 room per date.";
    }
    
    public void deleteWaitlistEntry() {
        
    }
}
