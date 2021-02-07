package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.Appointment;
import io.tobias.simplecalendar.model.Room;
import io.tobias.simplecalendar.repositories.AppointmentRepository;
import io.tobias.simplecalendar.repositories.RoomRepository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.Optional;


@RestController
public class AppointmentController {

    Gson gson = new Gson();

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

        Iterable<Appointment> all = appointmentRepository.findAll();
        return new JsonParser().parse(gson.toJson(all));

    }

}
