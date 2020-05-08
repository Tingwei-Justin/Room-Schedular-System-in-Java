
import java.sql.Timestamp;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class WaitlistEntry {
    private final String faculty;
    private final Date date;
    private final int seats;
    private final Timestamp timestamp;

    public WaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp) {
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    
    
}
