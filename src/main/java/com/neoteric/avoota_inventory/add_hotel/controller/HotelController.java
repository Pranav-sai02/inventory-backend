package com.neoteric.avoota_inventory.add_hotel.controller;

import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;
import com.neoteric.avoota_inventory.add_hotel.service.HotelService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@Slf4j
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/saveHotel")
    public ResponseEntity<String> saveHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        log.info("POST /api/hotels - saving hotel");
        hotelService.saveHotel(hotelDTO); // call the service method
        return ResponseEntity.ok("Hotel saved successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {
        log.info("GET /api/hotels/{} - fetching hotel", id);
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        log.info("PUT /api/hotels/{} - updating hotel", id);
        return ResponseEntity.ok(hotelService.updateHotel(id, hotelDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        log.info("GET /api/hotels/all - Fetching all hotels");

        try {
            List<HotelDTO> hotels = hotelService.getAllHotels();
            log.info("Fetched {} hotels", hotels.size());
            return ResponseEntity.ok(hotels);
        } catch (Exception ex) {
            log.error("Error occurred while fetching all hotels", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


}
