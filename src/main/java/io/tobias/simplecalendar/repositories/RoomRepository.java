package io.tobias.simplecalendar.repositories;
import io.tobias.simplecalendar.model.Room;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface RoomRepository extends CrudRepository<Room, Integer> {


}
