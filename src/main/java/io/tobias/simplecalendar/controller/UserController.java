package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.dtos.UserDTO;
import io.tobias.simplecalendar.model.CalendarUser;
import io.tobias.simplecalendar.repositories.CalendarUserRepository;
import io.tobias.simplecalendar.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.http.HttpResponse;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserController {


    @Autowired
    CalendarUserRepository calendarUserRepository;

    @GetMapping("{id}")
    ResponseEntity<CalendarUser> getUser(@PathVariable Integer id) {
        HttpResponse response;
        final Optional<CalendarUser> byId = calendarUserRepository.findById(id);
        if (byId.isPresent()) {
            final CalendarUser calendarUser = byId.get();
            byId.get();
            return new ResponseEntity<CalendarUser>(calendarUser, HttpStatus.OK);
        }
        throw new IllegalArgumentException("User with id " + id + " not found.");
    }

    @PostMapping
    ResponseEntity<String> createUser (@RequestBody UserDTO userDto) {
        UserService userService = new UserService();
        final CalendarUser calendarUser = userService.registerNewAccount(userDto);
        return new ResponseEntity<String>("Successfully created", HttpStatus.OK);
    }

}
