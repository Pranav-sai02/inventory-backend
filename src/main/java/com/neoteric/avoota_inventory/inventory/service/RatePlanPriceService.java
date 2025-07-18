package com.neoteric.avoota_inventory.inventory.service;

import com.neoteric.avoota_inventory.inventory.entity.RatePlanPriceEntity;
import com.neoteric.avoota_inventory.inventory.model.RatePlanPriceDTO;
import com.neoteric.avoota_inventory.inventory.repository.RatePlanPriceRepository;
import com.neoteric.avoota_inventory.room_inventory_entry.entity.RoomsAvailabilityEntity;
import com.neoteric.avoota_inventory.room_inventory_entry.repository.RoomsAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatePlanPriceService { 
    private final RatePlanPriceRepository ratePlanPriceRepository;
    private final RoomsAvailabilityRepository availabilityRepository;

    public List<RatePlanPriceDTO> getRatesByHotel(Long hotelId, LocalDate from, LocalDate to) {
        log.info("Fetching rate plan prices for hotelId={}, fromDate={}, toDate={}", hotelId, from, to);
        try {
            List<RatePlanPriceEntity> prices = ratePlanPriceRepository.findByHotelIdAndDateBetween(hotelId, from, to);
            List<RoomsAvailabilityEntity> availability = availabilityRepository.findByHotelIdAndDateBetween(hotelId, from, to);

            List<RatePlanPriceDTO> response = new ArrayList<>();    

            for (RatePlanPriceEntity price : prices) {
                int availableCount = availability.stream()
                        .filter(a -> a.getRoomId().equals(price.getRoomId()) && a.getDate().equals(price.getDate()))
                        .map(RoomsAvailabilityEntity::getAvailableCount)
                        .findFirst()
                        .orElse(0);

                response.add(RatePlanPriceDTO.builder()
                        .hotelId(price.getHotelId())
                        .roomId(price.getRoomId())
                        .ratePlanId(price.getRatePlanId())
                        .date(price.getDate())
                        .availableCount(availableCount)
                        .pricePerOne(price.getPricePerOne())
                        .pricePerTwo(price.getPricePerTwo())
                        .build());
            }

            log.info("Returning {} rate plans for hotelId={}", response.size(), hotelId);
            return response;

        } catch (Exception e) {
            log.error("Error fetching rates for hotelId={}: {}", hotelId, e.getMessage(), e);
            throw new RuntimeException("Unable to fetch rate plan prices. Please try again later.");
        }
    }

    public void updateRatePlanPrices(List<RatePlanPriceDTO> dtos) {
        log.info("Updating rate plan prices for {} entries", dtos.size());
        try {
            for (RatePlanPriceDTO dto : dtos) {
                RatePlanPriceEntity entity = ratePlanPriceRepository
                        .findByHotelIdAndRoomIdAndRatePlanIdAndDate(
                                dto.getHotelId(),
                                dto.getRoomId(),
                                dto.getRatePlanId(),
                                dto.getDate());

                if (entity != null) {
                    log.info("Updating existing rate plan price (hotelId={}, roomId={}, ratePlanId={}, date={})",
                            dto.getHotelId(), dto.getRoomId(), dto.getRatePlanId(), dto.getDate());

                    entity.setPricePerOne(dto.getPricePerOne());
                    entity.setPricePerTwo(dto.getPricePerTwo());
                } else {
                    log.info("Creating new rate plan price (hotelId={}, roomId={}, ratePlanId={}, date={})",
                            dto.getHotelId(), dto.getRoomId(), dto.getRatePlanId(), dto.getDate());

                    entity = RatePlanPriceEntity.builder()
                            .hotelId(dto.getHotelId())
                            .roomId(dto.getRoomId())
                            .ratePlanId(dto.getRatePlanId())
                            .date(dto.getDate())
                            .pricePerOne(dto.getPricePerOne())
                            .pricePerTwo(dto.getPricePerTwo())
                            .build();
                }

                ratePlanPriceRepository.save(entity);
            }
            log.info("Rate plan prices updated successfully.");
        } catch (Exception e) {
            log.error("Error updating rate plan prices: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to update rate plan prices. Please try again later.");
        }
    }
}
