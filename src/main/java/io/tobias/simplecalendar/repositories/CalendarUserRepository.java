package io.tobias.simplecalendar.repositories;

import io.tobias.simplecalendar.model.CalendarUser;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CalendarUserRepository extends JpaRepository<CalendarUser, Integer> {

    CalendarUser findByUsername (String name);

    Optional<CalendarUser> findById(Integer id);

    void deleteById(Integer id);
}
