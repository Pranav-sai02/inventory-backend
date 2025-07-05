package com.neoteric.avoota_inventory.add_hotel.mapper;

import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {
//    public static HotelDTO toDTO(Hotel hotel) {
//        return new HotelDTO(hotel.getHotelId(), hotel.getHotelName(), hotel.getHotelAddress());
//    }
//
//    public static Hotel toEntity(HotelDTO dto) {
//        return new Hotel(dto.getHotelId(), dto.getHotelName(), dto.getHotelAddress());
//    }

    private final ModelMapper modelMapper;

    public HotelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public HotelDTO toDto(HotelEntity hotelEntity){
        return modelMapper.map(hotelEntity, HotelDTO.class);
    }
    public HotelEntity toEntity(HotelDTO hotelDTO){
        return modelMapper.map(hotelDTO, HotelEntity.class);
    }
}
