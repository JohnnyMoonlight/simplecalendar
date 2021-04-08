package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.Appointment;
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
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
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
        return new JsonParser().parse(gson.toJson(all));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/createRoom")
    public void createRoom(@RequestBody Room room) {
        System.out.println(room.toString());
        roomRepository.save(room);
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
        JsonElement response;
        Optional<Room> byId = roomRepository.findById(id);
        if (byId.isPresent()) {
            return new JsonParser().parse(gson.toJson((byId.get())));
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

            final List<Appointment> appointments = src.getAppointments();
            final int roomId = src.getRoomId();
            final String name = src.getName();

            JsonObject room = new JsonObject();
            room.addProperty("roomId", roomId);
            room.addProperty("name", name);

            JsonArray appointmentsJson = new JsonArray();

            for (Appointment appointment : appointments) {
                JsonObject appointmentJson = new JsonObject();
                appointmentJson.addProperty(ID, appointment.getId());
                appointmentJson.addProperty(START_TIME, gson.toJson(appointment.getStartTime()));
                appointmentJson.addProperty(END_TIME, gson.toJson(appointment.getEndTime()));
                appointmentsJson.add(appointmentJson);
            }
            room.add(APPOINTMENTS, appointmentsJson);

            return room;
           }
    }
}
