package io.tobias.simplecalendar.repositories;
import io.tobias.simplecalendar.model.Appointment;
import io.tobias.simplecalendar.model.CalendarEntry;

import org.springframework.data.repository.CrudRepository;


public interface CalendarEntryRepository extends CrudRepository<CalendarEntry, Integer> {
}
