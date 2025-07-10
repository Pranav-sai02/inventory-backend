package com.neoteric.avoota_inventory.add_hotel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.neoteric.avoota_inventory.create_room.entity.RoomEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels", schema = "avoota")
public class HotelEntity {
    @Id
    @Column(name = "hotel_id")
    private Long hotelId;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "hotel_address")
    private String hotelAddress;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RoomEntity> rooms = new ArrayList<>();


}
