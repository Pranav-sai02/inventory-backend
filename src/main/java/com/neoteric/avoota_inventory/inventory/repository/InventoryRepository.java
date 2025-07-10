package com.neoteric.avoota_inventory.inventory.repository;

import com.neoteric.avoota_inventory.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    // Find a specific inventory entry based on unique combination of keys
    Optional<InventoryEntity> findByHotelIdAndRoomIdAndRatePlanIdAndDate(
            Long hotelId,
            Long roomId,
            Long ratePlanId,
            LocalDate date
    );

    // Fetch all inventory for a hotel between a given date range
    List<InventoryEntity> findAllByHotelIdAndDateBetween(
            Long hotelId,
            LocalDate startDate,
            LocalDate endDate
    );
}
