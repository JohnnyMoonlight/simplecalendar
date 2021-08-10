package io.tobias.simplecalendar.service;
import io.tobias.simplecalendar.model.CalendarUser;
import io.tobias.simplecalendar.repositories.CalendarUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CalendarUserDetailsService implements UserDetailsService {

    @Autowired
    CalendarUserRepository calendarUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            final CalendarUser user = calendarUserRepository.findByUsername(username);
            if(user == null) {
                throw new UsernameNotFoundException("Username " + username + " not found.");
            } else {
                return user;
            }
    }


}
