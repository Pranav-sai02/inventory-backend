package com.neoteric.avoota_inventory.add_hotel.repository;

import com.neoteric.avoota_inventory.add_hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
