package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.Room;
import io.tobias.simplecalendar.repositories.RoomRepository;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    Gson gson = new Gson();
    @CrossOrigin(origins = "*")

    @GetMapping(path="/allRooms")
    public String allRooms() {
        Iterable<Room> all = roomRepository.findAll();
        return gson.toJson(all);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createRoom")
    public void createRoom(@RequestBody Room room) {
        roomRepository.save(room);
    }







}
