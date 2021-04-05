package io.tobias.simplecalendar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Room {

    public Room() {
        //Empty on purpose for Hibernate
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roomId;

    String name;

    @OneToMany(mappedBy="room")
    List<Appointment> appointments;


    public int getRoomId() {
        return roomId;
    }


    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Appointment> getAppointments() {
        return appointments;
    }


    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", name='" + name + '\'' + ", appointments=" + appointments + '}';
    }
}
