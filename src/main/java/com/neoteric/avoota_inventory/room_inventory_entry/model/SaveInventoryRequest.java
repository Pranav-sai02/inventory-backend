package com.neoteric.avoota_inventory.room_inventory_entry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveInventoryRequest {
    private Long hotelId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private List<RoomAvailabilityDTO> rooms;
}
