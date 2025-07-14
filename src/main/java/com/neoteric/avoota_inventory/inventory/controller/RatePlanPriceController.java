package com.neoteric.avoota_inventory.inventory.controller;

import com.neoteric.avoota_inventory.inventory.model.RatePlanPriceDTO;
import com.neoteric.avoota_inventory.inventory.service.RatePlanPriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rate-plan-prices")
@RequiredArgsConstructor
@Slf4j
public class RatePlanPriceController {
    private final RatePlanPriceService service;

    @GetMapping("/get-by-hotel")
    public ResponseEntity<List<RatePlanPriceDTO>> getRates(
            @RequestParam Long hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        log.info("Received GET request for rate plan prices with hotelId={}, from={}, to={}", hotelId, fromDate, toDate);
        List<RatePlanPriceDTO> response = service.getRatesByHotel(hotelId, fromDate, toDate);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/rates/update")
    public ResponseEntity<String> updateRates(@RequestBody List<RatePlanPriceDTO> dtos) {
        log.info("Received POST request to update rate plan prices. Total records: {}", dtos.size());
        service.updateRatePlanPrices(dtos);
        return ResponseEntity.ok("Rates updated successfully.");
    }


}
