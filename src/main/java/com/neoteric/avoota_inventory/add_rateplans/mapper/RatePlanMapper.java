package com.neoteric.avoota_inventory.add_rateplans.mapper;

import com.neoteric.avoota_inventory.add_rateplans.entity.RatePlanEntity;
import com.neoteric.avoota_inventory.add_rateplans.model.RatePlanDTO;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Component
public class RatePlanMapper {
    public static RatePlanEntity toEntity(RatePlanDTO dto, RoomEntity room) {
        return RatePlanEntity.builder()
                .id(dto.getId())
                .ratePlanName(dto.getRatePlanName())
                .mealPlan(dto.getMealPlan())
                .room(room)
                .build();
    }

    public static RatePlanDTO toDTO(RatePlanEntity ratePlan) {
        return RatePlanDTO.builder()
                .id(ratePlan.getId())
                .ratePlanName(ratePlan.getRatePlanName())
                .mealPlan(ratePlan.getMealPlan())
                .roomId(ratePlan.getRoom().getRoomId())
                .build();
    }
}
