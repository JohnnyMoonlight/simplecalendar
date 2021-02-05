package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.Room;
import io.tobias.simplecalendar.repositories.RoomRepository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@Controller
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    Gson gson = new Gson();

    @GetMapping("/allRooms")
    public JsonArray allRooms() {
        Iterable<Room> all = roomRepository.findAll();

        String s = gson.toJson(all);
        return  (JsonArray) new JsonParser().parse(s);
    }

    @PostMapping("/createRoom")
    public void createRoom(@RequestBody String room) {

        final Room objRoom = gson.fromJson(room, Room.class);
        roomRepository.save(objRoom);
    }







}
