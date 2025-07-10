package com.neoteric.avoota_inventory.add_hotel.mapper;

import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;
import com.neoteric.avoota_inventory.create_room.mapper.RoomMapper;
import com.neoteric.avoota_inventory.create_room.model.RoomDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    private final ModelMapper modelMapper;
    private final RoomMapper roomMapper;

    public HotelMapper(ModelMapper modelMapper, RoomMapper roomMapper) {
        this.modelMapper = modelMapper;
        this.roomMapper = roomMapper;
    }

    public HotelDTO toDto(HotelEntity hotelEntity) {
        HotelDTO dto = modelMapper.map(hotelEntity, HotelDTO.class);

        if (hotelEntity.getRooms() != null) {
            List<RoomDTO> roomDTOs = hotelEntity.getRooms().stream()
                    .map(roomMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setRooms(roomDTOs);
        }

        return dto;
    }

    public HotelEntity toEntity(HotelDTO hotelDTO) {
        return modelMapper.map(hotelDTO, HotelEntity.class);
    }
}
