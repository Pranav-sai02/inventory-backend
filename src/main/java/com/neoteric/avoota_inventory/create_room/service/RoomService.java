package com.neoteric.avoota_inventory.create_room.service;

import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import com.neoteric.avoota_inventory.exception.HotelNotFoundException;
import com.neoteric.avoota_inventory.add_hotel.repository.HotelRepository;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import com.neoteric.avoota_inventory.exception.RoomNotFoundException;
import com.neoteric.avoota_inventory.create_room.mapper.RoomMapper;
import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import com.neoteric.avoota_inventory.create_room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomMapper roomMapper;

    public ResponseEntity<String> saveRoomDetails(RoomDTO dto) {
        log.info("Entering to saveroomdetailsservie hoteid {} , ", dto.getHotelId());
        try {
            HotelEntity hotel = hotelRepository.findById(dto.getHotelId())
                    .orElseThrow(() -> new HotelNotFoundException("Invalid hotel ID: " + dto.getHotelId()));

            RoomEntity entity = new RoomEntity();
            entity.setHotel(hotel);
            entity.setRoomType(dto.getRoomType());
            entity.setRoomView(dto.getRoomView());
            entity.setSizeUnit(dto.getSizeUnit());
            entity.setRoomSize(dto.getRoomSize());
            entity.setRoomName(dto.getRoomName());
            entity.setNumberOfRooms(dto.getNumberOfRooms());
            entity.setDescription(dto.getDescription());
            roomRepository.save(entity);
            log.info("Saved room for hotel ID = {}", dto.getHotelId());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        } catch (Exception e) {
            log.error("Failed to save room details: {}", e.getMessage(), e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<RoomDTO> getRoomDetailsById(Long roomId) {
        log.info("🔍 Entering getRoomDetailsById() for roomId = {}", roomId);

        try {
            RoomEntity entity = roomRepository.findById(roomId)
                    .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));

            RoomDTO dto = roomMapper.toDTO(entity);
            log.info(" Room found for ID: {}", roomId);
            return new ResponseEntity<>(dto, HttpStatus.OK);

        } catch (RuntimeException e) {
            log.warn(" Room not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(" Error while fetching room details: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<RoomDTO> getRoomsByHotel(Long hotelId) {
        log.info("Fetching rooms for hotel ID: {}", hotelId);
        return roomRepository.findByHotelHotelId(hotelId)
                .stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RoomDTO updateRoom(Long id, RoomDTO dto) {
        log.info("Updating room with ID: {}", id);
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + id));

        room.setRoomType(dto.getRoomType());
        room.setRoomView(dto.getRoomView());
        room.setRoomSize(dto.getRoomSize());
        room.setSizeUnit(dto.getSizeUnit());
        room.setRoomName(dto.getRoomName());
        room.setNumberOfRooms(dto.getNumberOfRooms());
        room.setDescription(dto.getDescription());

        RoomEntity updated = roomRepository.save(room);
        return roomMapper.toDTO(updated);
    }

    public void deleteRoom(Long id) {
        log.info("Deleting room with ID: {}", id);
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + id));
        roomRepository.delete(room);
    }
}
