package com.neoteric.avoota_inventory.inventory.controller;

import com.neoteric.avoota_inventory.inventory.dto.InventoryDTO;
import com.neoteric.avoota_inventory.inventory.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/view-structure")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryStructureController {

    private final InventoryService inventoryService;

    // ✅ GET - to view inventory for hotel between dates
    @GetMapping("/{hotelId}")
    public List<InventoryDTO> getInventoryByDateRange(
            @PathVariable Long hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        log.info("Fetching inventory structure for hotel {} from {} to {}", hotelId, startDate, endDate);
        return inventoryService.getInventoryBetweenDates(hotelId, startDate, endDate);
    }

    // ✅ POST - to upsert (create or update) inventory data
    @PostMapping("/save")
    public InventoryDTO upsertInventory(@RequestBody InventoryDTO dto) {
        log.info("Upserting inventory from structure controller: {}", dto);
        return inventoryService.upsertInventory(dto);
    }
}
