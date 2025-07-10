package com.neoteric.avoota_inventory.room_inventory_entry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAvailabilityDTO {
    private Long roomId;
    private String roomName;
    private int availableCount; // For all selected dates
}
