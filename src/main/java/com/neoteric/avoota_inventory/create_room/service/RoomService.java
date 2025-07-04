package com.neoteric.avoota_inventory.create_room.service;

import com.neoteric.avoota_inventory.add_hotel.entity.Hotel;
import com.neoteric.avoota_inventory.add_hotel.repository.HotelRepository;
import com.neoteric.avoota_inventory.create_room.entity.Room;
import com.neoteric.avoota_inventory.create_room.exception.RoomNotFoundException;
import com.neoteric.avoota_inventory.create_room.mapper.RoomMapper;
import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import com.neoteric.avoota_inventory.create_room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public RoomDTO createRoom(RoomDTO dto) {
        log.info("Creating room for hotel ID: {}", dto.getHotelId());

        Hotel hotel = hotelRepository.findById(dto.getHotelId())
                .orElseThrow(() -> new RoomNotFoundException("Hotel not found with ID: " + dto.getHotelId()));

        Room room = RoomMapper.toEntity(dto, hotel);
        Room saved = roomRepository.save(room);

        return RoomMapper.toDTO(saved);
    }

    public RoomDTO getRoom(Long id) {
        log.info("Fetching room by ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + id));
        return RoomMapper.toDTO(room);
    }

    public List<RoomDTO> getRoomsByHotel(Long hotelId) {
        log.info("Fetching rooms for hotel ID: {}", hotelId);
        return roomRepository.findByHotelHotelId(hotelId)
                .stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RoomDTO updateRoom(Long id, RoomDTO dto) {
        log.info("Updating room with ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + id));

        room.setRoomType(dto.getRoomType());
        room.setRoomView(dto.getRoomView());
        room.setRoomSize(dto.getRoomSize());
        room.setSizeUnit(dto.getSizeUnit());
        room.setRoomName(dto.getRoomName());
        room.setNumberOfRooms(dto.getNumberOfRooms());
        room.setDescription(dto.getDescription());

        Room updated = roomRepository.save(room);
        return RoomMapper.toDTO(updated);
    }

    public void deleteRoom(Long id) {
        log.info("Deleting room with ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with ID: " + id));
        roomRepository.delete(room);
    }
}
