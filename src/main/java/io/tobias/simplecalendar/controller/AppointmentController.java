package io.tobias.simplecalendar.controller;

import io.tobias.simplecalendar.model.Appointment;
import io.tobias.simplecalendar.model.CalendarEntry;
import io.tobias.simplecalendar.model.Room;
import io.tobias.simplecalendar.repositories.CalendarEntryRepository;
import io.tobias.simplecalendar.repositories.RoomRepository;
import static io.tobias.simplecalendar.service.AppointmentService.createCalendarEntriesFromAppointment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class AppointmentController {

    private             Logger logger     = LoggerFactory.getLogger(AppointmentController.class);

    private static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String START_TIME            = "startTime";
    private static final String END_TIME              = "endTime";
    private static final String ROOM                  = "room";
    private static final String ROOM_ID               = "roomId";
    private static final String ID                    = "id";
    private static final String NAME                  = "name";
    private static final String NUMBER_OF_RECURRENCES = "numberOfRecurrences";
    private static final String IS_RECURRING_EVENT    = "isRecurringEvent";
    private static final String RECURRING_CYCLE       = "recurringCycle";

    final Gson gson = new GsonBuilder().setDateFormat(DATEFORMAT).registerTypeAdapter(CalendarEntry.class, new CalendarEntrySerializer()).create();

    @Autowired
    CalendarEntryRepository calendarEntryRepository;

    @Autowired
    RoomRepository roomRepository;


    @CrossOrigin(origins = "*")
    @PostMapping("/createAppointment")
    public void createNewAppointment(@RequestBody JsonObject request) {

        final JsonObject requestJson = request.getAsJsonObject();

        final boolean isRecurringEvent = requestJson.get(IS_RECURRING_EVENT).getAsBoolean();
        final String recurringCycle = requestJson.get(RECURRING_CYCLE).getAsString();
        final int numberOfRecurrences = requestJson.get(NUMBER_OF_RECURRENCES).getAsInt();
        Optional<Room> room = roomRepository.findById(requestJson.get(ROOM_ID).getAsInt());
        if (!room.isPresent()) {
            throw new IllegalArgumentException("Cannot create appointment for non-existent room.");
        }

        Date startTime = gson.fromJson(requestJson.get(START_TIME), Date.class);
        Date endTime = gson.fromJson(requestJson.get(END_TIME), Date.class);

        Appointment appointment = new Appointment(room.get(), startTime, endTime, isRecurringEvent, recurringCycle, numberOfRecurrences);

        final List<CalendarEntry> calendarEntriesFromAppointment = createCalendarEntriesFromAppointment(appointment);
        calendarEntryRepository.saveAll(calendarEntriesFromAppointment);

    }


    @CrossOrigin(origins = "*")
    @GetMapping("/allAppointments")
    public JsonElement getAppointments() {
        logger.debug("Get Appointments has been called");
        Iterable<CalendarEntry> all = calendarEntryRepository.findAll();
        final String s = gson.toJson(all);

        return JsonParser.parseString(s);
    }


    private class CalendarEntrySerializer implements JsonSerializer<CalendarEntry> {

        @Override
        public JsonElement serialize(CalendarEntry src, Type typeOfSrc, JsonSerializationContext context) {
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
