package com.neoteric.avoota_inventory.create_room.mapper;

import com.neoteric.avoota_inventory.add_hotel.entity.Hotel;
import com.neoteric.avoota_inventory.create_room.entity.Room;
import com.neoteric.avoota_inventory.create_room.model.RoomDTO;

public class RoomMapper {
    public static Room toEntity(RoomDTO dto, Hotel hotel) {
        return Room.builder()
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

    public static RoomDTO toDTO(Room room) {
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
