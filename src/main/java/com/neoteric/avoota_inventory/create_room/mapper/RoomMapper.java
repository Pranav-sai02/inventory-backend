package com.neoteric.avoota_inventory.create_room.mapper;

import com.neoteric.avoota_inventory.add_rateplans.mapper.RatePlanMapper;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoomMapper {
    private final RatePlanMapper ratePlanMapper;

    public RoomMapper(RatePlanMapper ratePlanMapper) {
        this.ratePlanMapper = ratePlanMapper;
    }

    public RoomDTO toDTO(RoomEntity room) {
        log.info("Mapping RoomEntity with ID: {}, RatePlans: {}",
                room.getRoomId(),
                room.getRatePlanEntityList() != null ? room.getRatePlanEntityList().size() : "null");
        return RoomDTO.builder()
                .roomId(room.getRoomId())
                .roomType(room.getRoomType())
                .roomView(room.getRoomView())
                .roomSize(room.getRoomSize())
                .sizeUnit(room.getSizeUnit())
                .roomName(room.getRoomName())
                .numberOfRooms(room.getNumberOfRooms())
                .description(room.getDescription())
                .hotelId(room.getHotel().getHotelId())
                .ratePlans(
                        room.getRatePlanEntityList() != null ?
                                room.getRatePlanEntityList().stream()
                                        .map(ratePlanMapper::toDTO)
                                        .toList()
                                : null
                )
                .build();
    }
}
