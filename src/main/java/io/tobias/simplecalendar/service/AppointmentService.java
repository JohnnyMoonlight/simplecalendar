package io.tobias.simplecalendar.service;
import io.tobias.simplecalendar.model.Appointment;
import io.tobias.simplecalendar.model.CalendarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AppointmentService {


    private static final int WEEK_DURATION = 7;


    public AppointmentService() {

    }


    public static List<CalendarEntry> createCalendarEntriesFromAppointment(Appointment appointment) {

        final boolean weeklyRecurringEvent = appointment.isWeeklyRecurringEvent();

        List<CalendarEntry> newEntries = new ArrayList<>();

        if (weeklyRecurringEvent) {

            final int numberOfRecurrences = appointment.getNumberOfRecurrences();
            Date startTime = appointment.getStartTime();
            Date endTime = appointment.getEndTime();

            for (int i = 0; i<numberOfRecurrences; i++) {
                final Date reoccurringStartDate = addDays(startTime, WEEK_DURATION * i);
                final Date reoccurringEndDate = addDays(endTime, WEEK_DURATION * i);

                CalendarEntry calendarEntryPerWeek = new CalendarEntry(appointment.getRoom(), reoccurringStartDate, reoccurringEndDate);

                newEntries.add(calendarEntryPerWeek);
            }

        } else {
            CalendarEntry entry = new CalendarEntry(appointment.getRoom(), appointment.getStartTime(), appointment.getEndTime());
            newEntries.add(entry);
        }

        return newEntries;

    }

    static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}
