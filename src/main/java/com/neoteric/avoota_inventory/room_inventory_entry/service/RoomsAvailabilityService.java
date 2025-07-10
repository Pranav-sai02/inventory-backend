package com.neoteric.avoota_inventory.room_inventory_entry.service;

import com.neoteric.avoota_inventory.room_inventory_entry.entity.RoomsAvailabilityEntity;
import com.neoteric.avoota_inventory.room_inventory_entry.model.RoomAvailabilityDTO;
import com.neoteric.avoota_inventory.room_inventory_entry.model.SaveInventoryRequest;
import com.neoteric.avoota_inventory.room_inventory_entry.repository.RoomsAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomsAvailabilityService {
    private final RoomsAvailabilityRepository availabilityRepository;

    public void saveInventory(SaveInventoryRequest request) {
        log.info("Saving inventory for hotel: {}, from {} to {}, rooms: {}", request.getHotelId(), request.getFromDate(), request.getToDate(), request.getRooms().size());

        List<RoomsAvailabilityEntity> entities = new ArrayList<>();

        for (RoomAvailabilityDTO room : request.getRooms()) {
            for (LocalDate date = request.getFromDate(); !date.isAfter(request.getToDate()); date = date.plusDays(1)) {
                RoomsAvailabilityEntity entity = new RoomsAvailabilityEntity();
                entity.setHotelId(request.getHotelId());
                entity.setRoomId(room.getRoomId());
                entity.setRoomName(room.getRoomName());
                entity.setDate(date);
                entity.setAvailableCount(room.getAvailableCount());

                entities.add(entity);
            }
        }

        availabilityRepository.saveAll(entities);
        log.info("Saved {} inventory records", entities.size());
    }
}
