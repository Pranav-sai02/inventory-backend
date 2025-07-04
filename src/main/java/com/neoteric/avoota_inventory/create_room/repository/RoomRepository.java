package com.neoteric.avoota_inventory.create_room.repository;

import com.neoteric.avoota_inventory.create_room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelHotelId(Long hotelId);
}
