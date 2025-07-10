package com.neoteric.avoota_inventory.add_hotel.service;

import com.neoteric.avoota_inventory.add_hotel.entity.HotelEntity;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import com.neoteric.avoota_inventory.create_room.mapper.RoomMapper;
import com.neoteric.avoota_inventory.create_room.repository.RoomRepository;

import com.neoteric.avoota_inventory.add_hotel.mapper.HotelMapper;
import com.neoteric.avoota_inventory.add_hotel.model.HotelDTO;
import com.neoteric.avoota_inventory.add_hotel.repository.HotelRepository;
import com.neoteric.avoota_inventory.exception.HotelNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public HotelService(HotelRepository hotelRepository, HotelMapper hotelMapper, RoomRepository roomRepository, RoomMapper roomMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }

    public HotelDTO saveHotel(HotelDTO dto) {
        try {
            log.info("Saving or updating hotel: {}", dto.getHotelName());

            HotelEntity existingHotel = hotelRepository.findById(dto.getHotelId()).orElse(null);

            if (existingHotel != null) {
                log.info("Hotel already exists. Updating existing hotel with ID: {}", dto.getHotelId());
                existingHotel.setHotelName(dto.getHotelName());
                existingHotel.setHotelAddress(dto.getHotelAddress());
                hotelRepository.save(existingHotel);
                return hotelMapper.toDto(existingHotel);
            } else {
                log.info("Hotel does not exist. Creating new hotel with ID: {}", dto.getHotelId());
                HotelEntity saved = hotelRepository.save(hotelMapper.toEntity(dto));
                return hotelMapper.toDto(saved);
            }

        } catch (Exception ex) {
            log.error("Exception occurred while saving hotel: {}", dto.getHotelName(), ex);
            throw new RuntimeException("Error saving hotel", ex);
        }
    }

    public HotelDTO getHotel(Long id) {
        try {
            log.info("Fetching hotel by ID: {}", id);
            HotelEntity hotel = hotelRepository.findById(id)
                    .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + id + " not found"));

            // Use custom method to fetch rooms with rate plans
            List<RoomEntity> roomsWithRatePlans = roomRepository.findByHotel_HotelIdWithRatePlans(id);
            hotel.setRooms(roomsWithRatePlans);

            return hotelMapper.toDto(hotel);
        } catch (HotelNotFoundException ex) {
            log.warn("Hotel not found: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception occurred while fetching hotel with ID: {}", id, ex);
            throw new RuntimeException("Error fetching hotel", ex);
        }
    }

    public HotelDTO updateHotel(Long id, HotelDTO dto) {
        try {
            log.info("Updating hotel with ID: {}", id);
            HotelEntity hotel = hotelRepository.findById(id)
                    .orElseThrow(() -> new HotelNotFoundException("Hotel with ID " + id + " not found"));

            hotel.setHotelName(dto.getHotelName());
            hotel.setHotelAddress(dto.getHotelAddress());

            HotelEntity updated = hotelRepository.save(hotel);
            return hotelMapper.toDto(updated);
        } catch (HotelNotFoundException ex) {
            log.warn("Hotel not found during update: {}", id);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception occurred while updating hotel with ID: {}", id, ex);
            throw new RuntimeException("Error updating hotel", ex);
        }
    }

    public List<HotelDTO> getAllHotels() {
        try {
            log.info("Fetching all hotels from the database");

            List<HotelDTO> hotelDTOList = hotelRepository.findAll()
                    .stream()
                    .map(hotelMapper::toDto)
                    .toList();

            log.info("Successfully fetched {} hotels", hotelDTOList.size());
            return hotelDTOList;

        } catch (Exception ex) {
            log.error("Exception occurred while fetching all hotels", ex);
            throw new RuntimeException("Error fetching all hotels", ex);
        }
    }
}
