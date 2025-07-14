package com.neoteric.avoota_inventory.room_inventory_entry.controller;

import com.neoteric.avoota_inventory.room_inventory_entry.model.SaveInventoryRequest;
import com.neoteric.avoota_inventory.room_inventory_entry.service.RoomsAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor

public class RoomsAvailabilityController {
    private final RoomsAvailabilityService availabilityService;

    @PostMapping("/saveRoomsAvailability")
    public ResponseEntity<String> saveInventory(@RequestBody SaveInventoryRequest request) {
        availabilityService.saveInventory(request);
        System.out.println("Received SaveInventoryRequest:");
        System.out.println("Hotel ID: " + request.getHotelId());
//        System.out.println("Room ID: " + request.getRoomId());
        return ResponseEntity.ok("Inventory saved successfully");
    }
}
