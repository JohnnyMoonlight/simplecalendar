package io.tobias.simplecalendar.repositories;
import io.tobias.simplecalendar.model.Appointment;

import org.springframework.data.repository.CrudRepository;


public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
}
