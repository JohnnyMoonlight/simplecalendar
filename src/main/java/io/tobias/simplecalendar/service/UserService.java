package io.tobias.simplecalendar.service;
import io.tobias.simplecalendar.dtos.UserDTO;
import io.tobias.simplecalendar.model.CalendarUser;
import io.tobias.simplecalendar.repositories.CalendarUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserService {

    @Autowired
    CalendarUserRepository calendarUserRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public CalendarUser registerNewAccount(UserDTO userdto) {
        if (userdto.getUsername() == null || userdto.getUsername().isEmpty() || userdto.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username must not be empty.");
        }
        if (userExists(userdto)) {
            throw new IllegalArgumentException("Username already in use.");
        }
        if (userdto.getEmail() == null || userdto.getEmail().isEmpty() || userdto.getEmail().isBlank()) {
            throw new IllegalArgumentException("eMail must not be empty.");
        }
        if (userdto.getPassword() == null || userdto.getPassword().isEmpty() || userdto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password must not be empty.");
        }

        CalendarUser calendarUser = new CalendarUser();
        calendarUser.setUsername(userdto.getUsername());
        calendarUser.setEmail(userdto.getEmail());
        calendarUser.setPassword(encoder.encode(userdto.getPassword()));

        return calendarUserRepository.save(calendarUser);

    }


    private boolean userExists(UserDTO userdto) {
        final CalendarUser user = calendarUserRepository.findByUsername(userdto.getUsername());
        if (user == null) {
            return true;
        }
        return false;
    }
}
