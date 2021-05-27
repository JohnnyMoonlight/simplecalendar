package io.tobias.simplecalendar.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    Integer roomId;

    String name;

    @OneToMany(mappedBy = "room", orphanRemoval = true, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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


    public List<CalendarEntry> getCalendarEntry() {
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
