package com.neoteric.avoota_inventory.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "rate_plan_prices", schema = "avoota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatePlanPriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "rate_plan_id")
    private Long ratePlanId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price_per_one")
    private Double pricePerOne;

    @Column(name = "price_per_two")
    private Double pricePerTwo;
}
