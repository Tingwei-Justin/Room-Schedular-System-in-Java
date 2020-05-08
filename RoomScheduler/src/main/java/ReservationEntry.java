
import java.sql.Timestamp;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class ReservationEntry {
    private final String faculty;
    private final String room;
    private final Date date;
    private final int seats;
    private final Timestamp timestamp;
    
    public ReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp) {
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }
    
    public String getFaculty() {
        return this.faculty;
    }
    public String getRoom() {
        return this.room;
    }
    public Date getDate() {
        return this.date;
    }
    public int getSeats() {
        return this.seats;
    }
    public Timestamp getTimeStamp() {
        return this.timestamp;
    }
    
}
