package com.neoteric.avoota_inventory.create_room.model;

import com.neoteric.avoota_inventory.create_room.entity.SizeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private Long roomId;
    private String roomType;
    private String roomView;
    private int roomSize;
    private SizeUnit sizeUnit;
    private String roomName;
    private int numberOfRooms;
    private String description;
    private Long hotelId;
}
