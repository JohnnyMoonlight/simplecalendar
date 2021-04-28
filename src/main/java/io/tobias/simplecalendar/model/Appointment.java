package io.tobias.simplecalendar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;


public class Appointment {


    int id;
    Room room;
    Date startTime;
    Date endTime;
    String recurringCycle;


    public String getRecurringCycle() {
        return recurringCycle;
    }


    public void setRecurringCycle(String recurringCycle) {
        this.recurringCycle = recurringCycle;
    }


    boolean isDailyRecurringEvent;
    boolean isWeeklyRecurringEvent;
    boolean isMonthlyRecurringEvent;
    int     numberOfRecurrences;

    public boolean isDailyRecurringEvent() {
        return isDailyRecurringEvent;
    }


    public void setDailyRecurringEvent(boolean dailyRecurringEvent) {
        isDailyRecurringEvent = dailyRecurringEvent;
    }


    public boolean isMonthlyRecurringEvent() {
        return isMonthlyRecurringEvent;
    }


    public void setMonthlyRecurringEvent(boolean monthlyRecurringEvent) {
        isMonthlyRecurringEvent = monthlyRecurringEvent;
    }



    public Appointment() {
        //Empty on purpose for Hibernate
    }

    public Appointment(Room room, Date startTime, Date endTime) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public int getNumberOfRecurrences() {
        return numberOfRecurrences;
    }


    public void setNumberOfRecurrences(int numberOfRecurrences) {
        this.numberOfRecurrences = numberOfRecurrences;
    }


    public Appointment(Room room, Date startTime, Date endTime, boolean isWeeklyRecurringEvent, String recurringCycle, int numberOfRecurrences) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.recurringCycle = recurringCycle;
        this.isWeeklyRecurringEvent = isWeeklyRecurringEvent;
        this.numberOfRecurrences = numberOfRecurrences;
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


    public boolean isWeeklyRecurringEvent() {
        return isWeeklyRecurringEvent;
    }


    public void setWeeklyRecurringEvent(boolean weeklyRecurringEvent) {
        isWeeklyRecurringEvent = weeklyRecurringEvent;
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
