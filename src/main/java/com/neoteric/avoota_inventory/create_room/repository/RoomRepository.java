package com.neoteric.avoota_inventory.create_room.repository;

import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findByHotelHotelId(Long hotelId);

//    @Query("SELECT r FROM RoomEntity r JOIN FETCH r.hotel WHERE r.hotel.hotelId = :hotelId")
//    List<RoomEntity> findAllByHotelIdWithHotel(@Param("hotelId") Long hotelId);
// Custom query to fetch rooms with rate plans eagerly

    @Query("SELECT r FROM RoomEntity r LEFT JOIN FETCH r.ratePlanEntityList WHERE r.hotel.hotelId = :hotelId")
    List<RoomEntity> findByHotel_HotelIdWithRatePlans(@Param("hotelId") Long hotelId);

}
