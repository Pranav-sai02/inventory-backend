package com.neoteric.avoota_inventory.inventory.dto;


import com.neoteric.avoota_inventory.inventory.entity.OccupancyType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InventoryDTO {

    private Long hotelId;           // Hotel ID - used for filtering
    private Long roomId;            // Room ID - required for inventory mapping
    private Long ratePlanId;        // Rate Plan ID - maps the pricing plan
    private LocalDate date;         // Date for this inventory entry
    private Integer availableRooms; // Number of rooms available for that date
    private Integer rate;           // Price per night for this room on this date
    private OccupancyType occupancy; // SINGLE / DOUBLE - optional enum
}
