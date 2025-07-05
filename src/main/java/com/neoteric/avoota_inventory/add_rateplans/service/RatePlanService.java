package com.neoteric.avoota_inventory.add_rateplans.service;

import com.neoteric.avoota_inventory.add_rateplans.entity.RatePlanEntity;
import com.neoteric.avoota_inventory.add_rateplans.exception.ResourceNotFoundException;
import com.neoteric.avoota_inventory.add_rateplans.mapper.RatePlanMapper;
import com.neoteric.avoota_inventory.add_rateplans.model.RatePlanDTO;
import com.neoteric.avoota_inventory.add_rateplans.repository.RatePlanRepository;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import com.neoteric.avoota_inventory.create_room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatePlanService {
    private final RatePlanRepository ratePlanRepository;
    private final RoomRepository roomRepository;

    public ResponseEntity<String> addRatePlan(RatePlanDTO dto) {
        log.info("Attempting to add new rate plan: {}", dto);

        validateRatePlanDTO(dto);

        RoomEntity room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> {
                    log.error("Room not found with ID: {}", dto.getRoomId());
                    return new ResourceNotFoundException("Room not found with id: " + dto.getRoomId());
                });

        boolean exists = ratePlanRepository.findByRoomRoomId(dto.getRoomId()).stream()
                .anyMatch(rp -> rp.getRatePlanName().equalsIgnoreCase(dto.getRatePlanName()));

        if (exists) {
            log.warn("Rate plan '{}' already exists for room ID {}", dto.getRatePlanName(), dto.getRoomId());
            throw new IllegalArgumentException("Rate plan with this name already exists for the room");
        }

        RatePlanEntity ratePlan = RatePlanMapper.toEntity(dto, room);
        RatePlanEntity saved = ratePlanRepository.save(ratePlan);

        log.info("Rate plan '{}' saved successfully with ID: {}", saved.getRatePlanName(), saved.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<RatePlanDTO> getRatePlansByRoomId(Long roomId) {
        log.info("Fetching rate plans for roomId: {}", roomId);
        List<RatePlanDTO> ratePlans = ratePlanRepository.findByRoomRoomId(roomId).stream()
                .map(RatePlanMapper::toDTO)
                .collect(Collectors.toList());
        log.info("Found {} rate plan(s) for roomId {}", ratePlans.size(), roomId);
        return ratePlans;
    }

    public RatePlanDTO updateRatePlan(Long id, RatePlanDTO dto) {
        log.info("Updating rate plan with ID: {}", id);
        validateRatePlanDTO(dto);

        RatePlanEntity existing = ratePlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate plan not found with id: " + id));

        existing.setRatePlanName(dto.getRatePlanName());
        existing.setMealPlan(dto.getMealPlan());

        RatePlanEntity updated = ratePlanRepository.save(existing);
        log.info("Updated rate plan ID {} with new name: '{}' and meal plan: '{}'", id, dto.getRatePlanName(), dto.getMealPlan());

        return RatePlanMapper.toDTO(updated);
    }

    private void validateRatePlanDTO(RatePlanDTO dto) {
        if (dto.getRatePlanName() == null || dto.getRatePlanName().trim().isEmpty()) {
            log.error("Validation failed: Rate plan name is empty");
            throw new IllegalArgumentException("Rate plan name cannot be empty");
        }

        if (dto.getMealPlan() == null || dto.getMealPlan().trim().isEmpty()) {
            log.error("Validation failed: Meal plan is empty");
            throw new IllegalArgumentException("Meal plan cannot be empty");
        }

        if (dto.getRoomId() == null) {
            log.error("Validation failed: Room ID is null");
            throw new IllegalArgumentException("Room ID cannot be null");
        }
    }
}
