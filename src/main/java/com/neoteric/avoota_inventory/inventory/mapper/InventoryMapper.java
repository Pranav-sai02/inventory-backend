package com.neoteric.avoota_inventory.inventory.mapper;

import com.neoteric.avoota_inventory.inventory.dto.InventoryDTO;
import com.neoteric.avoota_inventory.inventory.entity.InventoryEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryMapper {

    private final ModelMapper modelMapper;

    public InventoryDTO toDto(InventoryEntity entity) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, InventoryDTO.class);
    }

    public InventoryEntity toEntity(InventoryDTO dto) {
        if (dto == null) {
            return null;
        }
        return modelMapper.map(dto, InventoryEntity.class);
    }
}
