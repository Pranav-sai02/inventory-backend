package com.neoteric.avoota_inventory.create_room.mapper;

import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public static RoomEntity toEntity(RoomDTO dto, HotelEntity hotel) {
        return RoomEntity.builder()
                .roomId(dto.getRoomId())
                .roomType(dto.getRoomType())
                .roomView(dto.getRoomView())
                .roomSize(dto.getRoomSize())
                .sizeUnit(dto.getSizeUnit())
                .roomName(dto.getRoomName())
                .numberOfRooms(dto.getNumberOfRooms())
                .description(dto.getDescription())
                .hotel(hotel)
                .build();
    }

    public static RoomDTO toDTO(RoomEntity room) {
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
                .build();
    }
}
