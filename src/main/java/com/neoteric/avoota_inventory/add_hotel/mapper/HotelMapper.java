package com.neoteric.avoota_inventory.add_hotel.mapper;

import com.neoteric.avoota_inventory.add_hotel.entity.Hotel;
import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;

public class HotelMapper {
    public static HotelDTO toDTO(Hotel hotel) {
        return new HotelDTO(hotel.getHotelId(), hotel.getHotelName(), hotel.getHotelAddress());
    }

    public static Hotel toEntity(HotelDTO dto) {
        return new Hotel(dto.getHotelId(), dto.getHotelName(), dto.getHotelAddress());
    }
}
