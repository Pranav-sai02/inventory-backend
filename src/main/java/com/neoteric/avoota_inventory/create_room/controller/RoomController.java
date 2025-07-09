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
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/save")
    public ResponseEntity<String> createRoom(@RequestBody RoomDTO dto) {
        log.info("API: Create Room");
        roomService.createRoom(dto);
        return new ResponseEntity<>("Room saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public RoomDTO getRoom(@PathVariable Long id) {
        return roomService.getRoom(id);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<RoomDTO> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    @PutMapping("/{id}")
    public RoomDTO updateRoom(@PathVariable Long id, @RequestBody RoomDTO dto) {
        return roomService.updateRoom(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
