package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.CalendarEntry;
import io.tobias.simplecalendar.model.Room;
import io.tobias.simplecalendar.repositories.RoomRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    Gson gson = new GsonBuilder()
    .registerTypeAdapter(Room.class, new RoomSerializer())
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    .create();



    @CrossOrigin(origins = "*")
    @GetMapping("/allRooms")
    public JsonElement allRooms() {
        Iterable<Room> all = roomRepository.findAll();
        System.out.println("Rooms have been retrieved");
        return JsonParser.parseString(gson.toJson(all));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createRoom")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        ResponseEntity<Room> response;
        try {
            if (room.getRoomId() != null) {
                throw new IllegalArgumentException("Illegal input for created room. ID must not be dictated from client. ID is defined in backend.");
            }
            if (room.getName() == null || room.getName().length() == 0) {
                throw new IllegalArgumentException("Illegal input for created room. Room name must be set and not null.");
            }
            roomRepository.save(room);
            response = new ResponseEntity<>(room, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            response = new ResponseEntity<>(room, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return response;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteRoom/{id}")
    public void  deleteRoom(@PathVariable int id){
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()){
            roomRepository.delete(byId.get());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getRoomById/{id}")
    public JsonElement getRoomById(@PathVariable int id){
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()) {
            return JsonParser.parseString(gson.toJson(byId.get()));
        } else {
            JsonObject obj = new JsonObject();
            obj.add("msg", gson.toJsonTree("no room found"));
            return obj;
        }
    }


    private class RoomSerializer implements JsonSerializer<Room> {

        private static final String ID = "id";
        private static final String START_TIME = "startTime";
        private static final String END_TIME = "endTime";
        private static final String APPOINTMENTS = "appointments";


        @Override
        public JsonElement serialize(Room src, Type typeOfSrc, JsonSerializationContext context) {

            final List<CalendarEntry> calendarEntries = src.getCalendarEntry();
            final int roomId = src.getRoomId();
            final String name = src.getName();
            final String icon = src.getIcon();

            JsonObject room = new JsonObject();
            room.addProperty("roomId", roomId);
            room.addProperty("name", name);
            room.addProperty("icon", icon);

            JsonArray appointmentsJson = new JsonArray();

            for (CalendarEntry entry : calendarEntries) {
                JsonObject appointmentJson = new JsonObject();
                appointmentJson.addProperty(ID, entry.getId());
                appointmentJson.addProperty(START_TIME, gson.toJson(entry.getStartTime()));
                appointmentJson.addProperty(END_TIME, gson.toJson(entry.getEndTime()));
                appointmentsJson.add(appointmentJson);
            }
            room.add(APPOINTMENTS, appointmentsJson);

            return room;
           }
    }
}
