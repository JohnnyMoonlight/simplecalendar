package io.tobias.simplecalendar.service;
import io.tobias.simplecalendar.model.Appointment;
import io.tobias.simplecalendar.model.CalendarEntry;
import io.tobias.simplecalendar.model.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;


class AppointmentServiceTest {

    private final int numberOfRecurrences = 2;
    AppointmentService appointmentService = new AppointmentService();

    Room testRoom = new Room(0, "testRoom");

    Date startDate = new Date(2021, 06, 03, 12, 00);
    Date endDate = new Date(2021, 06, 03, 12, 30);

    //the second occurring appointment should be this date
    Date secondAppointment = new Date(2021, 06, 10, 12, 00);


    Appointment testAppointment = new Appointment(testRoom, startDate, endDate, true, "weekly", numberOfRecurrences );




    @Test
    void testCreateNumberOfCreatedCalendarEntriesFromAppointment() {
        assertEquals(numberOfRecurrences, appointmentService.createCalendarEntriesFromAppointment(testAppointment).size());
    }

    @Test
    void testCreatedCalendarEntry() {
        final List<CalendarEntry> calendarEntriesFromAppointment = appointmentService.createCalendarEntriesFromAppointment(testAppointment);
        final CalendarEntry calendarEntry = calendarEntriesFromAppointment.get(1);
        assertEquals(secondAppointment, calendarEntry.getStartTime());
    }

}
