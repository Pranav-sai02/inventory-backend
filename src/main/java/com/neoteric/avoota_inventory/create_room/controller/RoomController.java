package com.neoteric.avoota_inventory.create_room.controller;

import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import com.neoteric.avoota_inventory.create_room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Slf4j
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/saveRoom")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTO dto) {
        log.info("API: Create Room");
        roomService.saveRoomDetails(dto);
        return new ResponseEntity<>("Room saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getByRoom/{id}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable Long id) {
        log.info(" GET Request for Room ID: {}", id);
        return roomService.getRoomDetailsById(id);
    }

    @GetMapping("/hetByHotel/{hotelId}")
    public List<RoomDTO> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    @PutMapping("/update/{id}")
    public RoomDTO updateRoom(@PathVariable Long id, @RequestBody RoomDTO dto) {
        return roomService.updateRoom(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
