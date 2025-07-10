package com.neoteric.avoota_inventory.inventory.services;

import com.neoteric.avoota_inventory.inventory.dto.InventoryDTO;
import com.neoteric.avoota_inventory.inventory.entity.InventoryEntity;
import com.neoteric.avoota_inventory.inventory.mapper.InventoryMapper;
import com.neoteric.avoota_inventory.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryDTO upsertInventory(InventoryDTO dto) {
        try {
            validateInput(dto);

            log.info("Upserting inventory: hotelId={}, roomId={}, ratePlanId={}, date={}, occupancy={}, rate={}",
                    dto.getHotelId(), dto.getRoomId(), dto.getRatePlanId(), dto.getDate(), dto.getOccupancy(), dto.getRate());

            InventoryEntity entity = inventoryRepository
                    .findByHotelIdAndRoomIdAndRatePlanIdAndDate(
                            dto.getHotelId(), dto.getRoomId(), dto.getRatePlanId(), dto.getDate())
                    .orElseGet(InventoryEntity::new);

            applyDtoToEntity(dto, entity);

            InventoryEntity saved = inventoryRepository.save(entity);
            return inventoryMapper.toDto(saved);
        } catch (Exception ex) {
            log.error("❌ Error while upserting inventory: {}", dto, ex);
            throw new RuntimeException("Inventory upsert failed", ex);
        }
    }

    public List<InventoryDTO> getInventoryBetweenDates(Long hotelId, LocalDate startDate, LocalDate endDate) {
        try {
            log.info("Fetching inventory for hotelId={} from {} to {}", hotelId, startDate, endDate);
            return inventoryRepository.findAllByHotelIdAndDateBetween(hotelId, startDate, endDate)
                    .stream()
                    .map(inventoryMapper::toDto)
                    .toList();
        } catch (Exception ex) {
            log.error("❌ Error while fetching inventory for hotelId={}", hotelId, ex);
            throw new RuntimeException("Inventory fetch failed", ex);
        }
    }

    private void applyDtoToEntity(InventoryDTO dto, InventoryEntity entity) {
        entity.setHotelId(dto.getHotelId());
        entity.setRoomId(dto.getRoomId());
        entity.setRatePlanId(dto.getRatePlanId());
        entity.setDate(dto.getDate());
        entity.setAvailableRooms(dto.getAvailableRooms());
        entity.setRate(dto.getRate());
        entity.setOccupancy(dto.getOccupancy()); // ✅ new field
    }

    private void validateInput(InventoryDTO dto) {
        if (dto.getHotelId() == null || dto.getRoomId() == null ||
                dto.getRatePlanId() == null || dto.getDate() == null ||
                dto.getOccupancy() == null || dto.getRate() == null) {
            throw new IllegalArgumentException("Missing required fields in InventoryDTO");
        }
    }
}
