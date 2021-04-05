package io.tobias.simplecalendar.repositories;
import io.tobias.simplecalendar.model.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {


}
