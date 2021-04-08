package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.Appointment;
import io.tobias.simplecalendar.model.Room;
import io.tobias.simplecalendar.repositories.AppointmentRepository;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class AppointmentController {

    private final String START_TIME = "startTime";
    private final String END_TIME = "endTime";
    private final String ROOM = "room";
    private final String ID = "id";
    private final String NAME = "name";

    Gson gson = new GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    .registerTypeAdapter(Appointment.class, new AppointmentSerializer())
    .create();

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    RoomRepository roomRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("/createAppointment")
    public JsonElement createNewAppointment(@RequestBody JsonElement appointment) {

        Optional<Room> room = roomRepository.findById(appointment.getAsJsonObject().get("roomId").getAsInt());
        Date startTime = gson.fromJson(appointment.getAsJsonObject().get("startTime"), Date.class);
        Date endTime = gson.fromJson(appointment.getAsJsonObject().get("endTime"), Date.class);

        if (room.isPresent()) {
            Appointment newAppointment = new Appointment(room.get(), startTime, endTime);
            appointmentRepository.save(newAppointment);
            return new JsonParser().parse(gson.toJson(appointmentRepository.findById(newAppointment.getId())));
        }
        else {

            JsonObject failedReponse = new JsonObject();
            return failedReponse;
        }


    }

    @CrossOrigin(origins = "*")
    @GetMapping("/allAppointments")
    public JsonElement getAppointments() {
        System.out.println("Get Appointments has been called");
        Iterable<Appointment> all = appointmentRepository.findAll();
        return new JsonParser().parse(gson.toJson(all));

    }

    private class AppointmentSerializer implements JsonSerializer<Appointment> {

        @Override
        public JsonElement serialize(Appointment src, Type typeOfSrc, JsonSerializationContext context) {
            final Date startTime = src.getStartTime();
            final Date endTime = src.getEndTime();
            final int id = src.getId();
            final Room room = src.getRoom();

            JsonObject appointment = new JsonObject();
            appointment.addProperty(START_TIME, gson.toJson(startTime));
            appointment.addProperty(END_TIME, gson.toJson(endTime));
            appointment.addProperty(ID, id);

            JsonObject roomJson = new JsonObject();
            roomJson.addProperty(ID, room.getRoomId());
            roomJson.addProperty(NAME, room.getName());
            appointment.add(ROOM, roomJson);

            return appointment;
        }
    }

}
