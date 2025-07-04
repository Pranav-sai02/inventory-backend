package com.neoteric.avoota_inventory.add_rateplans.service;

import com.neoteric.avoota_inventory.add_rateplans.entity.RatePlan;
import com.neoteric.avoota_inventory.add_rateplans.exception.ResourceNotFoundException;
import com.neoteric.avoota_inventory.add_rateplans.mapper.RatePlanMapper;
import com.neoteric.avoota_inventory.add_rateplans.model.RatePlanDTO;
import com.neoteric.avoota_inventory.add_rateplans.repository.RatePlanRepository;
import com.neoteric.avoota_inventory.create_room.entity.Room;
import com.neoteric.avoota_inventory.create_room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatePlanService {
    private final RatePlanRepository ratePlanRepository;
    private final RoomRepository roomRepository;

    public RatePlanDTO addRatePlan(RatePlanDTO dto) {
        log.info("Adding new rate plan: {}", dto.getRatePlanName());

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + dto.getRoomId()));

        RatePlan ratePlan = RatePlanMapper.toEntity(dto, room);
        RatePlan saved = ratePlanRepository.save(ratePlan);

        return RatePlanMapper.toDTO(saved);
    }

    public List<RatePlanDTO> getRatePlansByRoomId(Long roomId) {
        log.info("Fetching rate plans for roomId: {}", roomId);
        return ratePlanRepository.findByRoomRoomId(roomId).stream()
                .map(RatePlanMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RatePlanDTO updateRatePlan(Long id, RatePlanDTO dto) {
        log.info("Updating rate plan with id: {}", id);

        RatePlan existing = ratePlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rate plan not found with id: " + id));

        existing.setRatePlanName(dto.getRatePlanName());
        existing.setMealPlan(dto.getMealPlan());

        RatePlan updated = ratePlanRepository.save(existing);
        return RatePlanMapper.toDTO(updated);
    }
}
