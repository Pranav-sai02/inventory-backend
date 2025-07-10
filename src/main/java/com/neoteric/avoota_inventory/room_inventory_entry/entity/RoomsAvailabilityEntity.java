package com.neoteric.avoota_inventory.room_inventory_entry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "room_availability", schema = "avoota",
        uniqueConstraints = @UniqueConstraint(columnNames = {"room_id", "date"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomsAvailabilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rooms_availability_id")
    private Long roomsAvailabilityId;

    @Column(name = "hotel_id", nullable = false)
    private Long hotelId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "available_count", nullable = false)
    private int availableCount;
}
