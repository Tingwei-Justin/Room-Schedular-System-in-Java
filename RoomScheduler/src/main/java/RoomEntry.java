/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luckyjustin
 */
public class RoomEntry {
    private final String name;
    private final int seats;
    
    public RoomEntry(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getSeats() {
        return this.seats;
    }
}
