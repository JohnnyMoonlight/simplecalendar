package io.tobias.simplecalendar.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Room {

    public Room() {
        //Empty on purpose for Hibernate
    }


    public Room(Integer roomId, String name) {
        //for testing purposes
        this.roomId = roomId;
        this.name = name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer roomId;

    String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CalendarEntry> calendarEntry;

    String icon;


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Integer getRoomId() {
        return roomId;
    }


    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<CalendarEntry> getCalendarEntries() {
        return calendarEntry;
    }


    public void setCalendarEntry(List<CalendarEntry> calendarEntry) {
        this.calendarEntry = calendarEntry;
    }


    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", name='" + name + '\'' + ", appointments=" + calendarEntry + '}';
    }
}
