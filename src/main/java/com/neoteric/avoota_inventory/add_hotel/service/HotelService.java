package com.neoteric.avoota_inventory.add_hotel.service;

import com.neoteric.avoota_inventory.add_hotel.entity.Hotel;
import com.neoteric.avoota_inventory.add_hotel.exception.HotelNotFoundException;
import com.neoteric.avoota_inventory.add_hotel.mapper.HotelMapper;
import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;
import com.neoteric.avoota_inventory.add_hotel.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelDTO saveHotel(HotelDTO dto) {
        log.info("Saving or updating hotel: {}", dto.getHotelName());

        Hotel existingHotel = hotelRepository.findById(dto.getHotelId()).orElse(null);

        if (existingHotel != null) {
            log.info("Hotel already exists. Updating existing hotel with ID: {}", dto.getHotelId());
            existingHotel.setHotelName(dto.getHotelName());
            existingHotel.setHotelAddress(dto.getHotelAddress());
            Hotel updated = hotelRepository.save(existingHotel);
            return HotelMapper.toDTO(updated);
        } else {
            log.info("Hotel does not exist. Creating new hotel with ID: {}", dto.getHotelId());
            Hotel saved = hotelRepository.save(HotelMapper.toEntity(dto));
            return HotelMapper.toDTO(saved);
        }
    }

    public HotelDTO getHotel(Long id) {
        log.info("Fetching hotel by ID: {}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + id + " not found"));
        return HotelMapper.toDTO(hotel);
    }

    public HotelDTO updateHotel(Long id, HotelDTO dto) {
        log.info("Updating hotel with ID: {}", id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + id + " not found"));

        hotel.setHotelName(dto.getHotelName());
        hotel.setHotelAddress(dto.getHotelAddress());

        Hotel updated = hotelRepository.save(hotel);
        return HotelMapper.toDTO(updated);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
