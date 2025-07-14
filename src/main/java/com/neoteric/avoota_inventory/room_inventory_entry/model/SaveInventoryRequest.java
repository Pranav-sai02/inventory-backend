package com.neoteric.avoota_inventory.room_inventory_entry.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd") // 👈 fixes parsing from frontend
    private LocalDate fromDate;

    @JsonFormat(pattern = "yyyy-MM-dd") // 👈 same here
    private LocalDate toDate;
    private List<RoomAvailabilityDTO> rooms;
}
