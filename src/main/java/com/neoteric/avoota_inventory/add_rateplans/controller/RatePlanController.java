package com.neoteric.avoota_inventory.add_rateplans.controller;

import com.neoteric.avoota_inventory.add_rateplans.model.RatePlanDTO;
import com.neoteric.avoota_inventory.add_rateplans.service.RatePlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rateplans")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RatePlanController {
    private final RatePlanService ratePlanService;

    @PostMapping("/saveRatePlan")
    public ResponseEntity<String> createRatePlan(@RequestBody RatePlanDTO dto) {
        log.info("POST /api/rateplans - Creating rate plan");

        return  ratePlanService.addRatePlan(dto);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<RatePlanDTO>> getRatePlansByRoom(@PathVariable Long roomId) {
        log.info("GET /api/rateplans/room/{} - Fetching rate plans", roomId);
        return ResponseEntity.ok(ratePlanService.getRatePlansByRoomId(roomId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatePlanDTO> updateRatePlan(@PathVariable Long id, @RequestBody RatePlanDTO dto) {
        log.info("PUT /api/rateplans/{} - Updating rate plan", id);
        return ResponseEntity.ok(ratePlanService.updateRatePlan(id, dto));
    }
}
