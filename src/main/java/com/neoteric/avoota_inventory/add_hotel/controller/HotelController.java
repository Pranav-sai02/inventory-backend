package com.neoteric.avoota_inventory.add_hotel.controller;

import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;
import com.neoteric.avoota_inventory.add_hotel.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "*")
@Slf4j
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveHotel(@RequestBody HotelDTO hotelDTO) {
        log.info("POST /api/hotels - saving hotel");
        hotelService.saveHotel(hotelDTO); // call the service method
        return ResponseEntity.ok("Hotel saved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {
        log.info("GET /api/hotels/{} - fetching hotel", id);
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        log.info("PUT /api/hotels/{} - updating hotel", id);
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO));
    }

    @GetMapping("/hotels")
    public List<HotelEntity> getAllHotels() {
        return hotelService.getAllHotels();
    }
}
