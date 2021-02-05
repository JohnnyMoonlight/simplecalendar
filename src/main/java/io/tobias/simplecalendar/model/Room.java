package io.tobias.simplecalendar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Room {

    public Room() {
        //Empty on purpose for Hibernate
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roomId;

    String name;


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
}
