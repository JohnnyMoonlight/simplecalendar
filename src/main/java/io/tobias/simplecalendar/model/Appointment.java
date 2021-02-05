package io.tobias.simplecalendar.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;


@Entity
public class Appointment {

    @Id
    int id;

    @OneToOne
    Room room;
    Date startTime;
    Date endTime;


    public Appointment() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Room getRoom() {
        return room;
    }


    public void setRoom(Room room) {
        this.room = room;
    }


    public Date getStartTime() {
        return startTime;
    }


    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getEndTime() {
        return endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
