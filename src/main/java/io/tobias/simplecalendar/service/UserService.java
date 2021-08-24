package io.tobias.simplecalendar.service;
import io.tobias.simplecalendar.dtos.UserDTO;
import io.tobias.simplecalendar.model.CalendarUser;
import io.tobias.simplecalendar.repositories.CalendarUserRepository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserService {

    @Autowired
    CalendarUserRepository calendarUserRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public static JsonObject retrieveUserStatus() {
        JsonObject userStatus = new JsonObject();
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        final Object isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        final Object authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            userStatus.add("name", JsonParser.parseString(username));
            userStatus.add("isAuthenticated", JsonParser.parseString(isAuthenticated.toString()));
            userStatus.add("authorities", JsonParser.parseString(authorities.toString()));

        }
        else {
            String username = principal.toString();
            userStatus.add("name", JsonParser.parseString(username));
            userStatus.add("isAuthenticated", JsonParser.parseString(isAuthenticated.toString()));
            userStatus.add("authorities", JsonParser.parseString(authorities.toString()));
        }
        return userStatus;
    }


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
