package com.neoteric.avoota_inventory.add_rateplans.mapper;

import com.neoteric.avoota_inventory.add_rateplans.entity.RatePlan;
import com.neoteric.avoota_inventory.add_rateplans.model.RatePlanDTO;
import com.neoteric.avoota_inventory.create_room.entity.Room;

public class RatePlanMapper {
    public static RatePlan toEntity(RatePlanDTO dto, Room room) {
        return RatePlan.builder()
                .id(dto.getId())
                .ratePlanName(dto.getRatePlanName())
                .mealPlan(dto.getMealPlan())
                .room(room)
                .build();
    }

    public static RatePlanDTO toDTO(RatePlan ratePlan) {
        return RatePlanDTO.builder()
                .id(ratePlan.getId())
                .ratePlanName(ratePlan.getRatePlanName())
                .mealPlan(ratePlan.getMealPlan())
                .roomId(ratePlan.getRoom().getRoomId())
                .build();
    }
}
