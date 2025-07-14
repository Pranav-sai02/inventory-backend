package com.neoteric.avoota_inventory.inventory.repository;

import com.neoteric.avoota_inventory.inventory.entity.RatePlanPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RatePlanPriceRepository extends JpaRepository<RatePlanPriceEntity, Long> {
    List<RatePlanPriceEntity> findByHotelIdAndDateBetween(Long hotelId, LocalDate from, LocalDate to);
    RatePlanPriceEntity findByHotelIdAndRoomIdAndRatePlanIdAndDate(Long hotelId, Long roomId, Long ratePlanId, LocalDate date);
}
