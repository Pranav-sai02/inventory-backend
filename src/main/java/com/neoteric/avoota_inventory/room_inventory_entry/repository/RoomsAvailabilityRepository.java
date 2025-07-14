package com.neoteric.avoota_inventory.room_inventory_entry.repository;

import com.neoteric.avoota_inventory.room_inventory_entry.entity.RoomsAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomsAvailabilityRepository extends JpaRepository<RoomsAvailabilityEntity, Long> {
    List<RoomsAvailabilityEntity> findByHotelIdAndDateBetween(Long hotelId, LocalDate fromDate, LocalDate toDate);

    Optional<RoomsAvailabilityEntity> findByRoomIdAndDate(Long roomId, LocalDate date);
}
