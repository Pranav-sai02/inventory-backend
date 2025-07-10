package com.neoteric.avoota_inventory.add_hotel.model;

import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    @NotNull(message = "Hotel ID is required")
    private Long hotelId;
    @NotBlank(message = "Hotel name is required")
    private String hotelName;
    private String hotelAddress;
    private List<RoomDTO> rooms;

}
