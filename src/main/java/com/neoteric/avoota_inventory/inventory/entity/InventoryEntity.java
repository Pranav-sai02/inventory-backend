package com.neoteric.avoota_inventory.inventory.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inventory", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hotel_id", "room_id", "rate_plan_id", "date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id", nullable = false)
    private Long hotelId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "rate_plan_id", nullable = false)
    private Long ratePlanId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "available_rooms", nullable = false)
    private Integer availableRooms;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "occupancy", nullable = false)
    private OccupancyType occupancy;
}
